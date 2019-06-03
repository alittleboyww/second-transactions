package com.second.hand.transactions.web.controller;

import com.second.hand.transactions.commands.constant.PathConstant;
import com.second.hand.transactions.commands.transform.impl.*;
import com.second.hand.transactions.commands.utils.ImageTransformUtils;
import com.second.hand.transactions.model.requestparam.*;
import com.second.hand.transactions.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.UUID;

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

    @GetMapping("/changeImage")
    public JSONObject changeImage(@RequestParam("changeImage") String changeImage, HttpServletRequest request) throws FileNotFoundException {
        ChangeImageRequestParam requestParam = StringToChangeImage.getInstance().analysisRequestParam(changeImage);
        //文件名
        String fileName = UUID.randomUUID() + requestParam.getType();
        //存储文件的路径
        String path = ResourceUtils.getFile("classpath:").getPath();

        File file = new File(path);
        file = new File(file.getAbsolutePath(), PathConstant.UPLOAD_IMAGE);
        if(!file.exists()){
            file.mkdirs();
        }
        String filePath = file.getPath() + File.separator + fileName;
        ImageTransformUtils.GenerateImage(requestParam.getImageStr(),filePath);

        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + File.separator + PathConstant.UPLOAD_IMAGE + fileName;
        //设置图片存储得路径
        requestParam.setImagePath(url);

        JSONObject jsonObject = userService.changeImage(requestParam);
        return jsonObject;
    }
}
