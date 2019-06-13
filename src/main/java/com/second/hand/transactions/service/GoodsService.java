package com.second.hand.transactions.service;

import com.github.pagehelper.PageInfo;
import com.second.hand.transactions.model.requestparam.AddGoodsRequestParam;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/4 0004
 * Time:15:28
 */
public interface GoodsService {

    //旧商品 列表分页
    PageInfo selectList(Integer pageNumber,Integer pageSize);

    //商品详情
    JSONObject goodsDetail(int goodsId,String userId);

    //添加商品
    JSONObject addGoods(AddGoodsRequestParam requestParam);
}
