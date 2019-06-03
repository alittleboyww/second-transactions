package com.second.hand.transactions.model.requestparam;

import lombok.Data;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/3 0003
 * Time:8:08
 */
@Data
public class ChangePhoneRequestParam extends BaseRequestParam {
    //修改后得电话号码
    private String phone;
}
