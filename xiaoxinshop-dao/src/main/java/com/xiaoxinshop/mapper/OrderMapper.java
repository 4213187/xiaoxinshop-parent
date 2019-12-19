package com.xiaoxinshop.mapper;

import com.xiaoxinshop.entity.GOrder;
import com.xiaoxinshop.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);

    List<GOrder> findByUserName(@Param(value = "userId") String userId, @Param(value = "status")String status);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}