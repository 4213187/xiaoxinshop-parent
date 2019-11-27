package com.xiaoxinshop.content.service.impl;
import java.util.List;

import com.xiaoxinshop.content.service.ContentService;
import com.xiaoxinshop.entity.Content;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.mapper.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentMapper contentMapper;

	@Autowired
	private  RedisTemplate redisTemplate;

	/**
	 * 查询全部
	 */
	@Override
	public List<Content> findAll() {
		return null;
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {

		return null;
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Content content)
	{
		contentMapper.insert(content);
		redisTemplate.boundHashOps("content").delete(content.getCategoryId());
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Content content){

		contentMapper.updateByPrimaryKey(content);
		redisTemplate.boundHashOps("content").delete(content.getCategoryId());
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Content findById(Long id){
		return contentMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			Long categoryId = contentMapper.selectByPrimaryKey(id).getCategoryId();//广告分类ID
			redisTemplate.boundHashOps("content").delete(categoryId);

			contentMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(Content content, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
        Page<Content> page = (Page<Content> )  contentMapper.find(content);

		return new PageResult(page.getTotal(),page.getResult());
	}

	@Override
	public List<Content> findByCategoryId(Long categoryId) {
		List<Content> list = (List<Content>)redisTemplate.boundHashOps("content").get(categoryId);
		if (list ==null){
			list = contentMapper.findByCategoryId(categoryId);
			redisTemplate.boundHashOps("content").put(categoryId,list);
			System.out.println("从数据库中读取");
		}else {
			System.out.println("从缓存中读取");
		}

		return  list;

	}

}
