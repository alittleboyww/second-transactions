package com.second.hand.transactions.commands.transform.impl;

import com.second.hand.transactions.commands.constant.UserRequestParamConstant;
import com.second.hand.transactions.model.requestparam.ChangeImageRequestParam;
import com.second.hand.transactions.model.requestparam.UserRequestParam;
import net.sf.json.JSONObject;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/3 0003
 * Time:16:23
 */
public class StringToChangeImage extends StringToBaseRequestParam<ChangeImageRequestParam> {
    private StringToChangeImage(){

    }
    static class SingleInstance{
        private static StringToChangeImage stringToChangeImage = new StringToChangeImage();
    }
    public static StringToChangeImage getInstance(){
        return StringToChangeImage.SingleInstance.stringToChangeImage;
    }



    @Override
    public ChangeImageRequestParam getRequestInstance() throws IllegalAccessException, InstantiationException {
        return ChangeImageRequestParam.class.newInstance();
    }

    @Override
    public void analysis(ChangeImageRequestParam analysedObject, String json) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        analysedObject.setImageStr(jsonObject.getString(UserRequestParamConstant.IMAGE_STR));
        analysedObject.setType(jsonObject.getString(UserRequestParamConstant.TYPE));
    }
}
