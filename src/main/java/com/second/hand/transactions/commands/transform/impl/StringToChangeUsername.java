package com.second.hand.transactions.commands.transform.impl;

import com.second.hand.transactions.commands.constant.UserRequestParamConstant;
import com.second.hand.transactions.model.requestparam.ChangeUsernameRequestParam;
import net.sf.json.JSONObject;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/3 0003
 * Time:9:14
 */
public class StringToChangeUsername extends StringToBaseRequestParam<ChangeUsernameRequestParam> {
    private StringToChangeUsername(){

    }
    static class SingleInstance{
        private static StringToChangeUsername stringToChangeUsername = new StringToChangeUsername();
    }
    public static StringToChangeUsername getInstance(){
        return StringToChangeUsername.SingleInstance.stringToChangeUsername;
    }

    @Override
    public ChangeUsernameRequestParam getRequestInstance() throws IllegalAccessException, InstantiationException {
        return ChangeUsernameRequestParam.class.newInstance();
    }
    @Override
    public void analysis(ChangeUsernameRequestParam analysedObject, String json) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        analysedObject.setUsername(jsonObject.getString(UserRequestParamConstant.USERNAME));
    }
}
