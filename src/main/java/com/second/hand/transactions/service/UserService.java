package com.second.hand.transactions.service;

import com.second.hand.transactions.model.User;
import com.second.hand.transactions.model.requestparam.*;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/5/27 0027
 * Time:11:14
 */
public interface UserService {
    //添加用户
    JSONObject save(User user);

    //通过id查询用户
    User selectById(String id);

    //通过手机号查询用户
    User selectByPhone(String phone);

    //通过用户名查询用户
    User selectByUsername(String username);

    //通过微信号查询用户
    User selectByWechat(String wechat);

    //登录
    JSONObject login(LoginRequestParam requestParam);

    //修改密码
    JSONObject changePassword(ChangePasswordRequestParam requestParam);

    //修改电话号码
    JSONObject changePhone(ChangePhoneRequestParam requestParam);

    //修改weChat
    JSONObject changeWeChat(ChangeWeChatRequestParam requestParam);

    //修改用户名
    JSONObject changeUsername(ChangeUsernameRequestParam requestParam);

    //修改头像
    JSONObject changeImage(ChangeImageRequestParam requestParam);


    //收藏
    JSONObject collectGoods(String userId, int goodsId);

    //取消收藏
    JSONObject cancelCollect(String userId, int goodsId);

    //收藏列表
    com.alibaba.fastjson.JSONObject collectList(String userId);

    //检查用户
    JSONObject checkUser(String id, String password);

    //用户发布商品
    com.alibaba.fastjson.JSONObject publicGoods(String userId);

    //用户删除发布的商品
    JSONObject cancelGoods(String userId,String password,Integer goodsId);
}
