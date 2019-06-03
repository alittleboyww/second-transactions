package com.second.hand.transactions.model.requestparam;

import lombok.Data;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/5/28 0028
 * Time:10:58
 */
@Data
public class UserRequestParam extends BaseRequestParam{

    private String username;

    private String phone;

    private String wechat;

    private String imageStr;

    private String type;
}
