package com.second.hand.transactions.commands.utils;

import com.second.hand.transactions.commands.constant.OtherConstant;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/11 0011
 * Time:15:39
 */
public class JsonDateValueProcessor implements JsonValueProcessor {
    private String datePattern = OtherConstant.DATA_FORMAT;

    @Override
    public Object processArrayValue(Object value, JsonConfig config) {
        return process(value);
    }

    @Override
    public Object processObjectValue(String arg0, Object value, JsonConfig config) {
        return process(value);
    }
    private Object process(Object value){
        //遇到类型为日期的，就自动转换成“yyyy-MM-dd HH:mm:ss”格式的字符串
        if(value instanceof Date){
            SimpleDateFormat sdf = new SimpleDateFormat(datePattern, Locale.UK);
            return sdf.format(value);
        }
        return value == null ? "" : value.toString();
    }
}
