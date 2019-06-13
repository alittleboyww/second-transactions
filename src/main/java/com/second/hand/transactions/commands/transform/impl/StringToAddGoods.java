package com.second.hand.transactions.commands.transform.impl;

import com.second.hand.transactions.commands.constant.GoodsRequestParamConstant;
import com.second.hand.transactions.commands.constant.UserRequestParamConstant;
import com.second.hand.transactions.model.requestparam.AddGoodsRequestParam;
import com.second.hand.transactions.model.requestparam.AddMessageRequestParam;
import com.second.hand.transactions.model.requestparam.ChangeWeChatRequestParam;
import net.sf.json.JSONObject;

import java.util.Date;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/13 0013
 * Time:15:09
 */
public class StringToAddGoods extends StringToBaseRequestParam<AddGoodsRequestParam> {
    private StringToAddGoods(){

    }
    static class SingleInstance{
        private static StringToAddGoods stringToAddGoods = new StringToAddGoods();
    }
    public static StringToAddGoods getInstance(){
        return StringToAddGoods.SingleInstance.stringToAddGoods;
    }

    @Override
    public AddGoodsRequestParam getRequestInstance() throws IllegalAccessException, InstantiationException {
        return AddGoodsRequestParam.class.newInstance();
    }

    @Override
    public void analysis(AddGoodsRequestParam analysedObject, String json) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        analysedObject.setGoodsTitle(jsonObject.getString(GoodsRequestParamConstant.GOODS_TITLE));
        analysedObject.setGoodsDesc(jsonObject.getString(GoodsRequestParamConstant.GOODS_DESC));
        analysedObject.setImageStr(jsonObject.getString(GoodsRequestParamConstant.IMAGE_STR));
        analysedObject.setType(jsonObject.getString(GoodsRequestParamConstant.TYPE));
        Date date = new Date(jsonObject.getLong(GoodsRequestParamConstant.UP_TIME));
        analysedObject.setUpTime(date);
        analysedObject.setTags(jsonObject.getJSONArray(GoodsRequestParamConstant.TAGS));
    }
}
