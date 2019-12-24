package com.xiaoxinshop.order.service;
import java.util.List;
import java.util.Map;

import com.xiaoxinshop.entity.GOrder;
import com.xiaoxinshop.entity.Order;
import com.xiaoxinshop.entity.PageResult;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface OrderService {

	/**
	 * 查询该用户所有的订单
	 * @param userId
	 * @return
	 */
	List<GOrder> findByUserName(String userId,String status);
	/**
	 * 返回全部列表
	 * @return
	 */
	public List<Order> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public String add(Order order);
	
	
	/**
	 * 修改
	 */
	public void update(Order order);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public Order findById(String id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(String[] ids);

	PageResult findBySellerIdAndStatus(int  pageNum,int pageSize,String sellerId,String status);

	public PageResult findPage(int  pageNum,int pageSize, String userId, String status);


	public  void updateOrderStatus(String status,String tradeNo  );

	  void updateOrder(String status,String orderId  );
}
