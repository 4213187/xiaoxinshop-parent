package com.xiaoxinshop.mapper;

import com.xiaoxinshop.entity.SpecificationOption;

public interface SpecificationOptionMapper {
    int deleteByPrimaryKey(Long id);

    int deleteBySpecId(Long specId);

    int insert(SpecificationOption record);

    int insertSelective(SpecificationOption record);

    SpecificationOption selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpecificationOption record);

    int updateByPrimaryKey(SpecificationOption record);
}