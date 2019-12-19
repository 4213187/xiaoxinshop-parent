package com.xiaoxinshop.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaoxinshop.entity.Manager;
import com.xiaoxinshop.entity.ResultVo;
import com.xiaoxinshop.entity.Seller;
import com.xiaoxinshop.service.ManagerService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @CrossOrigin(origins = "http://localhost:63343" ,allowCredentials = "true")
    public ResultVo login(@RequestBody Manager manager, HttpServletRequest request) {
        System.out.println("login");
        try {
            Manager manager1 = managerService.find(manager);
            if (manager1 != null) {
                HttpSession session = request.getSession();
                session.setAttribute("manager",manager1);
                return new ResultVo(true, "登陆成功");
            }else {
                return new ResultVo(false, "登陆失败");
            }



        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVo(false, "登陆失败");
        }

    }

    @CrossOrigin(origins = "http://localhost:63343" ,allowCredentials = "true")
    @RequestMapping(value = "/check")
    public  ResultVo check(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Manager manager =(Manager) session.getAttribute("manager");
        if (manager!=null){

            return  new ResultVo(true,manager.getName()) ;
        }else {
            return  new ResultVo(false,"未登录") ;
        }
    }

    @CrossOrigin(origins = "http://localhost:63343" ,allowCredentials = "true")
    @RequestMapping(value = "/logout")
    public  ResultVo logout(HttpServletRequest httpServletRequest){
        System.out.println("logout");
        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute("manager");
        Manager manager = (Manager) session.getAttribute("manager");
        if (manager!=null){

            return  new ResultVo(false,"退出失败") ;
        }else {
            return  new ResultVo(true,"退出成功") ;
        }
    }



    @RequestMapping(value = "/Tcode")
    public ResultVo tCode(String ticket, String randstr) {

        DescribeCaptchaResult describeCaptchaResult = new DescribeCaptchaResult();

        if (describeCaptchaResult.codeResponse(ticket,randstr)){
            System.out.println("验证成功");
            return new ResultVo(true,"验证成功");
        }else{
            return new ResultVo(false,"验证失败");
        }

    }




}
