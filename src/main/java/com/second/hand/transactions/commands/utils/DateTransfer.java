package com.second.hand.transactions.commands.utils;

import net.sf.json.JsonConfig;

import java.util.Date;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/11 0011
 * Time:19:46
 */
public class DateTransfer {
    public static JsonConfig getJsonConfig(){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        return jsonConfig;
    }
}
