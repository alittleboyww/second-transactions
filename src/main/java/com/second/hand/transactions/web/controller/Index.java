package com.second.hand.transactions.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/3 0003
 * Time:9:37
 */
@Controller
public class Index {

    /**
     * 用于上传
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
