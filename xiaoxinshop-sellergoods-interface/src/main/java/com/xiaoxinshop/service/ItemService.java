package com.xiaoxinshop.service;
import java.util.List;

import com.xiaoxinshop.entity.Item;
import com.xiaoxinshop.entity.PageResult;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface ItemService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<Item> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(Item item);
	
	
	/**
	 * 修改
	 */
	public void update(Item item);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public Item findById(Long id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long[] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(Item item, int pageNum, int pageSize);


	public List<Item> findItemListByGoodsIdandStatus(Long[] goodsIds, String status );
	
}