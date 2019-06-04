package com.second.hand.transactions.service;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/4 0004
 * Time:15:28
 */
public interface GoodsService {
    PageInfo selectList(Integer pageNumber,Integer pageSize);
}
