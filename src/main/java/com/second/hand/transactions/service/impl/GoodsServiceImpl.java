package com.second.hand.transactions.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.second.hand.transactions.commands.constant.ResultConstant;
import com.second.hand.transactions.commands.utils.DateTransfer;
import com.second.hand.transactions.commands.utils.JsonDateValueProcessor;
import com.second.hand.transactions.mapper.GoodsMapper;
import com.second.hand.transactions.mapper.TagMapper;
import com.second.hand.transactions.mapper.UserMapper;
import com.second.hand.transactions.model.Goods;
import com.second.hand.transactions.model.Tag;
import com.second.hand.transactions.model.requestparam.AddGoodsRequestParam;
import com.second.hand.transactions.service.GoodsService;
import com.second.hand.transactions.service.UserService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private UserService userService;

    @Override
    public PageInfo selectList(Integer pageNumber, Integer pageSize) {
        //设置当前页码和当前要显示的数据条数
        PageHelper.startPage(pageNumber,pageSize);
        List<Goods> goods = goodsMapper.goodsList();
        PageInfo<Goods> pageInfo = new PageInfo<>(goods);
        return pageInfo;
    }

    @Transactional
    @Override
    public JSONObject goodsDetail(int goodsId,String userId) {
        Goods goods = goodsMapper.selectById(goodsId);

        //解决date类型反返回格式问题
        JsonConfig jsonConfig = DateTransfer.getJsonConfig();

        HashMap<String,Object> goodsHashMap = new HashMap<>();
        goodsHashMap.put(ResultConstant.RESULT_MESSAGE,goods);

        List<Integer> list = userMapper.selectCollect(userId);
        //如果返回的列表大小为 0 表示当前用户没收藏
        if (list.size() == 0){
            goodsHashMap.put(ResultConstant.RESULT_COLLECT_KEY,ResultConstant.RESULT_COLLECT);
        }
        //否则  查看goodsId是否存在
        else{
            boolean contains = list.contains(goodsId);
            if (contains == false){
                goodsHashMap.put(ResultConstant.RESULT_COLLECT_KEY,ResultConstant.RESULT_COLLECT);
            }else{
                goodsHashMap.put(ResultConstant.RESULT_COLLECT_KEY,ResultConstant.RESULT_CANCEL);
            }
        }
        JSONObject jsonObject = JSONObject.fromObject(goodsHashMap,jsonConfig);
        return jsonObject;
    }


    //TODO：应该需要添加事务处理的  查询资料后进行添加
    @Transactional
    @Override
    public JSONObject addGoods(AddGoodsRequestParam requestParam) {
        JSONObject jsonObject = userService.checkUser(requestParam.getId(), requestParam.getPassword());
        Goods goods = new Goods(requestParam.getGoodsTitle(),requestParam.getImagePath(),requestParam.getGoodsDesc(),requestParam.getUpTime());

        //插入商品 并返回商品id
        goodsMapper.insert(goods);
        List<Tag> tags = tagMapper.select();
        List<String> tagsStr = requestParam.getTags();

        //建立商品标签联系selectList
        for (String tagStr : tagsStr) {
            for (Tag tag : tags) {
                if (tag.getTagName().equals(tagStr)){
                    goodsMapper.insertGoodsTag(goods.getId(),tag.getId());
                }
            }
        }
        //建立商品用户联系
        userMapper.insertUserGoods(requestParam.getId(),goods.getId());
        jsonObject.put(ResultConstant.RESULT_MESSAGE,ResultConstant.RESULT_SUCCESS);
        return jsonObject;
    }

    @Transactional
    @Override
    public PageInfo<Goods> searchGoods(String tag,String text,Integer pageNumber,Integer pageSize) {
        List<Goods> goodsResult = new ArrayList<>();
        List<Goods> goods;
        //如果标签选择的是全部
        if(tag.equals("全部")){
            goods = goodsMapper.goodsList();
        }else{
            int tagId = tagMapper.findTagIdByName(tag);
            goods = goodsMapper.selectGoodsByTag(tagId);
        }
        //需要返回的商品
        //如果添加的筛选条件不为空
        if (text != null && goods != null){
            for (Goods good : goods) {
                if(good.getGoodsTitle().contains(text)){
                    goodsResult.add(good);
                }
            }
        }
        //设置当前页码和当前要显示的数据条数
        PageHelper.startPage(pageNumber,pageSize);
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsResult);
        return pageInfo;
    }
}
