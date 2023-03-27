package com.littlecow.blog.mapper;

import com.littlecow.blog.entity.Link;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LinkMapper {
    @Select("select * from t_link where published = true order by createTime desc")
    List<Link> getLinkListPublished();

    @Select("select * from t_link order by createTime desc")
    List<Link> getLinkListAll();

    @Insert("insert into t_link(email,nickName,url,description,coverPictureUrl,published,createTime)" +
            "values (#{link.email},#{link.nickName},#{link.url},#{link.description},#{link.coverPictureUrl},#{link.published},#{link.createTime})")
    Boolean saveLink(@Param("link") Link link);

    @Delete("delete from t_link where id = #{Lid}")
    Boolean delete(@Param("Lid")Integer id);

    @Select("select * from t_link where id = #{Lid}")
    Link getLinkById(@Param("Lid")Integer id);

    @Update({"<script>",
            "update t_link",
            "<set>",
            "<if test = 'link.email != null'>email = #{link.email}, </if>",
            "<if test = 'link.nickName != null'>nickName = #{link.nickName}, </if>",
            "<if test = 'link.url != null'>url = #{link.url}, </if>",
            "<if test = 'link.description != null'>description = #{link.description}, </if>",
            "<if test = 'link.coverPictureUrl != null'>coverPictureUrl = #{link.coverPictureUrl}, </if>",
            "<if test = 'link.published != null'>published = #{link.published}, </if>",
            "<if test = 'link.createTime != null'>createTime = #{link.createTime}, </if>",
            "</set>",
            "where id = #{link.id}",
            "</script>"})
    Boolean updateLink(@Param("link") Link link);
}
