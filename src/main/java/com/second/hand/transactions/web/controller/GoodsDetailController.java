package com.second.hand.transactions.web.controller;

import com.second.hand.transactions.commands.constant.PathConstant;
import com.second.hand.transactions.commands.transform.impl.StringToAddGoods;
import com.second.hand.transactions.commands.utils.ImageTransformUtils;
import com.second.hand.transactions.model.requestparam.AddGoodsRequestParam;
import com.second.hand.transactions.service.GoodsService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/4 0004
 * Time:20:32
 */
@RestController
public class GoodsDetailController {

    Logger logger = LoggerFactory.getLogger(GoodsDetailController.class);

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ChangeInfoController changeInfoController;

    @GetMapping("/goodsDetail")
    public JSONObject goodsDetail(@RequestParam("goodsId") Integer goodsId,@RequestParam("userId")String userId){
        JSONObject jsonObject = goodsService.goodsDetail(goodsId,userId);
        return jsonObject;
    }


    @PostMapping("/addGoods")
    public JSONObject goodsDetail(@RequestParam("goodsInfo") String goodsInfo, HttpServletRequest request){
        System.out.println(goodsInfo);
        AddGoodsRequestParam requestParam = StringToAddGoods.getInstance().analysisRequestParam(goodsInfo);
        //如果上传图片不为空则 不需要设置路径
        if(requestParam.getType() != null && !requestParam.getType().equals("")){
            logger.info("上传商品插入图片");
            //设置图片存储得路径
            requestParam.setImagePath(changeInfoController.saveImage(requestParam.getType(),requestParam.getImageStr(),request));
        }
        JSONObject jsonObject = goodsService.addGoods(requestParam);
        return jsonObject;
    }
}
