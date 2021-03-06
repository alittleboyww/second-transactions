package com.second.hand.transactions.web.controller;

import com.second.hand.transactions.commands.constant.PathConstant;
import com.second.hand.transactions.commands.transform.impl.*;
import com.second.hand.transactions.commands.utils.ImageTransformUtils;
import com.second.hand.transactions.model.requestparam.*;
import com.second.hand.transactions.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${web.upload-path}")
    private String uploadPath;


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

    @PostMapping("/changeImage")
    public JSONObject changeImage(@RequestParam("changeImage") String changeImage, HttpServletRequest request) throws FileNotFoundException {
        ChangeImageRequestParam requestParam = StringToChangeImage.getInstance().analysisRequestParam(changeImage);
        requestParam.setImagePath(saveImage(requestParam.getType(),requestParam.getImageStr(),request));
        JSONObject jsonObject = userService.changeImage(requestParam);
        return jsonObject;
    }

    public String saveImage(String type,String imageStr,HttpServletRequest request){
        //文件名
        String fileName = UUID.randomUUID() + type;
        //存储文件的路径
        File file = new File(uploadPath);
        if(!file.exists()){
            file.mkdirs();
        }
        String filePath = file.getPath() + File.separator + fileName;
        ImageTransformUtils.GenerateImage(imageStr,filePath);

        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + PathConstant.UPLOAD_IMAGE + fileName;
        return url;
    }
}


