package com.second.hand.transactions.commands.transform.impl;

import com.second.hand.transactions.commands.transform.RequestParamAnalysis;
import com.second.hand.transactions.model.requestparam.LoginRequestParam;
import net.sf.json.JSONObject;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/2 0002
 * Time:13:55
 */
public class StringToLoginInfo extends StringToBaseRequestParam<LoginRequestParam> {

    private StringToLoginInfo(){

    }
    static class SingleInstance{
        private static StringToLoginInfo stringToLoginInfo = new StringToLoginInfo();
    }
    public static StringToLoginInfo getInstance(){
        return StringToLoginInfo.SingleInstance.stringToLoginInfo;
    }

    @Override
    public LoginRequestParam getRequestInstance() throws IllegalAccessException, InstantiationException {
        return LoginRequestParam.class.newInstance();
    }
}
