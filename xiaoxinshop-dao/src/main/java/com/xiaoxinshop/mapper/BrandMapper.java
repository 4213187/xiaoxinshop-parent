package com.xiaoxinshop.mapper;


import com.xiaoxinshop.entity.Brand;

import java.util.List;
import java.util.Map;

public interface BrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Brand record);

    int insertSelective(Brand record);

    Brand selectByPrimaryKey(Long id);

    List<Brand> findAll();

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);

    List<Brand> find(Brand brand);

    List<Map>  findBrands();

}