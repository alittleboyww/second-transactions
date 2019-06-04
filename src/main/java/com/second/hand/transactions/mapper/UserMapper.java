package com.second.hand.transactions.mapper;

import com.second.hand.transactions.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/5/27 0027
 * Time:11:09
 */
@Mapper
@Repository
public interface UserMapper {
    //添加用户
    @Insert("insert into user(id,phone,wechat,password,username,image_path) values(#{id},#{phone},#{wechat},#{password},#{username},#{imagePath})")
    void insert(User user);

    //查询ID用户
    @Select("select * from user where id=#{id}")
    User selectById(@Param("id") String id);

    //通过Phone查询用户
    @Select("select * from user where phone=#{phone}")
    User selectByPhone(@Param("phone") String phone);

    //通过username查询用户
    @Select("select * from user where username=#{username}")
    User selectByUsername(@Param("username") String username);

    //通过weChat查询用户
    @Select("select * from user where wechat=#{wechat}")
    User selectByWechat(@Param("wechat") String wechat);

    //修改密码
    @Update("update user set password=#{newPassword} where id=#{id}")
    void updatePassword(@Param("id") String id, @Param("newPassword") String newPassword);

    //修改电话号码
    @Update("update user set phone=#{phone} where id=#{id}")
    void updatePhone(@Param("id") String id, @Param("phone") String phone);

    //修改微信
    @Update("update user set wechat=#{wechat} where id=#{id}")
    void updateWeChat(@Param("id") String id, @Param("wechat") String wechat);

    //修改用户名
    @Update("update user set username=#{username} where id=#{id}")
    void updateUsername(@Param("id") String id,@Param("username") String username);

    //修改头像
    @Update("update user set image_path=#{imagePath} where id=#{id}")
    void updateImage(@Param("id") String id,@Param("imagePath") String imagePath);
}
