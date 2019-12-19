package com.xiaoxinshop.user.service;

import com.xiaoxinshop.entity.Area;
import com.xiaoxinshop.entity.City;
import com.xiaoxinshop.entity.Province;

import java.util.List;

/**
 * @Author 小浩
 * @Date 2019/12/14 21:59
 * @Version 1.0
 **/
public interface AddrService {
    /**
     * 查询所有的省份
     * @return
     */
    List<Province> findAllProvince();


    /**
     * 查询所有的城市
     * @return
     */
    List<City> findAllCities();


    /**
     * 查询所有的区县
     * @return
     */
    List<Area> findAllAreas();

    /**
     * 根据id 查询省份
     * @param id
     * @return
     */
    Province findProvinceById(Integer id);

    /**
     * 根据id查询市
     * @param id
     * @return
     */
    City findCityById(Integer id);

    /**
     * 根据id查询县
     * @param id
     * @return
     */
    Area findAreaById(Integer id);

    /**
     * 根据省份id 查询省份下所有的市
     * @param provinceId
     * @return
     */
    List<City> findCitiesByProvinceId(String provinceId);

    /**
     * 根据市id 查询市 下边所有的区和县
     * @param cityId
     * @return
     */
    List<Area> findAreasByCityId(String cityId);



}
