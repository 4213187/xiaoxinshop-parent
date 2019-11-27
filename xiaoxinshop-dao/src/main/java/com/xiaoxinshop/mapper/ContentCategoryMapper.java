package com.xiaoxinshop.mapper;

import com.xiaoxinshop.entity.ContentCategory;

import java.util.List;

public interface ContentCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ContentCategory record);

    int insertSelective(ContentCategory record);

    ContentCategory selectByPrimaryKey(Long id);

    List<ContentCategory> find(ContentCategory contentCategory);

    List<ContentCategory> findAll();

    int updateByPrimaryKeySelective(ContentCategory record);

    int updateByPrimaryKey(ContentCategory record);
}