package com.second.hand.transactions.web.controller;

import com.second.hand.transactions.service.GoodsService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/4 0004
 * Time:20:32
 */
@RestController
public class GoodsDetailController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/goodsDetail")
    public JSONObject goodsDetail(@RequestParam("goodsId") Integer goodsId,@RequestParam("userId")String userId){
        JSONObject jsonObject = goodsService.goodsDetail(goodsId,userId);
        return jsonObject;
    }
}
