package com.second.hand.transactions.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.second.hand.transactions.commands.constant.ResultConstant;
import com.second.hand.transactions.mapper.GoodsMapper;
import com.second.hand.transactions.model.Goods;
import com.second.hand.transactions.service.GoodsService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/4 0004
 * Time:15:30
 */
@Controller
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public PageInfo selectList(Integer pageNumber, Integer pageSize) {
        //设置当前页码和当前要显示的数据条数
        PageHelper.startPage(pageNumber,pageSize);
        List<Goods> goods = goodsMapper.goodsList();
        PageInfo<Goods> pageInfo = new PageInfo<>(goods);
        return pageInfo;
    }

    @Override
    public JSONObject goodsDetail(int goodsId) {
        Goods goods = goodsMapper.selectById(goodsId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ResultConstant.RESULT_MESSAGE,goods);
        return jsonObject;
    }
}
