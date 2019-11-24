package com.xiaoxinshop.mapper;

import com.xiaoxinshop.entity.SpecificationOption;

import java.util.List;

public interface SpecificationOptionMapper {
    int deleteByPrimaryKey(Long id);

    int deleteBySpecId(Long specId);

    int insert(SpecificationOption record);

    int insertSelective(SpecificationOption record);

    SpecificationOption selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpecificationOption record);

    List<SpecificationOption> findBySpecId(Long specId);

    int updateByPrimaryKey(SpecificationOption record);
}