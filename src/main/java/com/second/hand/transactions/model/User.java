package com.second.hand.transactions.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.second.hand.transactions.commands.utils.RegexpUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/5/27 0027
 * Time:10:52
 * Describe:用户实体表
 */
@Data
public class User implements Serializable {
    //用户学号
    private String id;

    //用户手机号
    private String phone;

    //用户微信
    private String wechat;

    //用户密码
    private String password;

    //用户名
    private String username;

    //头像路径
    private String imagePath;

    //留言列表
    private List<Message> messages;

    public User(){

    }

    public User(String id,String phone, String wechat, String password, String username, String imagePath) {
        this.phone = phone;
        this.wechat = wechat;
        this.password = password;
        this.username = username;
        this.imagePath = imagePath;
        this.id = id;
    }
}
