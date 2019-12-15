package com.xiaoxinshop.mapper;

import com.xiaoxinshop.entity.Province;

import java.util.List;

public interface ProvinceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Province record);

    int insertSelective(Province record);

    Province selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Province record);

    int updateByPrimaryKey(Province record);


    /**
     * 查询所有的省份
     * @return
     */
    List<Province> findAllProvince();
}