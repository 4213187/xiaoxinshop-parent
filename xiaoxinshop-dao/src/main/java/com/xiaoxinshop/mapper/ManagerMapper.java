package com.xiaoxinshop.mapper;

import com.xiaoxinshop.entity.Manager;

public interface ManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(Integer id);

    Manager find(Manager manager);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);
}