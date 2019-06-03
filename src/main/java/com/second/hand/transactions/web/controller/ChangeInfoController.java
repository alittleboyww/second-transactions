package com.second.hand.transactions.web.controller;

import com.second.hand.transactions.commands.transform.impl.StringToChangePassword;
import com.second.hand.transactions.commands.transform.impl.StringToChangePhone;
import com.second.hand.transactions.commands.transform.impl.StringToChangeUsername;
import com.second.hand.transactions.commands.transform.impl.StringToChangeWeChat;
import com.second.hand.transactions.model.requestparam.ChangePasswordRequestParam;
import com.second.hand.transactions.model.requestparam.ChangePhoneRequestParam;
import com.second.hand.transactions.model.requestparam.ChangeUsernameRequestParam;
import com.second.hand.transactions.model.requestparam.ChangeWeChatRequestParam;
import com.second.hand.transactions.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/2 0002
 * Time:15:19
 */
@RestController
public class ChangeInfoController {
    @Autowired
    private UserService userService;

    @PostMapping("/changePassword")
    public JSONObject changePassword(@RequestParam("changePassword") String changePassword){
        ChangePasswordRequestParam requestParam = StringToChangePassword.getInstance().analysisRequestParam(changePassword);
        JSONObject jsonObject = userService.changePassword(requestParam);
        return jsonObject;
    }

    @PostMapping("/changePhone")
    public JSONObject changePhone(@RequestParam("changePhone") String changePhone){
        ChangePhoneRequestParam requestParam = StringToChangePhone.getInstance().analysisRequestParam(changePhone);
        JSONObject jsonObject = userService.changePhone(requestParam);
        return jsonObject;
    }

    @PostMapping("/changeWeChat")
    public JSONObject changeWeChat(@RequestParam("changeWeChat") String changeWeChat){
        ChangeWeChatRequestParam requestParam = StringToChangeWeChat.getInstance().analysisRequestParam(changeWeChat);
        JSONObject jsonObject = userService.changeWeChat(requestParam);
        return jsonObject;
    }

    @PostMapping("/changeUsername")
    public JSONObject changeUsername(@RequestParam("changeUsername") String changeUsername){
        ChangeUsernameRequestParam requestParam = StringToChangeUsername.getInstance().analysisRequestParam(changeUsername);
        JSONObject jsonObject = userService.changeUsername(requestParam);
        return jsonObject;
    }

}
