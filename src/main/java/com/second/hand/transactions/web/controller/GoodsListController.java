package com.second.hand.transactions.web.controller;

import com.github.pagehelper.PageInfo;
import com.second.hand.transactions.model.Goods;
import com.second.hand.transactions.service.GoodsService;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/4 0004
 * Time:14:48
 */
@RestController
public class GoodsListController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/goodsList")
    public PageInfo<Goods> goodsList(@RequestParam("pageNumber") Integer pageNumber, @RequestParam("pageSize") Integer pageSize){
        PageInfo<Goods> pageInfo = goodsService.selectList(pageNumber, pageSize);
        return pageInfo;
    }
}
