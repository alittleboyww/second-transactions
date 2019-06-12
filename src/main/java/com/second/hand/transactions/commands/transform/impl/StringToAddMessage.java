package com.second.hand.transactions.commands.transform.impl;

import com.second.hand.transactions.commands.constant.UserRequestParamConstant;
import com.second.hand.transactions.commands.transform.RequestParamAnalysis;
import com.second.hand.transactions.model.requestparam.AddMessageRequestParam;
import com.second.hand.transactions.model.requestparam.BaseRequestParam;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/12 0012
 * Time:9:59
 */
public class StringToAddMessage{
    public static AddMessageRequestParam analysisRequestParam(String json) {
        AddMessageRequestParam analysedObject = new AddMessageRequestParam();
        JSONObject jsonObject = JSONObject.fromObject(json);
        analysedObject.setUserId(jsonObject.getString("userId"));
        analysedObject.setGoodsId(jsonObject.getInt("goodsId"));
        analysedObject.setMessageContent(jsonObject.getString("messageContent"));
        Date date = new Date();
        date.setTime(jsonObject.getLong("messageTime"));
        analysedObject.setMessageTime(date);
        return analysedObject;
    }
}
