package com.second.hand.transactions.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/5/27 0027
 * Time:11:04
 * Describe:留言实体表
 */
@Data
public class Message implements Serializable {
    //留言id
    private Integer id;

    //留言内容
    private String messageContent;

    //留言时间
    private Date messageTime;

    public Message(){

    }

    public Message(String messageContent, Date messageTime) {
        this.messageContent = messageContent;
        this.messageTime = messageTime;
    }
}
