package com.second.hand.transactions.mapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/14 0014
 * Time:15:47
 */
public class Provider {
    /* 批量删除 */
    public String batchDelete(Map map) {
        List<Integer> list = (List<Integer>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM message WHERE id IN (");
        for (int i = 0; i < list.size(); i++) {
            sb.append("'").append(list.get(i)).append("'");
            if (i < list.size() - 1)
                sb.append(",");
        }
        sb.append(")");
        return sb.toString();
    }
}
