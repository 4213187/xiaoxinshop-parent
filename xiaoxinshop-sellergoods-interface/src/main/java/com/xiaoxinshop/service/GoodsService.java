package com.xiaoxinshop.service;
import com.xiaoxinshop.entity.GGoods;
import com.xiaoxinshop.entity.Goods;
import com.xiaoxinshop.entity.PageResult;

import java.util.List;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface GoodsService {


	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(GGoods goods);
	
	
	/**
	 * 修改
	 */
	public void update(GGoods goods);

	public void updateStatus(Long[] ids,String status);

	public  void updateMarketableStatus(Long[] ids,String status);

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public GGoods findById(Long id);
	
	
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
	public PageResult findPage(Goods goods, int pageNum, int pageSize);
	
}
