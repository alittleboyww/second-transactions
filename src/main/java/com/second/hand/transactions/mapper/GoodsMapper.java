package com.second.hand.transactions.mapper;

import com.second.hand.transactions.model.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/5/29 0029
 * Time:20:08
 */
@Mapper
@Repository
public interface GoodsMapper {

    //通过商品id 查询出商品的信息
    @Select("select * from goods where id=#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "up_time", property = "upTime"),
            @Result(column = "goods_title", property = "goodsTitle"),
            @Result(column = "image_path", property = "imagePath"),
            @Result(column = "id", property = "tags", javaType = List.class, many = @Many(select = "com.second.hand.transactions.mapper.GoodsMapper.getTagByGoodsId")),
            @Result(column = "id", property = "userMessages", javaType = List.class, many = @Many(select = "com.second.hand.transactions.mapper.GoodsMapper.getUserMessageByGoodsId")),
            @Result(column = "id", property = "user", javaType = User.class, many = @Many(select = "com.second.hand.transactions.mapper.GoodsMapper.getUserInfoByGoodsId")),
    })
    Goods selectById(@Param("id") Integer id);

    //通过商品ID查询标签
    @Select("select t.id,t.tag_name from tag as t left join goods_tag as gt on t.id=gt.tag_id left join goods as g on gt.goods_id=g.id where g.id=#{id}")
    @Results({
            @Result(column = "tag_name", property = "tagName"),
    })
    Tag getTagByGoodsId(@Param("id") Integer id);


    //通过商品id查询对应 留言信息及用户信息
    @Select("SELECT u.username,u.image_path,m.message_content,m.message_time FROM USER AS u LEFT JOIN goods_message AS gm ON u.id=gm.user_id  LEFT JOIN message AS m ON m.id=gm.message_id LEFT JOIN goods AS g ON gm.goods_id=g.id WHERE g.id=#{id} ORDER BY m.message_time DESC")
    @Results({
            @Result(column = "username", property = "username"),
            @Result(column = "image_path", property = "imagePath"),
            @Result(column = "message_content", property = "messageContent"),
            @Result(column = "message_time", property = "messageTime")
    })
    UserMessage getUserMessageByGoodsId(@Param("id") Integer id);

    //查询留言信息
    //TODO:这里还没用到
    @Select("select m.id,m.message_content,m.message_time from message as m left join goods_message as gm on m.id=gm.message_id left join goods as g on g.id=gm.goods_id where g.id=#{id}")
    @Results({
            @Result(column = "message_content", property = "messageContent"),
            @Result(column = "message_time", property = "messageTime"),
    })
    Message getMessageByGoodsId(@Param("id") Integer id);

    @Insert("insert into goods(goods_title,image_path,goods_desc,up_time) values(#{goodsTitle},#{imagePath},#{goodsDesc},#{upTime})")
    //插入商品返回id
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Goods goods);

    @Insert("insert into goods_tag(goods_id,tag_id) values(#{goodsId},#{tagId})")
    //插入商品后 需要与 tag建立关联
    void insertGoodsTag(@Param("goodsId") int goodsId, @Param("tagId") int tagId);


    @Select("select * from goods order by up_time desc")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "tags", javaType = List.class, many = @Many(select = "com.second.hand.transactions.mapper.GoodsMapper.getTagByGoodsId")),
            @Result(column = "id", property = "user", javaType = User.class, many = @Many(select = "com.second.hand.transactions.mapper.GoodsMapper.getUserByGoodsId")),
    })
    List<Goods> goodsList();

    @Select("select u.username,u.image_path from user as u left join user_goods as ug on u.id=ug.user_id left join goods as g on g.id=ug.goods_id where g.id=#{id}")
    User getUserByGoodsId(@Param("id") int id);

    @Select("select u.username,u.wechat,u.phone from user as u left join user_goods as ug on u.id=ug.user_id left join goods as g on g.id=ug.goods_id where g.id=#{id}")
    User getUserInfoByGoodsId(@Param("id") int id);

    @Select("select count(1) from goods order by up_time desc")
    int goodsCount();


}
