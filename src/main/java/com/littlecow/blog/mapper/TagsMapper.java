package com.littlecow.blog.mapper;

import com.littlecow.blog.entity.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagsMapper {
    @Select("select * from t_tag")
    List<Tag> getTagList();

    @Select("select * from t_tag where name = #{tagname}")
    Tag getTagByName(@Param("tagname") String name);

    @Insert("insert into t_tag(name) value(#{tagname})")
    boolean addTag(@Param("tagname") String name);

    @Delete("delete from t_tag where id = #{tagid}")
    boolean deleteTagById(@Param("tagid")Long id);

    @Select("select * from t_tag where id = #{tagid}")
    Tag getTagById(@Param("tagid") Long id);

    @Update("update t_tag set name = #{tagname} where id=#{tagid}")
    boolean editTag(@Param("tagname")String name,@Param("tagid")Long id);
}
