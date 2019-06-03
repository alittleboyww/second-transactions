package com.second.hand.transactions.commands.transform.impl;

import com.second.hand.transactions.commands.constant.ResultConstant;
import com.second.hand.transactions.commands.constant.UserRequestParamConstant;
import com.second.hand.transactions.model.requestparam.ChangeWeChatRequestParam;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/3 0003
 * Time:8:36
 */
public class StringToChangeWeChat extends StringToBaseRequestParam<ChangeWeChatRequestParam> {

    private StringToChangeWeChat(){

    }
    static class SingleInstance{
        private static StringToChangeWeChat stringToChangeWeChat = new StringToChangeWeChat();
    }
    public static StringToChangeWeChat getInstance(){
        return StringToChangeWeChat.SingleInstance.stringToChangeWeChat;
    }



    @Override
    public ChangeWeChatRequestParam getRequestInstance() throws IllegalAccessException, InstantiationException {
        return ChangeWeChatRequestParam.class.newInstance();
    }

    @Override
    public void analysis(ChangeWeChatRequestParam analysedObject, String json) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        analysedObject.setWechat(jsonObject.getString(UserRequestParamConstant.WECHAT));
    }
}
