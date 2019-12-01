package com.xiaoxinshop.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaoxinshop.entity.*;
import com.xiaoxinshop.search.service.ItemSearchService;
import com.xiaoxinshop.service.GoodsService;
import com.xiaoxinshop.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * @author lenovo
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Reference
    private GoodsService goodsService;

    @Reference
    private ItemService itemService;

    @Reference
    private ItemSearchService itemSearchService;


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return goodsService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param gGoods
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultVo add(@RequestBody GGoods gGoods) {
        try {
            System.out.println(gGoods.getGoods());
            System.out.println(gGoods.getGoodsDesc());
            System.out.println(gGoods.getItemList());
            goodsService.add(gGoods);
            return new ResultVo(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVo(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param goods
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultVo update(@RequestBody GGoods goods) {
        try {
            System.out.println(goods.getGoods().getIsEnableSpec());
            goodsService.update(goods);
            return new ResultVo(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVo(false, "修改失败");
        }
    }


    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public ResultVo updateStatus(@RequestBody Long[] ids, String status) {
        try {
            System.out.println("updateStatus");
            goodsService.updateStatus(ids, status);


            if ("1".equals(status)) {
                List<Item> items = itemService.findItemListByGoodsIdandStatus(ids, "1");
                if (items.size() > 0) {
                    System.out.println(items+"1111");
                    itemSearchService.importList(items);
                }
            }

            return new ResultVo(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVo(false, "修改失败");
        }
    }


    @RequestMapping(value = "/updateMarketableStatus", method = RequestMethod.POST)
    public ResultVo updateMarketableStatus(@RequestBody Long[] ids, String status) {
        try {
            System.out.println("updateMarketableStatus");
            goodsService.updateMarketableStatus(ids, status);
            return new ResultVo(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVo(false, "修改失败");
        }
    }


    @RequestMapping("/findById")
    public GGoods findById(Long id) {
        return goodsService.findById(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultVo delete(@RequestBody Long[] ids) {
        try {
            System.out.println("delete");
            goodsService.delete(ids);

            itemSearchService.deleteByGoodsIds(Arrays.asList(ids));
            return new ResultVo(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVo(false, "删除失败");
        }
    }


    @RequestMapping(value = "/sellerFind", method = RequestMethod.POST)
    public PageResult sellerFind(@RequestBody Goods goods, int pageNum, int pageSize) {

        return goodsService.findPage(goods, pageNum, pageSize);
    }

}
