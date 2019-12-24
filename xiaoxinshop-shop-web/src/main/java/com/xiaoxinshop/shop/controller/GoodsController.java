package com.xiaoxinshop.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.xiaoxinshop.entity.*;
import com.xiaoxinshop.service.GoodsService;
import com.xiaoxinshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.*;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * controller
 * @author Administrator
 *
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/goods")
public class GoodsController {

	@Reference(timeout = 36000)
	private GoodsService goodsService;

	@Reference(timeout = 36000)
	private ItemService itemService;
	

	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows){
		return goodsService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param gGoods
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:63343" ,allowCredentials = "true")
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public ResultVo add(@RequestBody GGoods gGoods,HttpServletRequest httpServletRequest){
		try {
			HttpSession session = httpServletRequest.getSession();
			Seller seller =(Seller) session.getAttribute("seller");

			System.out.println(gGoods.getGoods());
			System.out.println(gGoods.getGoodsDesc());
			System.out.println(gGoods.getItemList());
			gGoods.getGoods().setSellerId(seller.getSellerId());
			gGoods.getGoods().setIsMarketable("0");
			goodsService.add(gGoods);
			return new ResultVo(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param goods
	 * @return
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public ResultVo update(@RequestBody GGoods goods){
		try {
			System.out.println(goods.getGoods().getIsEnableSpec());
			goodsService.update(goods);
			return new ResultVo(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "修改失败");
		}
	}


	@RequestMapping(value = "/updateStatus",method = RequestMethod.POST)
	public ResultVo updateStatus(@RequestBody Long[] ids ,String status){
		try {
			System.out.println("updateStatus");
			goodsService.updateStatus(ids,status);
			return new ResultVo(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "修改失败");
		}
	}

	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	private Destination queueSolrDestination;


	@Autowired
	private Destination queueSolrDeleteDestination;

	@RequestMapping(value = "/updateMarketableStatus",method = RequestMethod.POST)
	public ResultVo updateMarketableStatus(@RequestBody Long[] ids ,String status){
		try {
			System.out.println("updateMarketableStatus");
			goodsService.updateMarketableStatus(ids,status);
			if ("1".equals(status)){
				List<Item> itemList = itemService.findItemListByGoodsIdandStatus(ids, "1");
				if (itemList.size() > 0) {
//                  solr
					final String jsonString = JSON.toJSONString(itemList);
					System.out.println(itemList);
					System.out.println("jmsTemplate");
					jmsTemplate.send(queueSolrDestination, new MessageCreator() {
						@Override
						public Message createMessage(Session session) throws JMSException {
							return session.createTextMessage(jsonString);
						}
					});
				}
			}else {
				jmsTemplate.send(queueSolrDeleteDestination, new MessageCreator() {
					@Override
					public Message createMessage(Session session) throws JMSException {
						return session.createObjectMessage(ids);
					}
				});
			}
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
	public GGoods findById(Long id){
		return goodsService.findById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public ResultVo delete(@RequestBody Long [] ids){
		try {
			System.out.println("delete");
			goodsService.delete(ids);
			return new ResultVo(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "删除失败");
		}
	}

	@CrossOrigin(origins = "http://localhost:63343" ,allowCredentials = "true")
	@RequestMapping(value = "/sellerFind",method = RequestMethod.POST)
	public PageResult sellerFind(@RequestBody Goods goods, int pageNum, int pageSize , HttpServletRequest httpServletRequest){
		HttpSession session = httpServletRequest.getSession();
		Seller seller =(Seller) session.getAttribute("seller");
		if (seller !=null){
			System.out.println(seller);
			goods.setSellerId(seller.getSellerId());
		}

		return goodsService.findPage(goods, pageNum, pageSize);
	}
	
}
