package com.second.hand.transactions.web.controller;

import com.second.hand.transactions.commands.transform.impl.StringToAddMessage;
import com.second.hand.transactions.model.requestparam.AddMessageRequestParam;
import com.second.hand.transactions.service.MessageService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/12 0012
 * Time:9:44
 */
@RestController
public class MessageController {
    Logger logger = LoggerFactory.getLogger(MessageController.class);


    @Autowired
    private MessageService messageService;
    @PostMapping("/addMessage")
    public JSONObject message(@RequestParam("message") String message){
        AddMessageRequestParam requestParam = StringToAddMessage.analysisRequestParam(message);
        JSONObject jsonObject = messageService.addMessage(requestParam);
        return jsonObject;
    }
}
