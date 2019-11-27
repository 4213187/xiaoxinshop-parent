package com.xiaoxinshop.content.service.impl;
import java.util.List;

import com.xiaoxinshop.content.service.ContentCategoryService;
import com.xiaoxinshop.entity.ContentCategory;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.mapper.ContentCategoryMapper;
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
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private ContentCategoryMapper contentCategoryMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<ContentCategory> findAll() {

		return contentCategoryMapper.findAll();
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
	public void add(ContentCategory contentCategory) {
		contentCategoryMapper.insert(contentCategory);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(ContentCategory contentCategory){
		contentCategoryMapper.updateByPrimaryKey(contentCategory);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public ContentCategory findById(Long id){
		return contentCategoryMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			contentCategoryMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(ContentCategory contentCategory, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<ContentCategory> page = (Page<ContentCategory> ) contentCategoryMapper.find(contentCategory);

		return new PageResult(page.getTotal(),page.getResult());
	}
	
}
