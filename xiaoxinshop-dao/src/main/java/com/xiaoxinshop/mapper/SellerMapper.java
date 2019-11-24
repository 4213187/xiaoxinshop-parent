package com.xiaoxinshop.mapper;

import com.xiaoxinshop.entity.Seller;

import java.util.List;

public interface SellerMapper {
    int deleteByPrimaryKey(String sellerId);

    int insert(Seller record);

    int insertSelective(Seller record);

    Seller selectByPrimaryKey(String sellerId);

    List<Seller> find(Seller seller);

    int updateStatus(Seller seller);

    Seller findLogin(Seller seller);

    int updateByPrimaryKeySelective(Seller record);

    int updateByPrimaryKey(Seller record);
}