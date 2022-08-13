package com.littlecow.blog.mapper;

import com.littlecow.blog.entity.Blog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogsMapper {
    @Select("select * from t_blog order by update_time desc,create_time desc ")
    List<Blog> getBlogList();

    @Select("select * from t_blog where published = 1 order by update_time desc,create_time desc ")
    List<Blog> getBlogListPublished();

    @Insert("insert into t_blog(title,content,first_picture,flag,views,appreciation,share_statement,commentabled," +
            "published,recommend,create_time,update_time,type_id,user_id,description,tag_ids) values(#{blog.title}," +
            "#{blog.content},#{blog.firstPicture},#{blog.flag},#{blog.views},#{blog.appreciation}," +
            "#{blog.shareStatement},#{blog.commentabled},#{blog.published},#{blog.recommend},#{blog.createTime}," +
            "#{blog.updateTime},#{blog.typeId},#{blog.userId},#{blog.description},#{blog.tagIds}) ")
    boolean addBlog(@Param("blog")Blog blog);

    @Delete("delete from t_blog where id = #{bid}")
    boolean deleteBlogById(@Param("bid") Long id);

    @Select("select * from t_blog where id=#{bid}")
    Blog getBlogById(@Param("bid")Long id);

    @Update({"<script>",
            "update t_blog",
            "  <set>",
            "    <if test='blog.title != null'> title=#{blog.title}, </if>",
            "    <if test='blog.content != null'> content=#{blog.content}, </if>",
            "    <if test='blog.firstPicture != null'> first_picture=#{blog.firstPicture}, </if>",
            "    <if test='blog.flag != null'> flag=#{blog.flag}, </if>",
            "    <if test='blog.views != null'> views=#{blog.views}, </if>",
            "    <if test='blog.appreciation != null'> appreciation=#{blog.appreciation}, </if>",
            "    <if test='blog.shareStatement != null'> share_statement=#{blog.shareStatement}, </if>",
            "    <if test='blog.commentabled != null'> commentabled=#{blog.commentabled}, </if>",
            "    <if test='blog.published != null'> published=#{blog.published}, </if>",
            "    <if test='blog.recommend != null'> recommend=#{blog.recommend}, </if>",
            "    <if test='blog.createTime != null'> create_time=#{blog.createTime}, </if>",
            "    <if test='blog.updateTime != null'> update_time=#{blog.updateTime}, </if>",
            "    <if test='blog.typeId != null'> type_id=#{blog.typeId}, </if>",
            "    <if test='blog.userId != null'> user_id=#{blog.userId}, </if>",
            "    <if test='blog.description != null'> description=#{blog.description}, </if>",
            "    <if test='blog.tagIds != null'> tag_ids=#{blog.tagIds} </if>",
            "  </set>",
            "where id=#{blog.id}",
            "</script>"})
    boolean updateBlog(@Param("blog")Blog blog);

    @Select({"<script>",
            "select * from t_blog",
            "<where>",
            "    <if test='blog.title != null'>title like #{blog.title}</if>",
            "    <if test='blog.typeId != null'>and type_id=#{blog.typeId}</if>",
            "    <if test='blog.recommend != null'>and recommend=#{blog.recommend}</if>",
            "    <if test='blog.published != null'>and published=#{blog.published}</if>",
            "</where>",
            " order by update_time desc",
            "</script>"})
    List<Blog> getBlogByCondition(@Param("blog")Blog blog);

    @Select("select * from t_blog where type_id=#{tid} and published = 1 order by update_time desc")
    List<Blog> getBlogsByTypeId(@Param("tid")Long type_id);

    @Select("select * from t_blog where recommend=#{recommend} and published = 1 order by update_time desc limit #{nums}")
    List<Blog> getBlogsByRecommendFlag(@Param("recommend")Boolean recommend,@Param("nums") Integer nums);

    @Update("update t_blog set views = #{views} where id = #{bid} ")
    Boolean updateViewsById(@Param("bid")Long id,@Param("views") Integer views);

    @Select("select * from t_blog where published = 1 and title like #{query} or content like #{query} order by update_time desc ")
    List<Blog> searchGlobal(@Param("query") String query);

    @Select("select * from t_blog where published = 1 order by views desc,update_time desc limit #{nums}")
    List<Blog> getHotBlogs(@Param("nums") Integer nums);  // 查询查看次数最多的限定数量文章
}
