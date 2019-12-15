package com.xiaoxinshop.mapper;

import com.xiaoxinshop.entity.Area;

import java.util.List;

public interface AreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

    /**
     * 根据市id 查询市 下边所有的区和县
     * @param cityId
     * @return
     */
    List<Area> findAreasByCityId(String cityId);

    /**
     * 查询所有的区和县
     * @return
     */
    List<Area> findAllAreas();

}