package com.xiaoxinshop.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaoxinshop.search.service.ItemSearchService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author 小浩
 * @Date 2019/11/28 22:06
 * @Version 1.0
 **/
@RestController
@RequestMapping("/itemsearch")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ItemSearchController {
    @Reference
    private ItemSearchService itemSearchService;

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Map<String, Object> search(@RequestBody Map searchMap ){
        return  itemSearchService.search(searchMap);
    }
}
