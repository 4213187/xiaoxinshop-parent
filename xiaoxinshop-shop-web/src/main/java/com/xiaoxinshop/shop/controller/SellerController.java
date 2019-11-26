package com.xiaoxinshop.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.entity.ResultVo;
import com.xiaoxinshop.entity.Seller;
import com.xiaoxinshop.service.SellerService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * controller
 * @author Administrator
 *
 */
@CrossOrigin(origins = "*", maxAge=3600)
@RestController
@RequestMapping("/seller")
public class SellerController {

	@Reference
	private SellerService sellerService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<Seller> findAll(){
		return sellerService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows){
		return sellerService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param seller
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public ResultVo add(@RequestBody Seller seller){
		try {
			System.out.println("addSeller");
			seller.setStatus("0");
			seller.setCreateTime(new Date());
			sellerService.add(seller);
			return new ResultVo(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param seller
	 * @return
	 */
	@RequestMapping("/update")
	public ResultVo update(@RequestBody Seller seller){
		try {
			sellerService.update(seller);
			return new ResultVo(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "修改失败");
		}
	}

	@RequestMapping("/update1")
	public ResultVo update1( String sellerId){
		try {
			Seller seller = new Seller();
			seller.setStatus("1");
			seller.setSellerId(sellerId);
			sellerService.updateStatus(seller);
			return new ResultVo(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "修改失败");
		}
	}

	@RequestMapping("/update2")
	public ResultVo update2( String sellerId){
		try {
			Seller seller = new Seller();
			seller.setStatus("2");
			seller.setSellerId(sellerId);
			sellerService.updateStatus(seller);
			return new ResultVo(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "修改失败");
		}
	}


	@RequestMapping("/update3")
	public ResultVo update3( String sellerId){
		try {
			Seller seller = new Seller();
			seller.setStatus("3");
			seller.setSellerId(sellerId);
			sellerService.updateStatus(seller);
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
	public Seller findById(String id){
		return sellerService.findById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public ResultVo delete(String [] ids){
		try {
			sellerService.delete(ids);
			return new ResultVo(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "删除失败");
		}
	}

	/**
	 * 分页查询 以及待条件查询
	 * @param seller
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/find0",method = RequestMethod.POST)
	public PageResult find0(@RequestBody Seller seller, int pageNum, int pageSize  ){
		seller.setStatus("0");
		return sellerService.findPage(seller, pageNum, pageSize);
	}

	/**
	 * 分页查询 以及待条件查询
	 * @param seller
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/find",method = RequestMethod.POST)
	public PageResult find(@RequestBody Seller seller, int pageNum, int pageSize  ){

		return sellerService.findPage(seller, pageNum, pageSize);
	}


	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public ResultVo login(@RequestBody Seller seller) {
		System.out.println("sellerlogin");
		try {
			Seller login = sellerService.findLogin(seller);
			if (login != null){
				return  new ResultVo(true,"登陆成功");
			}else {
				return  new ResultVo(false,"登陆失败");
			}
		}catch (Exception e){
			e.printStackTrace();
			return  new ResultVo(false,"登陆失败");
		}



	}

}
