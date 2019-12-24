package com.xiaoxinshop.order.service.impl;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xiaoxinshop.entity.*;
import com.xiaoxinshop.mapper.OrderItemMapper;
import com.xiaoxinshop.mapper.OrderMapper;
import com.xiaoxinshop.mapper.PayLogMapper;
import com.xiaoxinshop.order.service.OrderService;
import com.xiaoxinshop.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Override
	public List<GOrder> findByUserName(String userId,String status) {
		return orderMapper.findByUserName(userId,status);
	}

	/**
	 * 查询全部
	 */
	@Override
	public List<Order> findAll() {
		return null;
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
	  return  null;
	}

	@Autowired
	RedisTemplate redisTemplate;
	@Autowired
	IdWorker idWorker;
	@Autowired
	OrderItemMapper orderItemMapper;

	@Autowired
	PayLogMapper payLogMapper;
	/**
	 * 增加
	 */
	@Override
	public String add(Order order) {
//		从redis中获取对应购物车列表
		List<Cart> cartList = (List<Cart>)redisTemplate.boundHashOps("cartList").get(order.getUserId());
		System.out.println("cartList"+cartList);


//		支付日志 对应订单号
		StringBuilder orderList =  new StringBuilder("");
		double totalMoney=0;
		for (Cart cart: cartList ) {

			Order newOrder = new Order();
			//设置订单id
        	Long orderId =idWorker.nextId();
			newOrder.setOrderId(String.valueOf(orderId));
			//用户名
			newOrder.setUserId(order.getUserId());
			//支付类型
			newOrder.setPaymentType(order.getPaymentType());
			//状态未付款
			newOrder.setStatus("1");
			//订单创建时间
			newOrder.setCreateTime(new Date());
			//订单更新时间
			newOrder.setUpdateTime(new Date());
			//地址
			newOrder.setReceiverAreaName(order.getReceiverAreaName());
			//手机号
			newOrder.setReceiverMobile(order.getReceiverMobile());
			//收货人
			newOrder.setReceiver(order.getReceiver());
			//订单来源
			newOrder.setSourceType(order.getSourceType());
			//商家ID
			newOrder.setSellerId(cart.getSellerId());
			//循环购物车明细
			double money=0;
			for(OrderItem orderItem :cart.getOrderItemList()){
				//为订单详细表也生成一个不重复id
				orderItem.setId(idWorker.nextId());
				//订单ID
				orderItem.setOrderId( orderId  );
				//金额累加
				money+=orderItem.getTotalFee().doubleValue();
				orderItemMapper.insert(orderItem);
			}
			newOrder.setPayment(new BigDecimal(money));
			orderMapper.insert(newOrder);

//			支付日志 添加对应订单号
			if ("".equals(orderList.toString())){
				System.out.println("第一次添加订单");
				orderList.append(orderId);
			}else {
				orderList.append((","+orderId));
			}

//			支付日志 总金额
			totalMoney += money;


		}

		PayLog  payLog = new PayLog();
		Long outTradeNo = idWorker.nextId();
		payLog.setOutTradeNo(outTradeNo+"");
		payLog.setCreateTime(new Date());
		payLog.setUserId(order.getUserId());
		payLog.setOrderList(orderList.toString());
		System.out.println(payLog.getOrderList());
//		未支付
		payLog.setTradeState("0");
		payLog.setTotalFee((long)(totalMoney*100));
		System.out.println(payLog.getTotalFee());
		payLog.setPayType("1");
		payLogMapper.insert(payLog);

 		redisTemplate.boundHashOps("cartList").delete(order.getUserId());

 		return payLog.getOutTradeNo();

	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Order order){
		orderMapper.updateByPrimaryKeySelective(order);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Order findById(String id){
		return orderMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(String[] ids) {
		for(String id:ids){
			orderMapper.deleteByPrimaryKey(id);
		}		
	}

	@Override
	public PageResult findBySellerIdAndStatus(int  pageNum,int pageSize,String sellerId, String status) {
		PageHelper.startPage(pageNum,pageSize);
		Page<Order> page =(Page<Order>) orderMapper.findBySellerIdAndStatus(sellerId, status);
		return  new PageResult(page.getTotal(),page.getResult());

	}


	@Override
	public PageResult findPage(int  pageNum,int pageSize, String userId, String status) {

		PageHelper.startPage(pageNum,pageSize);
		Page<GOrder> page =(Page<GOrder> ) orderMapper.findByUserName(userId, status);
		return  new PageResult(page.getTotal(),page.getResult());
	}

	@Override
	public void updateOrderStatus(String status, String tradeNo) {
		PayLog payLog = payLogMapper.selectByPrimaryKey(tradeNo);
		if (!"-1".equals(status)){
			String[] strings = payLog.getOrderList().split(",");
			for (String orderId : strings){
				Order order = orderMapper.selectByPrimaryKey(orderId);
				order.setStatus(status);
				order.setPaymentTime(new Date());
				orderMapper.updateByPrimaryKey(order);
			}
		}
        payLog.setPayTime(new Date());
		payLog.setTradeState(status);
		payLogMapper.updateByPrimaryKeySelective(payLog);
	}

	@Override
	public void updateOrder(String status, String orderId) {
		Order order = new Order();
		order.setStatus(status);
		order.setOrderId(orderId);
		System.out.println(order);
		orderMapper.updateByPrimaryKeySelective(order);
	}

}
