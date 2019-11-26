package com.xiaoxinshop.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaoxinshop.entity.Brand;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.entity.ResultVo;
import com.xiaoxinshop.service.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author 小浩
 * @Date 2019/11/11 23:06
 * @Version 1.0
 **/
@CrossOrigin(origins = "*", maxAge=3600)
@RestController
@RequestMapping("/brand")
public class BrandController {
    @Reference
    private BrandService brandService;


    @RequestMapping("/findAll")
    public List<Brand> finAll(){
       return brandService.findAll();
    }


    @RequestMapping("/findPage")
    public PageResult findPage(int pageNum,int pageSize){
       return brandService.findPage(pageNum,pageSize);
    }

    @RequestMapping(value = "/find",method= RequestMethod.POST)
    public PageResult find(@RequestBody Brand brand,int pageNum,int pageSize) {
        System.out.println("controller");
        return brandService.findPage(brand, pageNum, pageSize);
    }


    @RequestMapping(value = "/add",method= RequestMethod.POST)
    public ResultVo add( @RequestBody Brand brand){
        try {
            System.out.println(brand);
            brandService.add(brand);
            return  new ResultVo(true,"添加成功");
        }catch (Exception e){
            return  new ResultVo(false,"添加失败");
        }

    }

    @RequestMapping(value = "/update",method= RequestMethod.POST)
    public ResultVo update(@RequestBody Brand brand){

        try {
            System.out.println("update"+brand);
            brandService.update(brand);
            System.out.println("update");
            return  new ResultVo(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return  new ResultVo(true,"修改失败");
        }
    }

    @RequestMapping(value = "/findById")
    public Brand findById(int id){
         return  brandService.findById(id);
    }


    @RequestMapping(value = "/findBrands")
    public List<Map> findBrands(){
        return  brandService.findBrands();
    }


    @RequestMapping(value = "/delete",method= RequestMethod.POST)
    public ResultVo delete(@RequestBody int[] ids){
        try {
            brandService.delete(ids);
            return  new ResultVo(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return  new ResultVo(false,"删除失败");
        }

    }
}
