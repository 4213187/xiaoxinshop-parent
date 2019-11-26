package com.xiaoxinshop.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.entity.ResultVo;
import com.xiaoxinshop.entity.TypeTemplate;
import com.xiaoxinshop.service.TypeTemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/typeTemplate")
@CrossOrigin(origins = "*", maxAge=3600)
public class TypeTemplateController {

	@Reference
	private TypeTemplateService typeTemplateService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TypeTemplate> findAll(){
		return typeTemplateService.findAll();
	}
	
	

	/**
	 * 增加
	 * @param typeTemplate
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public ResultVo add(@RequestBody TypeTemplate typeTemplate){
		try {
			System.out.println("add");
			typeTemplateService.add(typeTemplate);
			return new ResultVo(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param typeTemplate
	 * @return
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public ResultVo update(@RequestBody TypeTemplate typeTemplate){
		try {
			typeTemplateService.update(typeTemplate);
			return new ResultVo(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findById")
	public TypeTemplate findOne(Long id){
		return typeTemplateService.findById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public ResultVo delete(@RequestBody Long [] ids){
		try {
			typeTemplateService.delete(ids);
			return new ResultVo(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "删除失败");
		}
	}
	

	@RequestMapping(value = "/find",method = RequestMethod.POST)
	public PageResult search(@RequestBody TypeTemplate typeTemplate, int pageNum, int pageSize  ){
		return typeTemplateService.findPage(typeTemplate, pageNum, pageSize);
	}

	@RequestMapping(value = "/findTypeTemplates")
	public List<Map> findTypeTemplates(){
		return  typeTemplateService.findTypeTemplates();
	}

	@RequestMapping(value = "/findSpecList")
	public List<Map> findSpecList(Long id){
		return  typeTemplateService.findSpecList(id);
	}

	
}
