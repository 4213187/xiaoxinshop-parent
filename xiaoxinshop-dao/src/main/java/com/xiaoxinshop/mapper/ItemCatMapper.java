package com.xiaoxinshop.mapper;

import com.xiaoxinshop.entity.ItemCat;

import java.util.List;

public interface ItemCatMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ItemCat record);

    int insertSelective(ItemCat record);

    ItemCat selectByPrimaryKey(Long id);

    List<ItemCat> findByParentId(Long parentId);

    List<ItemCat> findAll();

    int updateByPrimaryKeySelective(ItemCat record);

    int updateByPrimaryKey(ItemCat record);
}