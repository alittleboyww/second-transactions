package com.second.hand.transactions.web.controller;

import com.second.hand.transactions.commands.constant.PathConstant;
import com.second.hand.transactions.commands.transform.impl.StringToUser;
import com.second.hand.transactions.commands.utils.ImageTransformUtils;
import com.second.hand.transactions.model.User;
import com.second.hand.transactions.model.requestparam.UserRequestParam;
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
 * Date:2019/5/27 0027
 * Time:16:10
 */
@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    /*@PostMapping("/register")
    public JSONObject register(User user){
        if(user != null){
            System.out.println(user.toString());
        }
        //
        userService.insert(user);
        JSONObject jsonObject = new JSONObject();
        user = userService.selectByPhone(user.getPhone());
        jsonObject.put("user", user);
        return jsonObject;
    }*/

    /*@PostMapping("/register")
    public JSONObject register(@RequestParam("userInfo") String userInfo, HttpServletRequest request) throws FileNotFoundException {
        UserRequestParam userRequestParam = StringToUser.getInstance().analysisRequestParam(userInfo);
        //文件名
        String fileName = UUID.randomUUID() + userRequestParam.getType();
        //存储文件的路径
        String path = ResourceUtils.getFile("classpath:").getPath();
        File file = new File(path);
        file = new File(file.getAbsolutePath(), PathConstant.UPLOAD_IMAGE);
        if(!file.exists()){
            file.mkdirs();
        }
        String filePath = file.getPath() + File.separator + fileName;
        ImageTransformUtils.GenerateImage(userRequestParam.getImageStr(),filePath);

        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + PathConstant.UPLOAD_IMAGE + fileName;
        User user = new User();
        user.setImagePath(url);
        user.setUsername(userRequestParam.getUsername());
        user.setPassword(userRequestParam.getPassword());
        user.setWechat(userRequestParam.getWechat());
        user.setPhone(userRequestParam.getPhone());

        JSONObject jsonObject = userService.save(user);
        return jsonObject;
    }*/


    /*@GetMapping("/upload")
    public JSONObject upload(HttpServletRequest request) throws FileNotFoundException {
        String imageStr = ImageTransformUtils.getImageStr("E:\\Android\\image\\Absolutely.jpg");
        //文件名
        String fileName = UUID.randomUUID() + ".jpg";
        //存储文件的路径
        String path = ResourceUtils.getFile("classpath:").getPath();
        File file = new File(path);
        file = new File(file.getAbsolutePath(), PathConstant.UPLOAD_IMAGE);
        if(!file.exists()){
            file.mkdirs();
        }
        String filePath = file.getPath() + File.separator + fileName;
        ImageTransformUtils.GenerateImage(imageStr,filePath);

        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + File.separator + PathConstant.UPLOAD_IMAGE + fileName;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url",url);
        return jsonObject;
    }*/
}
