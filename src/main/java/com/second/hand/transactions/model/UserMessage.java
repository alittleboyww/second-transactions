package com.second.hand.transactions.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/2 0002
 * Time:9:36
 */
//留言信息
@Data
public class UserMessage implements Serializable {
    //用户名
    private String username;

    //头像信息
    private String imagePath;

    //留言内容
    private String messageContent;

    //留言时间
    private String messageTime;
}
