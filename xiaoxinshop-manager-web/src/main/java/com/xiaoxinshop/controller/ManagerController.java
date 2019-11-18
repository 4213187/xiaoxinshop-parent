package com.xiaoxinshop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaoxinshop.entity.Brand;
import com.xiaoxinshop.entity.Manager;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.entity.ResultVo;
import com.xiaoxinshop.service.ManagerService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author 小浩
 * @Date 2019/11/16 19:16
 * @Version 1.0
 **/
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Reference
    ManagerService managerService;



    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultVo login(@RequestBody Manager manager, HttpServletRequest request) {
        System.out.println("login");
        try {
            Manager manager1 = managerService.find(manager);
            if (manager1 != null) {
                HttpSession session = request.getSession();
                session.setAttribute("manager",manager);

                return new ResultVo(true, manager.getName());
            }else {
                return new ResultVo(false, "登陆失败");
            }



        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVo(false, "登陆失败");
        }

    }


}
