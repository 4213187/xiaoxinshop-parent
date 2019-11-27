package com.xiaoxinshop.manager.controller;
import java.util.List;

import com.xiaoxinshop.content.service.ContentService;
import com.xiaoxinshop.entity.Content;
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
@RequestMapping("/content")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ContentController {

	@Reference
	private ContentService contentService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<Content> findAll(){
		return contentService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows){
		return contentService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public ResultVo add(@RequestBody Content content){
		try {
			contentService.add(content);
			return new ResultVo(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public ResultVo update(@RequestBody Content content){
		try {
			contentService.update(content);
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
	public Content findOne(Long id){
		return contentService.findById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public ResultVo delete(@RequestBody Long [] ids){
		try {
			contentService.delete(ids);
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
	public PageResult search(@RequestBody Content content, int pageNum, int pageSize  ){
		return contentService.findPage(content, pageNum, pageSize);
	}
	
}
