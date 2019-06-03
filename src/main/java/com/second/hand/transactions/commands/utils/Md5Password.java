package com.second.hand.transactions.commands.utils;

import net.sf.json.JSONObject;
import org.springframework.util.DigestUtils;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/2 0002
 * Time:13:45
 */
public class Md5Password {
    public static void main(String[] args) {
        /*String password = DigestUtils.md5DigestAsHex("123456".getBytes());
        System.out.println(password);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1","先添加的");
        result(jsonObject);
        System.out.println(jsonObject.getString("2"));*/

        String imageStr = ImageTransformUtils.getImageStr("E:\\Android\\image\\Absolutely.jpg");

    }


    public static void result(JSONObject jsonObject){
        jsonObject.put("2","添加后");
    }
}
