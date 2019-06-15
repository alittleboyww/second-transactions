package com.second.hand.transactions.mapper;

import com.second.hand.transactions.model.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/2 0002
 * Time:10:44
 */
@Mapper
@Repository
public interface TagMapper {

    @Select("select * from tag")
    List<Tag> select();

    @Select("select id from tag where tag_name=#{tagName}")
    int findTagIdByName(@Param("tagName") String tagName);
}
