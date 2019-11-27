package com.xiaoxinshop.mapper;

import com.xiaoxinshop.entity.Content;

import java.util.List;

public interface ContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Content record);

    int insertSelective(Content record);

    Content selectByPrimaryKey(Long id);

    List<Content> find(Content content);

    List<Content> findByCategoryId(Long categoryId);


    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKey(Content record);
}