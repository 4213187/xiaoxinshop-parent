package com.xiaoxinshop.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaoxinshop.entity.Area;
import com.xiaoxinshop.entity.City;
import com.xiaoxinshop.entity.Province;
import com.xiaoxinshop.user.service.AddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 小浩
 * @Date 2019/12/14 22:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/addr")
public class AddrController {
    @Reference
    private AddrService addrService;


    @RequestMapping("/findAllProvince")
    public List<Province> findAll(){
        return addrService.findAllProvince();
    }

    @RequestMapping("/findCitiesByProvinceId")
    public List<City> findCitiesByProvinceId(String provinceId) {
        return addrService.findCitiesByProvinceId(provinceId);
    }

    @RequestMapping("/findAreasByCityId")
    public List<Area> findAreasByCityId(String cityId) {
        return addrService.findAreasByCityId(cityId);
    }



    @RequestMapping("/findAllCities")
    public List<City> findAllCities(){
        System.out.println("City");
        return  addrService.findAllCities();
    }

    @RequestMapping("/findAllAreas")
    public List<Area> findAllAreas(){
        System.out.println("Area");
        return  addrService.findAllAreas();
    }



}
