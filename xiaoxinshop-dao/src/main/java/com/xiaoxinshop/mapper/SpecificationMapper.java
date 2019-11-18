package com.xiaoxinshop.mapper;

import com.xiaoxinshop.entity.GSpecification;
import com.xiaoxinshop.entity.Specification;

import java.util.List;
import java.util.Map;

public interface SpecificationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Specification record);

    int insertSelective(Specification record);


    GSpecification selectByPrimaryKey(Long id);

    Specification findByName(String specName);

    int updateByPrimaryKeySelective(Specification record);

    int updateByPrimaryKey(Specification record);

    List<Map> findSpecifications();

    List<Specification> find(Specification specification);
}