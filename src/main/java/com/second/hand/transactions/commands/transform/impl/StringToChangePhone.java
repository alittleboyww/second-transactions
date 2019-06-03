package com.second.hand.transactions.commands.transform.impl;

import com.second.hand.transactions.commands.constant.UserRequestParamConstant;
import com.second.hand.transactions.model.requestparam.ChangePhoneRequestParam;
import net.sf.json.JSONObject;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/3 0003
 * Time:8:10
 */
public class StringToChangePhone extends StringToBaseRequestParam<ChangePhoneRequestParam> {


    private StringToChangePhone(){

    }
    static class SingleInstance{
        private static StringToChangePhone stringToChangePhone = new StringToChangePhone();
    }
    public static StringToChangePhone getInstance(){
        return StringToChangePhone.SingleInstance.stringToChangePhone;
    }

    @Override
    public ChangePhoneRequestParam getRequestInstance() throws IllegalAccessException, InstantiationException {
        return ChangePhoneRequestParam.class.newInstance();
    }

    @Override
    public void analysis(ChangePhoneRequestParam analysedObject, String json) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        analysedObject.setPhone(jsonObject.getString(UserRequestParamConstant.PHONE));
    }
}
