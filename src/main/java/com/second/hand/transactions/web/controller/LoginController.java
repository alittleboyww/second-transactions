package com.second.hand.transactions.web.controller;


import com.second.hand.transactions.commands.transform.impl.StringToLoginInfo;
import com.second.hand.transactions.model.requestparam.LoginRequestParam;
import com.second.hand.transactions.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/5/27 0027
 * Time:14:49
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;


    /**
     * 登录
     * @param loginInfo 用户登录信息
     * @return
     */
    @PostMapping("/login")
    public JSONObject login(@RequestParam("loginInfo") String loginInfo){
        LoginRequestParam loginRequestParam = StringToLoginInfo.getInstance().analysisRequestParam(loginInfo);
        JSONObject jsonObject = userService.login(loginRequestParam);
        return jsonObject;
    }
}
