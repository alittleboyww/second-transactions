package com.second.hand.transactions.commands.transform.impl;

import com.second.hand.transactions.commands.constant.UserRequestParamConstant;
import com.second.hand.transactions.commands.transform.RequestParamAnalysis;
import com.second.hand.transactions.model.User;
import com.second.hand.transactions.model.requestparam.UserRequestParam;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/5/27 0027
 * Time:20:08
 */
//下面标签用于再测试的时候
//@Component
public class StringToUser extends StringToBaseRequestParam<UserRequestParam> {

    private StringToUser(){

    }
    static class SingleInstance{
        private static StringToUser stringToUser = new StringToUser();
    }
    public static StringToUser getInstance(){
        return SingleInstance.stringToUser;
    }

    @Override
    public UserRequestParam getRequestInstance() throws IllegalAccessException, InstantiationException {
        return UserRequestParam.class.newInstance();
    }

    public void analysis(UserRequestParam requestParam, String json) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        requestParam.setUsername(jsonObject.getString(UserRequestParamConstant.USERNAME));
        requestParam.setWechat(jsonObject.getString(UserRequestParamConstant.WECHAT));
        requestParam.setPhone(jsonObject.getString(UserRequestParamConstant.PHONE));
        requestParam.setImageStr(jsonObject.getString(UserRequestParamConstant.IMAGE_STR));
        requestParam.setType(jsonObject.getString(UserRequestParamConstant.TYPE));
    }
}
