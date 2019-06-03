package com.second.hand.transactions;

import com.second.hand.transactions.commands.constant.ResultConstant;
import com.second.hand.transactions.commands.utils.ImageTransformUtils;
import com.second.hand.transactions.mapper.GoodsMapper;
import com.second.hand.transactions.mapper.MessageMapper;
import com.second.hand.transactions.mapper.TagMapper;
import com.second.hand.transactions.mapper.UserMapper;
import com.second.hand.transactions.model.Goods;
import com.second.hand.transactions.model.Message;
import com.second.hand.transactions.model.Tag;
import com.second.hand.transactions.model.User;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/5/27 0027
 * Time:13:57
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TransactionsApplicationTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private TagMapper tagMapper;


    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    //@Autowired
    //private RequestParamAnalysis stringToUser;


    @Before
    public void setUp() throws Exception{
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    //测试插入用户功能
    @Test
    public void testAddUser(){
        User user = new User();
        user.setPhone("15607090915");
        user.setWechat("573324982@qq.com");
        user.setPassword("123456");
        user.setUsername("刘某");
        userMapper.insert(user);
    }

    //测试通过id查询用户的功能
    @Test
    public void testSelectById(){
        User user = new User();
        user = userMapper.selectById("1");
        System.out.println(user.toString());
    }

    //测试通过手机号查询用户的功能
    @Test
    public void testSelectByPhone(){
        User user = new User();
        user = userMapper.selectByPhone("15607090915");
        System.out.println(user.toString());
    }
    //<!--阿里云OSS-->
    //测试数据传输
    @Test
    public void testRegister() throws Exception {
        //图片编码字符串
        String imageStr = ImageTransformUtils.getImageStr("E:\\Android\\image\\Absolutely.jpg");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","GBMONKEY");
        jsonObject.put("phone","15607090915");
        jsonObject.put("wechat","573324982@qq.com");
        jsonObject.put("password","123456");
        jsonObject.put("imageStr",imageStr);
        jsonObject.put("type",".jpg");
        String responseString = mvc.perform(MockMvcRequestBuilders.post("/register").contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("userInfo", jsonObject.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
        System.out.println("返回的json数据为：" + responseString);
    }

    //测试传输json装string对象
    @Test
    public void testRegisterJson() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","刘某某");
        jsonObject.put("phone","45678978945");
        jsonObject.put("wechat","573324982");
        jsonObject.put("password","123456");
        System.out.println(jsonObject.toString());
        String userInfo = mvc.perform(MockMvcRequestBuilders.post("/register")
                .param("userInfo", jsonObject.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
        System.out.println("响应后的json数据" + userInfo);
    }


    //测试服务器中String转json对象 简易版
    @Test
    public void testStringToUser(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","刘某某");
        jsonObject.put("phone","45678978945");
        jsonObject.put("wechat","573324982");
        jsonObject.put("password","123456");
        //stringToUser.analysisRequestParam(jsonObject.toString());
    }

    //测试服务器中String转json对象 带有其他参数
    @Test
    public void testStringToUserParams(){

    }

    //测试JSONObject是否为空
    @Test
    public void testJSONObject(){
        net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
        jsonObject.put("notEmpty","notEmpty");
        System.out.println(jsonObject.isEmpty());
    }

    //测试图片转换
    @Test
    public void testImageTransform(){
        String imageStr = ImageTransformUtils.getImageStr("E:\\Android\\image\\Absolutely.jpg");
        boolean b = ImageTransformUtils.GenerateImage(imageStr,"E:\\Android\\image\\test.jpg");
        System.out.println(b);
    }


    //测试通过商品id查询出留言信息
    @Test
    public void testGoodsTag(){
        Goods goods = goodsMapper.selectById(1);
        System.out.println(goods);
        Date upTime = goods.getUpTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(format.format(upTime));
    }

    //测试插入商品返回商品id
    @Test
    public void testInsertMessage(){
        Message message = new Message();
        message.setMessageContent("插入数据返回id");
        message.setMessageTime(new Date());
        messageMapper.insert(message);
        System.out.println(message.getId());
    }

    //测试插入商品留言信息
    @Test
    public void testInsertGoodsMessage(){
        messageMapper.insertMessage("4",1,3);
    }

    // 测试插入商品
    @Test
    public void testInsertGoods(){
        Goods goods = new Goods();
        goods.setGoodsTitle("优衣库");
        goods.setGoodsDesc("8成新，感觉棒棒哒");
        goods.setImagePath("http://localhost:8888/static/....");
        goods.setUpTime(new Date());
        goodsMapper.insert(goods);
    }

    //测试查询所有的Tag
    @Test
    public void testSelectTag(){
        List<Tag> list = tagMapper.select();
        for (Tag tag : list) {
            System.out.println(tag.toString());
        }
    }

    //测试插入GoodsTag
    @Test
    public void testInsertGoodsTag(){
        goodsMapper.insertGoodsTag(2,3);
    }



    //测试登录

    /**
     * @Param JSONObject 其中包含 username 和 password
     */
    @Test
    public void testLogin() throws Exception {
        JSONObject jsonObject = new JSONObject();
        //学号
        jsonObject.put("id","8000116159");
        jsonObject.put("password","123456");
        String loginInfo = mvc.perform(MockMvcRequestBuilders.post("/login").contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("loginInfo", jsonObject.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
        JSONObject jsonObject1 = new JSONObject(loginInfo);
        int result = jsonObject1.getInt(ResultConstant.RESULT_RESULT);
        JSONObject jsonObject2 = jsonObject1.getJSONObject(ResultConstant.RESULT_MESSAGE);
        System.out.println(result + "  " + jsonObject2.getString("username"));
    }


    /**
     * 修改密码 参数为JSONObject 其中包括 id,password,changedPassword
     */
    @Test
    public void testUpdateInfo() throws Exception {
        //这里再Android 客户端需要使用 SharedPreferences 来保持登录状态

        JSONObject jsonObject = new JSONObject();
        //学号  这些是通过SharedPreferences中获取过来的
        jsonObject.put("id","8000116159");
        jsonObject.put("password","123456");

        jsonObject.put("newPassword","987654");
        String loginInfo = mvc.perform(MockMvcRequestBuilders.post("/changePassword").contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("changePassword", jsonObject.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
        System.out.println(loginInfo);
    }

    @Test
    public void testUpdatePhone() throws Exception {
        //这里再Android 客户端需要使用 SharedPreferences 来保持登录状态

        JSONObject jsonObject = new JSONObject();
        //学号  这些是通过SharedPreferences中获取过来的
        jsonObject.put("id","8000116159");
        jsonObject.put("password","123456");

        jsonObject.put("phone","13361616385");

        String loginInfo = mvc.perform(MockMvcRequestBuilders.post("/changePhone").contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("changePhone", jsonObject.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
        System.out.println(loginInfo);
    }

    @Test
    public void testUpdateWeChat() throws Exception {
        //这里再Android 客户端需要使用 SharedPreferences 来保持登录状态

        JSONObject jsonObject = new JSONObject();
        //学号  这些是通过SharedPreferences中获取过来的
        jsonObject.put("id","8000116159");
        jsonObject.put("password","123456");

        jsonObject.put("wechat","150468565895");

        String loginInfo = mvc.perform(MockMvcRequestBuilders.post("/changeWeChat").contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("changeWeChat", jsonObject.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
        System.out.println(loginInfo);
    }

    @Test
    public void testUpdateUsername() throws Exception {
        //这里再Android 客户端需要使用 SharedPreferences 来保持登录状态

        JSONObject jsonObject = new JSONObject();
        //学号  这些是通过SharedPreferences中获取过来的
        jsonObject.put("id","8000116159");
        jsonObject.put("password","123456");

        jsonObject.put("username","MYUSERNAME");

        String loginInfo = mvc.perform(MockMvcRequestBuilders.post("/changeUsername").contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("changeUsername", jsonObject.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
        System.out.println(loginInfo);
    }
}