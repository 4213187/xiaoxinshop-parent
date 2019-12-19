package com.xiaoxinshop.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaoxinshop.entity.GOrder;
import com.xiaoxinshop.entity.Order;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.entity.ResultVo;
import com.xiaoxinshop.order.service.OrderService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*",maxAge = 3600)
public class OrderController {

	@Reference(timeout = 3600)
	private OrderService orderService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<Order> findAll(){
		return orderService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows){
		return orderService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public ResultVo add(@RequestBody Order order){

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		order.setUserId(username);
		order.setSourceType("2");

		try {
			System.out.println(order.getUserId()+"add");
			String  outTradeNo= orderService.add(order);
			return new ResultVo(true, outTradeNo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param order
	 * @return
	 */
	@RequestMapping("/update")
	public ResultVo update(@RequestBody Order order){
		try {
			orderService.update(order);
			return new ResultVo(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findById")
	public Order findOne(String id){
		return orderService.findById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public ResultVo delete(String [] ids){
		try {
			orderService.delete(ids);
			return new ResultVo(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "删除失败");
		}
	}

	@RequestMapping(value = "/search" )
	public PageResult search(String status ,int pageNum , int pageSize  ){
		//得到登陆人账号
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("pageNum:"+pageNum);
		System.out.println("pageSize:"+pageSize);
		if ("-1".equals(status)){
			status =null;
		}
		return orderService.findPage(pageNum,pageSize, userId,status);
	}


	@RequestMapping("/updateOrderStatus")
	public ResultVo updateOrderStatus(String status,String tradeNo  ){
		try {
			orderService.updateOrderStatus(status, tradeNo);
			return new ResultVo(true, "成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "失败");
		}
	}
	@RequestMapping("/findByUserName")
	public List<GOrder> findByUserName(){
		//得到登陆人账号
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		List<GOrder> orderList = orderService.findByUserName(userId,null);
		System.out.println(orderList.size());
		for (GOrder order:orderList
			 ) {
			System.out.println(order.getOrderId());

		}

		return  orderList;
	}

	@RequestMapping(value = "/findByStatusAndUserName")
	public List<GOrder> findByStatusAndUserName(String status){
		//得到登陆人账号
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		List<GOrder> orderList = orderService.findByUserName(userId,status);
		System.out.println(orderList.size());
		for (GOrder order:orderList
		) {
			System.out.println("status:" + status);
			System.out.println(order.getOrderId());

		}

		return  orderList;
	}

	@RequestMapping("/updateOrder")
	public ResultVo updateOrder(String status,String orderId  ){
		try {
			System.out.println(status+"  "+orderId);
			orderService.updateOrder(status, orderId);
			return new ResultVo(true, "成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "失败");
		}
	}



	
}
