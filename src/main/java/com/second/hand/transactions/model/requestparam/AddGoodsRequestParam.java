package com.second.hand.transactions.model.requestparam;

import com.second.hand.transactions.model.Tag;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/13 0013
 * Time:15:00
 */
@Data
public class AddGoodsRequestParam extends BaseRequestParam {
    //商品标题
    private String goodsTitle;

    //商品图片 字符串
    private String imageStr;

    //商品图片类型
    private String type;

    //图片路径
    private String imagePath;

    //商品描述
    private String goodsDesc;

    //商品信息上传时间
    private Date upTime;

    private List<String> tags;
}
