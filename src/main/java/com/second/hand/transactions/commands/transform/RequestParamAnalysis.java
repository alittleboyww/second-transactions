package com.second.hand.transactions.commands.transform;

import com.second.hand.transactions.model.requestparam.BaseRequestParam;
import net.sf.json.JSONObject;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/5/27 0027
 * Time:20:05
 */
public interface RequestParamAnalysis<T extends BaseRequestParam> {
    T analysisRequestParam(String json);
}
