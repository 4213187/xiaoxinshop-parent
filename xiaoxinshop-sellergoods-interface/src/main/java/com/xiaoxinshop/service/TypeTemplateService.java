package com.xiaoxinshop.service;
import java.util.List;
import java.util.Map;

import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.entity.TypeTemplate;


/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface TypeTemplateService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TypeTemplate> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TypeTemplate typeTemplate);
	
	
	/**
	 * 修改
	 */
	public void update(TypeTemplate typeTemplate);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TypeTemplate findById(Long id);
	
	
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
	public PageResult findPage(TypeTemplate typeTemplate, int pageNum, int pageSize);

	/**
	 * 返回所有模板 map
	 * @return
	 */
	List<Map> findTypeTemplates();

	/**
	 * 返回模板对应的所有规格
	 * @return
	 */
	List<Map> findSpecList(Long id);
	
}
