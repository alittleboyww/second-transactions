package com.second.hand.transactions.model.requestparam;

import lombok.Data;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/2 0002
 * Time:15:28
 */
@Data
public class ChangePasswordRequestParam extends BaseRequestParam {
    //修改后的密码
    private String newPassword;
}
