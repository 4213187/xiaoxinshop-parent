package com.xiaoxinshop.service;
import java.util.List;

import com.xiaoxinshop.entity.GSpecification;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.entity.Specification;



/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface SpecificationService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<Specification> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(GSpecification specification);
	
	
	/**
	 * 修改
	 */
	public void update(GSpecification specification);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public GSpecification findById(Long id);
	
	
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
	public PageResult findPage(Specification specification, int pageNum, int pageSize);
	
}
