package com.xiaoxinshop.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.xiaoxinshop.entity.*;
import com.xiaoxinshop.mapper.*;
import com.xiaoxinshop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;


/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsDescMapper goodsDescMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private ItemCatMapper itemCatMapper;
    @Autowired
    private SellerMapper sellerMapper;


    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        return null;
    }

    /**
     * 增加
     */
    @Override
    public void add(GGoods goods) {
        goods.getGoods().setAuditStatus("0");
        goodsMapper.insert(goods.getGoods());
        goods.getGoodsDesc().setGoodsId(goods.getGoods().getId());
        goodsDescMapper.insert(goods.getGoodsDesc());
        setItems(goods);



    }

    private void setItems(GGoods goods){
        if ("1".equals(goods.getGoods().getIsEnableSpec())) {
            for (Item item : goods.getItemList()) {
                //标题
                String title = goods.getGoods().getGoodsName();
                Map<String, Object> specMap = JSON.parseObject(item.getSpec());
                for (String key : specMap.keySet()) {
                    title += " " + specMap.get(key);

                }
                item.setTitle(title);
                setItemValues(goods, item);
                itemMapper.insert(item);


            }
        } else {
            Item item = new Item();
            //商品KPU+规格描述串作为SKU名称
            item.setTitle(goods.getGoods().getGoodsName());
            //价格
            item.setPrice(goods.getGoods().getPrice());
            //状态
            item.setStatus("1");
            //是否默认
            item.setIsDefault("1");
            //库存数量
            item.setNum(99999);
            item.setSpec("{}");
            setItemValues(goods, item);
            itemMapper.insert(item);


        }
    }

    private void setItemValues(GGoods goods, Item item) {
        //商品id
        item.setGoodsId(goods.getGoods().getId());

        //商家id
        item.setSellerId(goods.getGoods().getSellerId());

        //商品分类编号（3级）
        item.setCategoryid(goods.getGoods().getCategory3Id());

        //创建日期
        item.setCreateTime(new Date());
        //修改日期
        item.setUpdateTime(new Date());

        //品牌名称
        Brand brand = brandMapper.selectByPrimaryKey(goods.getGoods().getBrandId());
        item.setBrand(brand.getName());

        //分类名称
        ItemCat itemCat = itemCatMapper.selectByPrimaryKey(goods.getGoods().getCategory3Id());
        item.setCategory(itemCat.getName());


        //商家名称
        Seller seller = sellerMapper.selectByPrimaryKey(goods.getGoods().getSellerId());
        item.setSeller(seller.getNickName());

        //图片地址（取spu的第一个图片）
        List<Map> imageList = JSON.parseArray(goods.getGoodsDesc().getItemImages(), Map.class);
        if (imageList.size() > 0) {
            item.setImage((String) imageList.get(0).get("url"));
        }

    }


    /**
     * 修改
     */
    @Override
    public void update(GGoods goods) {

        System.out.println(goods.getGoods().getIsEnableSpec());
        goodsMapper.updateByPrimaryKeySelective(goods.getGoods());

        goodsDescMapper.updateByPrimaryKeySelective(goods.getGoodsDesc());

        itemMapper.deleteByGoodsId(goods.getGoods().getId());

        setItems(goods);



    }

    @Override
    public void updateStatus(Long[] ids,String status) {
        for (Long id:ids) {
            Goods goods = goodsMapper.selectByPrimaryKey(id);
            goods.setAuditStatus(status);
            goodsMapper.updateByPrimaryKeySelective(goods);
        }
    }

    @Override
    public void updateMarketableStatus(Long[] ids, String status) {
        for (Long id:ids) {
            Goods goods = goodsMapper.selectByPrimaryKey(id);
            goods.setIsMarketable(status);
            goodsMapper.updateByPrimaryKeySelective(goods);
        }

    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public GGoods findById(Long id) {
        GGoods gGoods = new GGoods();
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        gGoods.setGoods(goods);
        GoodsDesc goodsDesc = goodsDescMapper.selectByPrimaryKey(id);
        gGoods.setGoodsDesc(goodsDesc);

        List<Item> itemList = itemMapper.findByGoodsId(id);
        gGoods.setItemList(itemList);
        return gGoods;

    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {

            Goods goods = goodsMapper.selectByPrimaryKey(id);
            goods.setIsDelete("1");
            goodsMapper.updateByPrimaryKeySelective(goods);
        }
    }


    @Override
    public PageResult findPage(Goods goods, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Goods> page = (Page<Goods>) goodsMapper.findAll(goods);
        return new PageResult(page.getTotal(), page.getResult());
    }

}
