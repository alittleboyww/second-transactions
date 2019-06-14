package com.second.hand.transactions.commands.utils;

import net.sf.json.JSONObject;
import org.springframework.util.DigestUtils;

import java.util.Arrays;
import java.util.List;

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

        //String imageStr = ImageTransformUtils.getImageStr("E:\\Android\\image\\Absolutely.jpg");


        /*String string = "[电脑外设, 书籍, 免费]";
        String substring = string.substring(1, string.length()-1);

        String[] strings = substring.split(",");
        List<String> stringList = Arrays.asList(strings);
        System.out.println(stringList);*/
        String s = "http://120.79.52.40:8888/upload/d431fef7-101c-4b21-82e7-85904a13e7b7.jpg";
        String[] split = s.split("/");
        System.out.println(split[split.length - 1]);
    }


    public static void result(JSONObject jsonObject){
        jsonObject.put("2","添加后");
    }
}
