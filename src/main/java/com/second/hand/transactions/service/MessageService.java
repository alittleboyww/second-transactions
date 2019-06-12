package com.second.hand.transactions.service;

import com.second.hand.transactions.model.requestparam.AddMessageRequestParam;
import net.sf.json.JSONObject;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/12 0012
 * Time:9:55
 */
public interface MessageService {
    JSONObject addMessage(AddMessageRequestParam requestParam);
}
