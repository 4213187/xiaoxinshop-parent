package com.xiaoxinshop.mapper;

import com.xiaoxinshop.entity.TypeTemplate;

import java.util.List;
import java.util.Map;

public interface TypeTemplateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TypeTemplate record);

    int insertSelective(TypeTemplate record);

    TypeTemplate selectByPrimaryKey(Long id);

    List<TypeTemplate> findAll(TypeTemplate typeTemplate);

    List<Map> findTypeTemplates();

    int updateByPrimaryKeySelective(TypeTemplate record);

    int updateByPrimaryKey(TypeTemplate record);


}