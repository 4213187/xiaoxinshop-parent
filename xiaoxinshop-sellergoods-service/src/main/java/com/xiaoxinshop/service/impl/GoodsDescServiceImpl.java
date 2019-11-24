package com.xiaoxinshop.service.impl;
import java.util.List;

import com.xiaoxinshop.entity.GoodsDesc;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.mapper.GoodsDescMapper;
import com.xiaoxinshop.service.GoodsDescService;
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
public class GoodsDescServiceImpl implements GoodsDescService {

	@Autowired
	private GoodsDescMapper goodsDescMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<GoodsDesc> findAll() {
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
	public void add(GoodsDesc goodsDesc) {
		goodsDescMapper.insert(goodsDesc);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(GoodsDesc goodsDesc){
		goodsDescMapper.updateByPrimaryKey(goodsDesc);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public GoodsDesc findById(Long id){
		return goodsDescMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			goodsDescMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(GoodsDesc goodsDesc, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		

		return null;
	}
	
}
