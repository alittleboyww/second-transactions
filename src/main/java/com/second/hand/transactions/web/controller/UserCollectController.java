package com.second.hand.transactions.web.controller;

import com.second.hand.transactions.service.UserService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
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
    @Autowired
    private UserService userService;

    @GetMapping("/goodsCollect")
    public JSONObject collectGoods(@RequestParam("userId")String userId, @RequestParam("goodsId") int goodsId){
        JSONObject jsonObject = userService.collectGoods(userId, goodsId);
        return jsonObject;
    }

    @GetMapping("/cancelCollect")
    public JSONObject cancelCollect(@RequestParam("userId")String userId, @RequestParam("goodsId") int goodsId){
        JSONObject jsonObject = userService.cancelCollect(userId,goodsId);
        return jsonObject;
    }

}
