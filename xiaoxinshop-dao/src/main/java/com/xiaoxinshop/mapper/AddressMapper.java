package com.xiaoxinshop.mapper;

import com.xiaoxinshop.entity.Address;

import java.util.List;

public interface AddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    /**
     * 查询出默认地址
     * @return
     */
     Address findByIsDefault(String userId);

    /**
     * 根据用户查询地址
     * @param userId
     * @return
     */
    public List<Address> findListByUserId(String userId );

}