package com.second.hand.transactions.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/5/27 0027
 * Time:11:03
 * Describe:标签实体表
 */
@Data
public class Tag implements Serializable {
    //标签id
    private Integer id;

    //标签名
    private String tagName;

}
