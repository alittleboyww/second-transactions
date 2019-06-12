package com.second.hand.transactions.service.impl;

import com.second.hand.transactions.commands.constant.ResultConstant;
import com.second.hand.transactions.mapper.MessageMapper;
import com.second.hand.transactions.model.Message;
import com.second.hand.transactions.model.requestparam.AddMessageRequestParam;
import com.second.hand.transactions.service.MessageService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/12 0012
 * Time:10:04
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Override
    public JSONObject addMessage(AddMessageRequestParam requestParam) {
        Message message = new Message(requestParam.getMessageContent(), requestParam.getMessageTime());
        messageMapper.insert(message);
        messageMapper.insertMessage(requestParam.getUserId(),requestParam.getGoodsId(),message.getId());
        //获取
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ResultConstant.RESULT_MESSAGE,ResultConstant.RESULT_SUCCESS);
        return jsonObject;
    }
}
