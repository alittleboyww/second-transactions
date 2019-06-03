package com.second.hand.transactions.model.requestparam;

import lombok.Data;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/3 0003
 * Time:8:35
 */
@Data
public class ChangeWeChatRequestParam extends BaseRequestParam{
    //修改后的微信号
    private String wechat;
}
