package com.xiaoxinshop.service.impl;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.entity.SpecificationOption;
import com.xiaoxinshop.entity.TypeTemplate;
import com.xiaoxinshop.mapper.SpecificationOptionMapper;
import com.xiaoxinshop.mapper.TypeTemplateMapper;
import com.xiaoxinshop.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {

    @Autowired
    private TypeTemplateMapper typeTemplateMapper;

    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;

    /**
     * 查询全部
     */
    @Override
    public List<TypeTemplate> findAll() {
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
    public void add(TypeTemplate typeTemplate) {
        typeTemplateMapper.insert(typeTemplate);
    }


    /**
     * 修改
     */
    @Override
    public void update(TypeTemplate typeTemplate) {
        typeTemplateMapper.updateByPrimaryKey(typeTemplate);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TypeTemplate findById(Long id) {
        return typeTemplateMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            typeTemplateMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public PageResult findPage(TypeTemplate typeTemplate, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TypeTemplate> page = (Page<TypeTemplate>) typeTemplateMapper.findAll(typeTemplate);
        saveToRedis();
        return new PageResult(page.getTotal(),page.getResult());

    }

    @Override
    public List<Map> findTypeTemplates() {
        return typeTemplateMapper.findTypeTemplates();
    }

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 将数据存入缓存
     */
    private void saveToRedis(){
        //获取模板数据
        List<TypeTemplate> typeTemplateList = typeTemplateMapper.findAll(null);
        //循环模板
        for(TypeTemplate typeTemplate :typeTemplateList){
            //存储品牌列表
            List<Map> brandList = JSON.parseArray(typeTemplate.getBrandIds(), Map.class);
            redisTemplate.boundHashOps("brandList").put(typeTemplate.getId(), brandList);

            //存储规格列表
            //根据模板ID查询规格列表
            List<Map> specList = findSpecList(typeTemplate.getId());
            redisTemplate.boundHashOps("specList").put(typeTemplate.getId(), specList);
        }
        System.out.println("品牌 规格 放入缓存");
    }



    @Override
    public List<Map> findSpecList(Long id) {
        TypeTemplate typeTemplate = typeTemplateMapper.selectByPrimaryKey(id);

        List<Map> maps = JSON.parseArray(typeTemplate.getSpecIds(), Map.class);
        for (Map map: maps  ) {

            List<SpecificationOption> options = specificationOptionMapper.findBySpecId(new Long ((Integer) map.get("id")));
            System.out.println(options);
            map.put("options",options);
        }
        return maps;
    }

}
