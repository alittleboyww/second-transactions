package com.second.hand.transactions.web.controller;

import com.github.pagehelper.PageInfo;
import com.second.hand.transactions.model.Goods;
import com.second.hand.transactions.service.GoodsService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/15 0015
 * Time:9:57
 * DESC:商品查询控制器
 */
@RestController
public class GoodsSearchController {
    @Autowired
    private GoodsService goodsService;

    //商品条件查询
    @GetMapping("/searchGoods")
    public PageInfo<Goods> searchGoods(@RequestParam("tag") String tag,
                                  @RequestParam("text") String text,
                                  @RequestParam("pageNumber") Integer pageNumber,
                                  @RequestParam("pageSize") Integer pageSize){
        //
        PageInfo<Goods> pageInfo = goodsService.searchGoods(tag, text, pageNumber, pageSize);
        return pageInfo;
    }
}
