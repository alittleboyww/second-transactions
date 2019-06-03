package com.second.hand.transactions.model.requestparam;

import lombok.Data;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/3 0003
 * Time:15:39
 */
@Data
public class ChangeImageRequestParam extends BaseRequestParam {
    //图片字符
    private String imageStr;

    //图片类型
    private String type;

    //图片存储路径
    private String imagePath;
}
