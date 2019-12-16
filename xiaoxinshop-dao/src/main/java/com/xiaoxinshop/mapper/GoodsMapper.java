package com.xiaoxinshop.mapper;

import com.xiaoxinshop.entity.Goods;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long id);

    List<Goods> find(Goods goods);

    List<Goods> findAll(Goods goods);

    int updateByPrimaryKeySelective(Goods record);



    int updateByPrimaryKey(Goods record);
}