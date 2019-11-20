package com.xiaoxinshop.controller;
import java.util.List;

import com.xiaoxinshop.entity.ItemCat;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.entity.ResultVo;
import com.xiaoxinshop.service.ItemCatService;
import org.springframework.web.bind.annotation.*;
import com.alibaba.dubbo.config.annotation.Reference;



/**
 * controller
 * @author Administrator
 *
 */
@CrossOrigin(origins = "*", maxAge=3600)
@RestController
@RequestMapping("/itemCat")
public class ItemCatController {

	@Reference
	private ItemCatService itemCatService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<ItemCat> findAll(){
		return itemCatService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows){
		return itemCatService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param itemCat
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public ResultVo add(@RequestBody ItemCat itemCat){
		try {
			itemCatService.add(itemCat);
			return new ResultVo(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param itemCat
	 * @return
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public ResultVo update(@RequestBody ItemCat itemCat){
		try {
			itemCatService.update(itemCat);
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
	public ItemCat findById(Long id){
		return itemCatService.findById(id);
	}

	/**
	 * 查询子类别
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/findByParentId")
	public List<ItemCat> parentId(Long parentId){
		return itemCatService.findByParentId(parentId);
	}

	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public ResultVo delete(@RequestBody Long [] ids){
		try {

			itemCatService.delete(ids);
			return new ResultVo(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "删除失败");
		}
	}
	

	@RequestMapping("/find")
	public PageResult find(@RequestBody ItemCat itemCat, int pageNum, int pageSize  ){
		return itemCatService.findPage(itemCat, pageNum, pageSize);
	}
	
}
