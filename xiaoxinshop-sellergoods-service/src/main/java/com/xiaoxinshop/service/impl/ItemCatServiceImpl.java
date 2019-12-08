package com.xiaoxinshop.service.impl;

import java.util.List;

import com.xiaoxinshop.entity.ItemCat;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.mapper.ItemCatMapper;
import com.xiaoxinshop.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;

    /**
     * 查询全部
     */
    @Override
    public List<ItemCat> findAll() {
        return itemCatMapper.findAll();
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
    public void add(ItemCat itemCat) {

        itemCatMapper.insert(itemCat);

        redisTemplate.boundHashOps("itemCat").put(itemCat.getName(), itemCat.getTypeId());
        System.out.println("模板放入缓存");
    }


    /**
     * 修改
     */
    @Override
    public void update(ItemCat itemCat) {
        itemCatMapper.updateByPrimaryKey(itemCat);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public ItemCat findById(Long id) {
        return itemCatMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            itemCatMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public PageResult findPage(ItemCat itemCat, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        return null;
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<ItemCat> findByParentId(Long parentId) {

        List<ItemCat> itemCats = itemCatMapper.findAll();


        List<ItemCat> itemCatList = itemCatMapper.findByParentId(parentId);
        System.out.println(itemCatList);
        return itemCatList;
    }

}
