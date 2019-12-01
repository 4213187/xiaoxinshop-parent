package com.xiaoxinshop.mapper;

import com.xiaoxinshop.entity.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Long id);

    List<Item> findByGoodsId(Long goodsId);

    List<Item> findAll();

    int deleteByGoodsId(Long goodsId);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);

    List<Item> findItemListByGoodsIdandStatus(@Param("goodsIds") List<Long> goodsIds, @Param("status") String status);
}