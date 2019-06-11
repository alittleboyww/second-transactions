package com.second.hand.transactions.commands.constant;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/5/27 0027
 * Time:21:09
 */
public class ResultConstant {
    //1 表示成功
    public static final int RESULT_SUCCESS = 1;
    //0 表示失败
    public static final int RESULT_ERROR = 0;

    //1 表示已经收藏
    public static final int RESULT_CANCEL = 1;

    //0 表示未收藏
    public static final int RESULT_COLLECT = 0;

    //返回结果的key
    public static final String RESULT_RESULT = "result";

    //返回信息的key
    public static final String RESULT_MESSAGE = "message";

    //收藏信息
    public static final String RESULT_COLLECT_KEY = "collect";
}
