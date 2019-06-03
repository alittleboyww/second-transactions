package com.second.hand.transactions.commands.transform.impl;

import com.second.hand.transactions.commands.constant.UserRequestParamConstant;
import com.second.hand.transactions.commands.transform.RequestParamAnalysis;
import com.second.hand.transactions.model.User;
import com.second.hand.transactions.model.requestparam.BaseRequestParam;
import net.sf.json.JSONObject;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/2 0002
 * Time:15:29
 */
public abstract class StringToBaseRequestParam<T extends BaseRequestParam> implements RequestParamAnalysis {

    public abstract T getRequestInstance() throws IllegalAccessException, InstantiationException;
    @Override
    public T analysisRequestParam(String json) {
        T analysedObject = null;
        //获取一个请求对象
        try {
            analysedObject = getRequestInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.fromObject(json);
        analysedObject.setId(jsonObject.getString(UserRequestParamConstant.ID));
        analysedObject.setPassword(jsonObject.getString(UserRequestParamConstant.PASSWORD));
        analysis(analysedObject,json);
        return analysedObject;
    }
    public void analysis(T analysedObject,String json){

    }
}
