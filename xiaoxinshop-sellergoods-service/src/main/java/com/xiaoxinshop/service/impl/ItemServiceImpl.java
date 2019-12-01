package com.xiaoxinshop.service.impl;
import java.util.Arrays;
import java.util.List;

import com.xiaoxinshop.entity.Item;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.mapper.ItemMapper;
import com.xiaoxinshop.service.ItemService;
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
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<Item> findAll() {
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
	public void add(Item item) {
		itemMapper.insert(item);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Item item){
		itemMapper.updateByPrimaryKey(item);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Item findById(Long id){
		return itemMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			itemMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(Item item, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		return null;
	}

	/**
	 * 根据商品id 查询所有的sku
	 * @param goodsIds
	 * @param status
	 * @return
	 */
	@Override
	public List<Item> findItemListByGoodsIdandStatus(Long[] goodsIds, String status) {

		System.out.println("findItemListByGoodsIdandStatus");
		return itemMapper.findItemListByGoodsIdandStatus(Arrays.asList(goodsIds),status);
	}

}
