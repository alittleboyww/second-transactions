package com.second.hand.transactions.mapper;

import com.second.hand.transactions.model.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/2 0002
 * Time:9:22
 */
@Mapper
@Repository
public interface MessageMapper {

    @Insert("insert into message(message_content,message_time) values(#{messageContent},#{messageTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Message message);

    @Insert("insert into goods_message(user_id,goods_id,message_id) values(#{userId},#{goodsId},#{messageId})")
    void insertMessage(@Param("userId") String userId, @Param("goodsId") int goodsId, @Param("messageId") int messageId);
}
