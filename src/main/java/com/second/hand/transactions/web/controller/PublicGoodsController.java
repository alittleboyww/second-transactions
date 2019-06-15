package com.second.hand.transactions.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.second.hand.transactions.commands.constant.GoodsRequestParamConstant;
import com.second.hand.transactions.commands.constant.UserRequestParamConstant;
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

    /**
     * 以发布商品列表
     * @param userId  用户id
     * @return
     */
    @GetMapping("/publicGoodsList")
    public com.alibaba.fastjson.JSONObject publicGoods(@RequestParam("userId") String userId){
        JSONObject jsonObject = userService.publicGoods(userId);
        return jsonObject;
    }

    /**
     * 取消发布
     * @param cancel 取消发布对应的信息
     * @return
     */
    @PostMapping("/cancelPublic")
    public net.sf.json.JSONObject cancelPublic(@RequestParam("cancelInfo")String cancel){
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(cancel);
        String userId = jsonObject.getString(UserRequestParamConstant.ID);
        String password = jsonObject.getString(UserRequestParamConstant.PASSWORD);
        Integer goodsId = jsonObject.getInt(GoodsRequestParamConstant.ID);
        net.sf.json.JSONObject jsonObject1 = userService.cancelGoods(userId,password, goodsId);
        return jsonObject1;
    }
}
