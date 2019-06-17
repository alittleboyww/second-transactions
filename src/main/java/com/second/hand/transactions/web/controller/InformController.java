package com.second.hand.transactions.web.controller;

import com.second.hand.transactions.service.MessageService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/17 0017
 * Time:10:54
 */
@RestController
public class InformController {

    @Autowired
    private MessageService messageService;
    //需要轮询查找留言
    /**
     * 1. 怎么知道商品进行了最新的留言   通过前台将上一次的留言总量 传递过来
     * 通过商品用户id 商品id 查询对应留言的数量   前台传出留言的数量
     */
    @GetMapping("/informMessage")
    public JSONObject informMessage(@RequestParam("goodsId") Integer goodsId,
                                    @RequestParam("messageNum") Integer messageNum){
        JSONObject jsonObject = messageService.messageNumber(goodsId, messageNum);
        return jsonObject;
    }
}