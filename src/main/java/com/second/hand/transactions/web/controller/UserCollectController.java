package com.second.hand.transactions.web.controller;

import com.second.hand.transactions.service.UserService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/11 0011
 * Time:16:36
 */
@RestController
public class UserCollectController {
    Logger logger = LoggerFactory.getLogger(UserCollectController.class);

    @Autowired
    private UserService userService;

    //收藏商品
    @GetMapping("/goodsCollect")
    public JSONObject collectGoods(@RequestParam("userId")String userId, @RequestParam("goodsId") int goodsId){
        JSONObject jsonObject = userService.collectGoods(userId, goodsId);
        return jsonObject;
    }

    //取消收藏
    @GetMapping("/cancelCollect")
    public JSONObject cancelCollect(@RequestParam("userId")String userId, @RequestParam("goodsId") int goodsId){
        logger.info(userId + " " + goodsId);
        JSONObject jsonObject = userService.cancelCollect(userId,goodsId);
        return jsonObject;
    }

    //收藏商品列表
    @GetMapping("/collectList")
    public com.alibaba.fastjson.JSONObject collectList(@RequestParam("userId")String userId){
        com.alibaba.fastjson.JSONObject jsonObject = userService.collectList(userId);
        return jsonObject;
    }
}
