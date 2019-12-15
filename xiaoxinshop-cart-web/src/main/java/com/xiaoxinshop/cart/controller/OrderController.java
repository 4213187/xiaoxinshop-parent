package com.xiaoxinshop.cart.controller;
import java.util.List;

import com.xiaoxinshop.entity.Order;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.entity.ResultVo;
import com.xiaoxinshop.order.service.OrderService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/order")
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
	public Order findOne(Long id){
		return orderService.findById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public ResultVo delete(Long [] ids){
		try {
			orderService.delete(ids);
			return new ResultVo(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "删除失败");
		}
	}

	/**
	 * 分页
	 * @param order
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody Order order, int page, int rows  ){
		return orderService.findPage(order, page, rows);		
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
	
}
