package com.xiaoxinshop.service.impl;

import java.util.List;

import com.xiaoxinshop.entity.GSpecification;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.entity.Specification;
import com.xiaoxinshop.mapper.SpecificationMapper;
import com.xiaoxinshop.mapper.SpecificationOptionMapper;
import com.xiaoxinshop.service.SpecificationService;
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
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;

    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;

    /**
     * 查询全部
     */
    @Override
    public List<Specification> findAll() {

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
    public void add(GSpecification specification) {

        if (specificationMapper.insert(specification.getSpecification()) > 0) {
            Specification successfulspecification = specificationMapper.findByName(specification.getSpecification().getSpecName());
            for (int i = 0; i < specification.getSpecificationOptionList().size(); i++) {

                specification.getSpecificationOptionList().get(i).setSpecId(successfulspecification.getId());
                specificationOptionMapper.insert(specification.getSpecificationOptionList().get(i));
            }
        }

    }


    /**
     * 修改
     */
    @Override
    public void update(GSpecification specification) {
        specificationMapper.updateByPrimaryKey(specification.getSpecification());
        specificationOptionMapper.deleteBySpecId(specification.getSpecification().getId());
        for (int i = 0; i < specification.getSpecificationOptionList().size(); i++) {

            specification.getSpecificationOptionList().get(i).setSpecId(specification.getSpecification().getId());
            specificationOptionMapper.insert(specification.getSpecificationOptionList().get(i));
        }


    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public GSpecification findById(Long id) {
        return specificationMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            specificationOptionMapper.deleteBySpecId(id);
            specificationMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public PageResult findPage(Specification specification, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Specification> page = (Page<Specification>) specificationMapper.find(specification);
        return new PageResult(page.getTotal(), page.getResult());

    }

}
