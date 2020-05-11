package com.xiaoxinshop.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaoxinshop.content.service.ContentService;
import com.xiaoxinshop.entity.Content;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.entity.ResultVo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

	@RequestMapping("/findByCategoryId")
	public List<Content> findByCategoryId(Long categoryId){
		return  contentService.findByCategoryId(categoryId);

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


	@RequestMapping(value = "/name",method = RequestMethod.GET)
	public String showName() {

		//得到登陆人账号
		String name = SecurityContextHolder.getContext().getAuthentication().getName();

		System.out.println(name);

		return name;
	}


}
