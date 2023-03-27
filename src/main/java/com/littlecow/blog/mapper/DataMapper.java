package com.littlecow.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DataMapper {
    @Select("select count(*) from t_blog")
    Integer getBlogs();

    @Select("select sum(views) from t_blog")
    Integer getViews();

    @Select("select count(*) from t_comment")
    Integer getComments();

    @Select("select count(*) from t_notice")
    Integer getNotices();

    @Select("select count(*) from t_link")
    Integer getLinks();

    @Select("select count(*) from t_file")
    Integer getFiles();

    @Select("select count(*) from t_tag")
    Integer getTags();

    @Select("select count(*) from t_type")
    Integer getTypes();
}
