package com.xiaoxinshop.order.service.impl;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xiaoxinshop.entity.Cart;
import com.xiaoxinshop.entity.Order;
import com.xiaoxinshop.entity.OrderItem;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.mapper.OrderItemMapper;
import com.xiaoxinshop.mapper.OrderMapper;
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
	/**
	 * 增加
	 */
	@Override
	public void add(Order order) {
//		从redis中获取对应购物车列表
		List<Cart> cartList = (List<Cart>)redisTemplate.boundHashOps("cartList").get(order.getUserId());
		System.out.println("cartList"+cartList);
		for (Cart cart: cartList ) {

			Order newOrder = new Order();
			//设置订单id
        	Long orderId =idWorker.nextId();
			newOrder.setOrderId(orderId);
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


		}
		redisTemplate.boundHashOps("cartList").delete(order.getUserId());

	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Order order){
		orderMapper.updateByPrimaryKey(order);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Order findById(Long id){
		return orderMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			orderMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(Order order, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return  null;
	}
	
}
