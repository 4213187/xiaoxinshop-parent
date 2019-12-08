package com.xiaoxinshop.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaoxinshop.entity.Item;
import com.xiaoxinshop.mapper.ItemMapper;
import com.xiaoxinshop.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;

import org.springframework.data.solr.core.query.result.*;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author 小浩
 * @Date 2019/11/28 21:44
 * @Version 1.0
 **/
@Service
public class ItemSearchServiceImpl implements ItemSearchService {
    @Autowired
    private SolrTemplate solrTemplate;

//    @Autowired
//    private  ItemMapper itemMapper;

    @Override
    public Map<String, Object> search(Map searchMap) {

//        List<Item> items = itemMapper.findAll();
//        System.out.println("===商品列表===");
//        for(Item item:items){
//            //将spec字段中的json字符串转换为map
//            Map specMap= JSON.parseObject(item.getSpec());
//            //给带注解的字段赋值
//            item.setSpecMap(specMap);
//            System.out.println(item.getTitle());
//        }
//
//        solrTemplate.saveBeans(items);
//        solrTemplate.commit();
//        System.out.println("===结束===");

//        ----------------------------------------------

        //添加查询条件
//        Query query=new SimpleQuery("*:*");
//        Criteria criteria=new Criteria("item_keywords").is(searchMap.get("keywords"));
//        query.addCriteria(criteria);
//        ScoredPage<Item> page = solrTemplate.queryForPage(query, Item.class);
//        System.out.println(page.getContent());
//        map.put("rows", page.getContent());


//      要返回的结果
        Map<String, Object> map = new HashMap<>();

//      去空格
        String keywords = (String)searchMap.get("keywords");
        if (keywords!=null && keywords.length()>0 &&keywords.indexOf(" ")>=0){
            keywords =keywords.replace(" ","");
        }

        searchMap.put("keywords",keywords);

//      1.查询列表
        map.putAll(searchList(searchMap));
//      2.类别分类
        List<String> categoryList = searchCategoryList(searchMap);
        map.put("categoryList", categoryList);
//      3.查询品牌和规格列表
        String categoty = (String) searchMap.get("category");
        if (!"".equals(categoty)) {
            map.putAll(searchBrandAndSpecList(categoty));

        } else {
            if (categoryList.size() > 0) {
                map.putAll(searchBrandAndSpecList(categoryList.get(0)));
            }
        }


        return map;
    }

    @Override
    public void importList(List list) {

        solrTemplate.saveBeans(list);
        solrTemplate.commit();

    }

    @Override
    public void deleteByGoodsIds(List goodsIdList) {
        System.out.println("删除商品ID"+goodsIdList);
        Query query=new SimpleQuery();
        Criteria criteria=new Criteria("item_goodsid").in(goodsIdList);
        query.addCriteria(criteria);
        solrTemplate.delete(query);
        solrTemplate.commit();
    }


    private Map searchList(Map searchMap) {
        Map<String, Object> map = new HashMap<>();
        HighlightQuery query = new SimpleHighlightQuery();
        HighlightOptions highlightOptions = new HighlightOptions();
        //设置高亮的域
        highlightOptions.addField("item_title");
        //高亮前缀
        highlightOptions.setSimplePrefix("<em style='color:red'>");
        //高亮后缀
        highlightOptions.setSimplePostfix("</em>");
        //设置高亮选项
        query.setHighlightOptions(highlightOptions);

        //1.按照关键字查询
        Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
        query.addCriteria(criteria);

        //2.过滤类别
        if (!"".equals(searchMap.get("category"))) {
            FilterQuery filterQuery = new SimpleFacetQuery();
            Criteria filterCriteria = new Criteria("item_category").is(searchMap.get("category"));
            filterQuery.addCriteria(filterCriteria);
            query.addFilterQuery(filterQuery);
        }

        //3.过滤品牌
        if (!"".equals(searchMap.get("brand"))) {
            FilterQuery filterQuery = new SimpleFacetQuery();
            Criteria filterCriteria = new Criteria("item_brand").is(searchMap.get("brand"));
            filterQuery.addCriteria(filterCriteria);
            query.addFilterQuery(filterQuery);
        }

        //4.过滤规格
        if (searchMap.get("spec") != null) {
            Map<String, String> specMap = (Map<String, String>) searchMap.get("spec");
            for (String key : specMap.keySet()) {
                FilterQuery filterQuery = new SimpleFacetQuery();
                Criteria filterCriteria = new Criteria("item_spec_" + key).is(specMap.get(key));
                filterQuery.addCriteria(filterCriteria);
                query.addFilterQuery(filterQuery);
            }

        }

        //5.过滤价格
        if (!"".equals(searchMap.get("price"))) {
            String[] prices = ((String) searchMap.get("price")).split("-");
            if (!"0".equals(prices[0])) {
                FilterQuery filterQuery = new SimpleFacetQuery();
                Criteria filterCriteria = new Criteria("item_price").greaterThanEqual(prices[0]);
                filterQuery.addCriteria(filterCriteria);
                query.addFilterQuery(filterQuery);
            }

            if (!"*".equals(prices[1])) {
                FilterQuery filterQuery = new SimpleFacetQuery();
                Criteria filterCriteria = new Criteria("item_price").lessThanEqual(prices[1]);
                filterQuery.addCriteria(filterCriteria);
                query.addFilterQuery(filterQuery);
            }

        }

        //6.分页

        Integer pageNum = (Integer) searchMap.get("pageNum");
        if (pageNum == null) {
            pageNum = 1;
        }
        Integer pageSize = (Integer) searchMap.get("pageSize");
        if (pageSize == null) {
            pageSize = 20;
        }
        query.setOffset( (pageNum-1)*pageSize);
        query.setRows(pageSize);

        //排序

       String sortValue = (String) searchMap.get("sort");
       String sortField = (String) searchMap.get("sortField");
       if (!"".equals(sortValue)){
           if ("ASC".equals(sortValue)){
               Sort sort = new Sort(Sort.Direction.ASC,"item_"+sortField);
               query.addSort(sort);
           }
           if ("DESC".equals(sortValue)){
               Sort sort = new Sort(Sort.Direction.DESC,"item_"+sortField);
               query.addSort(sort);
           }

       }






        HighlightPage<Item> page = solrTemplate.queryForHighlightPage(query, Item.class);

        //循环高亮入口集合
        List<HighlightEntry<Item>> highlighted = page.getHighlighted();

        for (HighlightEntry<Item> h : highlighted) {
            //一条结果的高亮域
            List<HighlightEntry.Highlight> highlights = h.getHighlights();

            if (highlights.size() > 0 && highlights.get(0).getSnipplets().size() > 0) {
                Item item = h.getEntity();
                item.setTitle(highlights.get(0).getSnipplets().get(0));
            }

        }

        map.put("rows", page.getContent());
        map.put("totalPages",page.getTotalPages());
        map.put("total",page.getTotalElements());
        System.out.println("页数"+page.getTotalPages());
        System.out.println("条数"+page.getTotalElements());
        return map;


    }

    private List<String> searchCategoryList(Map searchMap) {
        List<String> list = new ArrayList<>();

        Query query = new SimpleQuery("*:*");

        //按照关键字查询
        Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
        query.addCriteria(criteria);

        //设置分组选项
        GroupOptions groupOptions = new GroupOptions().addGroupByField("item_category");

        query.setGroupOptions(groupOptions);
        query.addCriteria(criteria);

        //得到分组页
        GroupPage<Item> page = solrTemplate.queryForGroupPage(query, Item.class);

        //根据列得到分组结果集
        GroupResult<Item> groupResult = page.getGroupResult("item_category");

        //得到分组结果入口页
        Page<GroupEntry<Item>> groupEntries = groupResult.getGroupEntries();

        //得到分组入口集合
        List<GroupEntry<Item>> content = groupEntries.getContent();
        for (GroupEntry<Item> entry : content) {
            //将分组结果的名称封装到返回值中
            list.add(entry.getGroupValue());
        }


        return list;
    }


    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询品牌和规格列表
     *
     * @param category 分类名称
     * @return
     */
    private Map searchBrandAndSpecList(String category) {
        Map map = new HashMap();
        //获取模板ID
        Long typeId = (Long) redisTemplate.boundHashOps("itemCat").get(category);
        if (typeId != null) {
            //根据模板ID查询品牌列表
            List brandList = (List) redisTemplate.boundHashOps("brandList").get(typeId);
            map.put("brandList", brandList);
            //根据模板ID查询规格列表
            List specList = (List) redisTemplate.boundHashOps("specList").get(typeId);
            map.put("specList", specList);
        }
        return map;
    }




}
