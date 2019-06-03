package com.second.hand.transactions.mapper;

import com.second.hand.transactions.model.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
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
    @Results({
            @Result(column = "tag_name",property = "tagName")
    })
    List<Tag> select();

}
