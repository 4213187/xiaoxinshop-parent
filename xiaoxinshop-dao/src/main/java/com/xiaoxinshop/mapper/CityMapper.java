package com.xiaoxinshop.mapper;

import com.xiaoxinshop.entity.City;

import java.util.List;

public interface CityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);

    /**
     * 根据省份id 查询省份下所有的市
     * @param provinceId
     * @return
     */
    List<City> findCitiesByProvinceId(String provinceId);


    /**
     * 查询所有的城市
     * @return
     */
    List<City> findAllCities();
}