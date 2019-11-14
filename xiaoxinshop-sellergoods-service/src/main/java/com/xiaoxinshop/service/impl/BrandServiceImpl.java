package com.xiaoxinshop.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoxinshop.entity.Brand;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.mapper.BrandMapper;
import com.xiaoxinshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author 小浩
 * @Date 2019/11/11 22:59
 * @Version 1.0
 **/
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {

        return brandMapper.findAll();
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Brand> page =(Page<Brand> ) brandMapper.findAll();

        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public PageResult findPage(Brand brand, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Brand> page =(Page<Brand> ) brandMapper.find(brand);
        System.out.println("service");
        System.out.println(brand);

        System.out.println(page.getResult());
        return new PageResult(page.getTotal(),page.getResult());

    }

    @Override
    public void add(Brand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public void update(Brand brand) {

        System.out.println("service"+brand);
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override

    public Brand findById(int id) {
        return brandMapper.selectByPrimaryKey((long) id);
    }

    @Override
    public void delete(int[] ids) {
        for (int id:ids) {
           brandMapper.deleteByPrimaryKey((long) id);
        }
    }
}
