package com.xiaoxinshop.service.impl;

import java.util.List;

import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.entity.Seller;
import com.xiaoxinshop.mapper.SellerMapper;
import com.xiaoxinshop.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerMapper sellerMapper;

    /**
     * 查询全部
     */
    @Override
    public List<Seller> findAll() {
        return null;
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {

        return null;
    }

    /**
     * 增加
     */
    @Override
    public void add(Seller seller) {
        sellerMapper.insert(seller);
    }


    /**
     * 修改
     */
    @Override
    public void update(Seller seller) {
        sellerMapper.updateByPrimaryKey(seller);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public Seller findById(String id) {
        return sellerMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            sellerMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public PageResult findPage(Seller seller, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Page<Seller> page = (Page<Seller>) sellerMapper.find(seller);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public int updateStatus(Seller seller) {
        return sellerMapper.updateStatus(seller);
    }

    @Override
    public Seller findLogin(Seller seller) {
        return sellerMapper.findLogin(seller);
    }

}
