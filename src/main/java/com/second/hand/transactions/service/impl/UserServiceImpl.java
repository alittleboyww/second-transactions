package com.second.hand.transactions.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.second.hand.transactions.commands.constant.ResultConstant;
import com.second.hand.transactions.commands.constant.UserRequestParamConstant;
import com.second.hand.transactions.commands.utils.JsonDateValueProcessor;
import com.second.hand.transactions.commands.validate.BeanValidator;
import com.second.hand.transactions.mapper.UserMapper;
import com.second.hand.transactions.model.Goods;
import com.second.hand.transactions.model.User;
import com.second.hand.transactions.model.requestparam.*;
import com.second.hand.transactions.service.UserService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/5/27 0027
 * Time:11:15
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    //TODO:保持用户，由于没有登录 所以理论上这里也可以删掉
    @Override
    public JSONObject save(User user) {
        //判断用户是否存在且判断 用户信息格式否正确
        String validate = BeanValidator.validator(user);
        JSONObject jsonObject = new JSONObject();
        //如果验证不为空，表示验证失败
        if(validate != null){
            jsonObject.put(ResultConstant.RESULT_RESULT,ResultConstant.RESULT_ERROR);
            jsonObject.put(ResultConstant.RESULT_MESSAGE,validate);
        }
        //表示验证通过
        else{
            //这里需要验证用户是否存在
            JSONObject message = checkUserIsExist(user);
            //返回的信息为空表示验证成功
            if(message.isEmpty()){
                //对密码进行加密
                String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
                user.setPassword(password);
                userMapper.insert(user);
                jsonObject.put(ResultConstant.RESULT_RESULT,ResultConstant.RESULT_SUCCESS);
            }
            //用户存在
            else{
                jsonObject.put(ResultConstant.RESULT_MESSAGE,message);
                jsonObject.put(ResultConstant.RESULT_RESULT,ResultConstant.RESULT_ERROR);
            }
        }
        return jsonObject;
    }

    //通过id 查询用户
    @Override
    public User selectById(String id) {
        User user = userMapper.selectById(id);
        return user;
    }

    //通过手机号查询用户
    @Override
    public User selectByPhone(String phone) {
        User user = userMapper.selectByPhone(phone);
        return user;
    }

    //通过用户名查询用户
    @Override
    public User selectByUsername(String username) {
        User user = userMapper.selectByPhone(username);
        return null;
    }

    @Override
    public User selectByWechat(String wechat) {
        User user = userMapper.selectByWechat(wechat);
        return user;
    }

    @Override
    public JSONObject login(LoginRequestParam loginRequestParam) {
        //获得账号和密码
        JSONObject jsonObject = checkUser(loginRequestParam.getId(), loginRequestParam.getPassword());
        return jsonObject;
    }

    //检查username phone
    //TODO:注册逻辑，理论上可以删掉
    private JSONObject checkUserIsExist(User user){
        JSONObject jsonObject = new JSONObject();
        if(selectByPhone(user.getPhone()) != null){
            jsonObject.put(UserRequestParamConstant.PHONE,"手机号以被注册");
        }
        if(selectByUsername(user.getUsername()) != null){
            jsonObject.put(UserRequestParamConstant.USERNAME,"用户名已存在");
        }
        if(selectByWechat(user.getWechat()) != null){
            jsonObject.put(UserRequestParamConstant.WECHAT,"wechat已存在");
        }
        return jsonObject;
    }

    @Override
    public JSONObject changePassword(ChangePasswordRequestParam requestParam) {
        JSONObject jsonObject = checkUser(requestParam.getId(), requestParam.getPassword());
        //如果结果返回为1  则说明验证通过
        if (jsonObject.getInt(ResultConstant.RESULT_RESULT) == 1){
            String password = DigestUtils.md5DigestAsHex(requestParam.getNewPassword().getBytes());
            userMapper.updatePassword(requestParam.getId(),password);
            //修改成功 重新获得得对象返回
            User user = userMapper.selectById(requestParam.getId());
            user.setPassword(requestParam.getNewPassword());
            jsonObject.put(ResultConstant.RESULT_MESSAGE,user);
        }else{
            jsonObject.put(ResultConstant.RESULT_MESSAGE,"请先登录");
        }
        return jsonObject;
    }

    @Override
    public JSONObject changePhone(ChangePhoneRequestParam requestParam) {
        JSONObject jsonObject = checkUser(requestParam.getId(), requestParam.getPassword());
        //为1表示验证成功
        if(jsonObject.getInt(ResultConstant.RESULT_RESULT) == 1){
            //更新电话号码
            userMapper.updatePhone(requestParam.getId(),requestParam.getPhone());
            //返回更新后的用户信息
            jsonObject = userInfo(jsonObject, requestParam);
        }
        //否则验证失败
        else{
            jsonObject.put(ResultConstant.RESULT_MESSAGE,"请先登录");
        }
        return jsonObject;
    }

    @Override
    public JSONObject changeWeChat(ChangeWeChatRequestParam requestParam) {
        JSONObject jsonObject = checkUser(requestParam.getId(), requestParam.getPassword());
        //为1表示验证成功
        if(jsonObject.getInt(ResultConstant.RESULT_RESULT) == 1){
            //更新电话号码
            userMapper.updateWeChat(requestParam.getId(),requestParam.getWechat());
            //返回更新后的用户信息
            jsonObject = userInfo(jsonObject, requestParam);
        }
        //否则验证失败
        else{
            jsonObject.put(ResultConstant.RESULT_MESSAGE,"请先登录");
        }
        return jsonObject;
    }

    @Override
    public JSONObject changeUsername(ChangeUsernameRequestParam requestParam) {
        JSONObject jsonObject = checkUser(requestParam.getId(), requestParam.getPassword());
        //为1表示验证成功
        if(jsonObject.getInt(ResultConstant.RESULT_RESULT) == 1){
            //更新电话号码
            userMapper.updateUsername(requestParam.getId(),requestParam.getUsername());
            //返回更新后的用户信息
            jsonObject = userInfo(jsonObject, requestParam);
        }
        //否则验证失败
        else{
            jsonObject.put(ResultConstant.RESULT_MESSAGE,"请先登录");
        }
        return jsonObject;
    }

    @Override
    public JSONObject changeImage(ChangeImageRequestParam requestParam) {
        JSONObject jsonObject = checkUser(requestParam.getId(), requestParam.getPassword());
        if(jsonObject.getInt(ResultConstant.RESULT_RESULT) == 1){
            userMapper.updateImage(requestParam.getId(),requestParam.getImagePath());
            User user = selectById(requestParam.getId());
            user.setPassword(requestParam.getPassword());
            jsonObject.put(ResultConstant.RESULT_MESSAGE,user);
        }else{
            jsonObject.put(ResultConstant.RESULT_MESSAGE,"请先登录");
        }

        return jsonObject;
    }

    @Override
    public JSONObject collectGoods(String userId, int goodsId) {
        //收藏
        userMapper.collect(userId,goodsId);
        //返回收藏成功信息 1为收藏 0为未收藏
        JSONObject jsonObject = new JSONObject();
        //收藏成功前面显示   取消收藏
        jsonObject.put(ResultConstant.RESULT_COLLECT_KEY,ResultConstant.RESULT_CANCEL);
        return jsonObject;
    }

    @Override
    public JSONObject cancelCollect(String userId, int goodsId) {
        userMapper.cancelCollect(userId,goodsId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ResultConstant.RESULT_COLLECT_KEY,ResultConstant.RESULT_COLLECT);
        return jsonObject;
    }

    @Override
    public com.alibaba.fastjson.JSONObject collectList(String userId) {
        List<Goods> goods = userMapper.collectList(userId);
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        jsonObject.put(ResultConstant.RESULT_MESSAGE,goods);
        return jsonObject;
    }

    //检查用户逻辑
    public JSONObject checkUser(String id, String password){
        String returnPassword = password;
        //登录需要返回的json对象
        JSONObject jsonObject = new JSONObject();
        //通过学号查询id是否存在，存在则进行密码判断，不存在则直接返回错误信息
        User user = selectById(id);
        //查看用户是否为空  不为空 说明用户存在
        if(user != null){
            //密码使用md5进行加密
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            //如果用户存在 检查密码是否正确
            if (password.equals(user.getPassword())) {
                user.setPassword(returnPassword);
                jsonObject.put(ResultConstant.RESULT_RESULT,ResultConstant.RESULT_SUCCESS);
                jsonObject.put(ResultConstant.RESULT_MESSAGE,user);
                return jsonObject;
            }
        }
        //用户名或者密码错误
        jsonObject.put(ResultConstant.RESULT_RESULT,ResultConstant.RESULT_ERROR);
        jsonObject.put(ResultConstant.RESULT_MESSAGE,"用户名或密码错误");
        return jsonObject;
    }

    //返回修改后得用户得信息
    private JSONObject userInfo(JSONObject jsonObject, BaseRequestParam requestParam){
        User user = userMapper.selectById(requestParam.getId());
        user.setPassword(requestParam.getPassword());
        jsonObject.put(ResultConstant.RESULT_MESSAGE,user);
        return jsonObject;
    }

}
