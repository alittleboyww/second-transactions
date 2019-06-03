package com.second.hand.transactions.commands.transform.impl;

import com.second.hand.transactions.commands.constant.UserRequestParamConstant;
import com.second.hand.transactions.model.requestparam.BaseRequestParam;
import com.second.hand.transactions.model.requestparam.ChangePasswordRequestParam;
import net.sf.json.JSONObject;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/2 0002
 * Time:15:25
 */
public class StringToChangePassword extends StringToBaseRequestParam<ChangePasswordRequestParam> {

    private StringToChangePassword(){

    }
    static class SingleInstance{
        private static StringToChangePassword stringToChangePassword = new StringToChangePassword();
    }
    public static StringToChangePassword getInstance(){
        return StringToChangePassword.SingleInstance.stringToChangePassword;
    }

    @Override
    public ChangePasswordRequestParam getRequestInstance() throws IllegalAccessException, InstantiationException {
        return ChangePasswordRequestParam.class.newInstance();
    }

    @Override
    public void analysis(ChangePasswordRequestParam analysedObject, String json) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        analysedObject.setNewPassword(jsonObject.getString(UserRequestParamConstant.NEW_PASSWORD));
    }
}
