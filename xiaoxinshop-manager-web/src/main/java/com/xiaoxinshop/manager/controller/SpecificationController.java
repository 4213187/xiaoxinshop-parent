package com.xiaoxinshop.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaoxinshop.entity.GSpecification;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.entity.ResultVo;
import com.xiaoxinshop.entity.Specification;
import com.xiaoxinshop.service.SpecificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/specification")
@CrossOrigin(origins = "*", maxAge=3600)
public class SpecificationController {

	@Reference
	private SpecificationService specificationService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<Specification> findAll(){
		return specificationService.findAll();
	}
	
	

	
	/**
	 * 增加
	 * @param specification
	 * @return
	 */
	@RequestMapping(value = "/add",method= RequestMethod.POST)
	public ResultVo add(@RequestBody GSpecification specification){
		try {
		    specificationService.add(specification);
			return new ResultVo(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param specification
	 * @return
	 */
	@RequestMapping(value = "/update" ,method = RequestMethod.POST)
	public ResultVo update(@RequestBody GSpecification specification){
		try {
			specificationService.update(specification);
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
	public GSpecification findById(Long id){
		return specificationService.findById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete",method= RequestMethod.POST)
	public ResultVo delete(@RequestBody  Long [] ids){
		try {
			specificationService.delete(ids);
			return new ResultVo(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "删除失败");
		}
	}

	/**
	 * 分页查询
	 * @param specification
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/find",method= RequestMethod.POST)
	public PageResult find(@RequestBody Specification specification, int pageNum, int pageSize  ){
		return specificationService.findPage(specification, pageNum, pageSize);
	}


	@RequestMapping("/findSpecifications")
	public List<Map> findSpecifications(Long id){
		return specificationService.findSpecifications();
	}


}
