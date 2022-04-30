package com.littlecow.blog.mapper;

import com.littlecow.blog.entity.BlogAndTags;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogAndTagsMapper {
    @Select("select * from t_blog_tags where blog_id = #{bid}")
    List<BlogAndTags> getBlogAndTagsByBlogId(@Param("bid") Long id);

    @Select("select * from t_blog_tags where tag_id = #{tid}")
    List<BlogAndTags> getBlogAndTagsByTagId(@Param("tid")Long id);

}
