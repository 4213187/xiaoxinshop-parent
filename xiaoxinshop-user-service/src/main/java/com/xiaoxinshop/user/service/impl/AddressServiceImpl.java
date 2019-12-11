package com.xiaoxinshop.user.service.impl;
import java.util.List;

import com.xiaoxinshop.entity.Address;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.mapper.AddressMapper;
import com.xiaoxinshop.user.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;



/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressMapper addressMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<Address> findAll() {
		return null;
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		return null;
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Address address) {
		addressMapper.insert(address);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Address address){
		addressMapper.updateByPrimaryKey(address);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Address findById(Long id){
		return addressMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			addressMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(Address address, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		return null;
	}

	@Override
	public List<Address> findListByUserId(String userId) {
		return addressMapper.findListByUserId(userId);
	}

}
