package com.second.hand.transactions.model.requestparam;

import lombok.Data;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/3 0003
 * Time:9:13
 */
@Data
public class ChangeUsernameRequestParam extends BaseRequestParam{
    //用户名
    private String username;
}
