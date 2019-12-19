package com.xiaoxinshop.user.service;
import java.util.List;

import com.xiaoxinshop.entity.Address;
import com.xiaoxinshop.entity.PageResult;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface AddressService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<Address> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(Address address);
	
	
	/**
	 * 修改
	 */
	public void update(Address address);


	/**
	 * 修改默认地址
	 */
	public void updateIsDefault(Long id,String userId);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public Address findById(Long id);

	/**
	 * 查询出默认地址
	 * @return
	 */
	public Address findByIsDefault(String userId);



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
	public PageResult findPage(Address address, int pageNum, int pageSize);

	/**
	 * 根据用户查询地址
	 * @param userId
	 * @return
	 */
	public List<Address> findListByUserId(String userId );


}
