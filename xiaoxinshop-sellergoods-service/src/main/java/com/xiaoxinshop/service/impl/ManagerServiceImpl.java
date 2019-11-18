package com.xiaoxinshop.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaoxinshop.entity.Manager;
import com.xiaoxinshop.mapper.ManagerMapper;
import com.xiaoxinshop.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author 小浩
 * @Date 2019/11/17 11:37
 * @Version 1.0
 **/
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerMapper managerMapper;
    @Override
    public Manager find(Manager manager) {
        return managerMapper.find(manager);
    }
}
