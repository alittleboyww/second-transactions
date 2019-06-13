package com.second.hand.transactions.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/5/27 0027
 * Time:10:54
 * Describe:商品实体表
 */
@Data
public class Goods implements Serializable {
    //商品id
    private Integer id;

    //商品标题
    private String goodsTitle;

    //TODO:这里有需要的话可以改为一个数组，保证一个商品可以展示N张图片，但需要修改数据表联系。这里暂时使用一张
    //商品图片
    private String imagePath;

    //商品描述
    private String goodsDesc;

    //商品信息上传时间
    private Date upTime;

    private List<Tag> tags;

    private List<UserMessage> userMessages;

    private User user;

    public Goods(){

    }

    public Goods(String goodsTitle, String imagePath, String goodsDesc, Date upTime) {
        this.goodsTitle = goodsTitle;
        this.imagePath = imagePath;
        this.goodsDesc = goodsDesc;
        this.upTime = upTime;
    }
}
