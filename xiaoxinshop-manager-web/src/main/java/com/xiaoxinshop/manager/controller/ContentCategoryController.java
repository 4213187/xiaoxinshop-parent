package com.xiaoxinshop.manager.controller;
import java.util.List;

import com.xiaoxinshop.content.service.ContentCategoryService;
import com.xiaoxinshop.entity.ContentCategory;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.entity.ResultVo;
import org.springframework.web.bind.annotation.*;
import com.alibaba.dubbo.config.annotation.Reference;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/contentCategory")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ContentCategoryController {

	@Reference
	private ContentCategoryService contentCategoryService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<ContentCategory> findAll(){
		return contentCategoryService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows){
		return contentCategoryService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param contentCategory
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public ResultVo add(@RequestBody ContentCategory contentCategory){
		try {
			contentCategoryService.add(contentCategory);
			return new ResultVo(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param contentCategory
	 * @return
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public ResultVo update(@RequestBody ContentCategory contentCategory){
		try {
			contentCategoryService.update(contentCategory);
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
	public ContentCategory findOne(Long id){
		return contentCategoryService.findById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public ResultVo delete(@RequestBody Long [] ids){
		try {
			contentCategoryService.delete(ids);
			return new ResultVo(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/find",method = RequestMethod.POST)
	public PageResult search(@RequestBody ContentCategory contentCategory, int pageNum, int pageSize  ){

		return contentCategoryService.findPage(contentCategory, pageNum, pageSize);
	}
	
}
