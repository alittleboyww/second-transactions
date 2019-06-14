package com.second.hand.transactions.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.second.hand.transactions.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/13 0013
 * Time:20:31
 */
@RestController
public class PublicGoodsController {

    @Autowired
    private UserService userService;

    @GetMapping("/publicGoodsList")
    public com.alibaba.fastjson.JSONObject publicGoods(@RequestParam("userId") String userId){
        JSONObject jsonObject = userService.publicGoods(userId);
        return jsonObject;
    }

    @GetMapping("/cancelPublic")
    public net.sf.json.JSONObject cancelPublic(@RequestParam("userId") String userId,@RequestParam("goodsId") Integer goodsId){
        net.sf.json.JSONObject jsonObject = userService.cancelGoods(userId, goodsId);
        return jsonObject;
    }
}
