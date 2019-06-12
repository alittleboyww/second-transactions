package com.second.hand.transactions.model.requestparam;

import lombok.Data;

import java.util.Date;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/12 0012
 * Time:9:56
 */
@Data
public class AddMessageRequestParam {
    //用户id
    private String userId;
    //商品id
    private int goodsId;
    //留言内容
    private String messageContent;

    //留言时间
    private Date messageTime;
}
