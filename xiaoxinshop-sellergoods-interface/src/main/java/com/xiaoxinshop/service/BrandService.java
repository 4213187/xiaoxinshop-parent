package com.xiaoxinshop.service;

import com.xiaoxinshop.entity.Brand;
import com.xiaoxinshop.entity.PageResult;


import java.util.List;
import java.util.Map;

/**
 * @Author 小浩
 * @Date 2019/11/11 22:57
 * @Version 1.0
 **/
public interface BrandService {
    public List<Brand> findAll();

    /**
     * 分页查询
     * @param pageNum 第几页
     * @param pageSize 数量
     * @return 分页后的结果
     */
    public PageResult findPage(int pageNum, int pageSize);

    /**
     * 通过条件分页查询
     * @param brand  条件
     * @param pageNum 第几页
     * @param pageSize 大小
     * @return
     */
    public PageResult findPage(Brand brand,int pageNum, int pageSize);

    /**
     * 添加品牌
     * @param brand 品牌
     */
    public void add(Brand brand);

    /**
     * 更新品牌
     * @param brand 品牌
     */
    public void update(Brand brand);

    /**
     * 通过id查询品牌
     * @param id  品牌id
     * @return 返回对应的品牌
     */
    public Brand findById(int id);


    /**
     * 通过id集合删除对应品牌
     * @param ids  id集合
     */
    public void  delete(int[] ids);

    /**
     * 查询所有品牌 返回map
     * @return
     */
    List<Map>  findBrands();




}
