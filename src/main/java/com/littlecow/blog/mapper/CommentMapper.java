package com.littlecow.blog.mapper;

import com.littlecow.blog.entity.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {
    @Select("select * from t_comment where blog_id = #{bid} and parent_comment_id = #{parentCommentId} order by create_time desc")
    List<Comment> getCommentByBlogId(@Param("bid") Long id,@Param("parentCommentId")Long parentCommentId);

    @Insert("insert into t_comment(nickname,email,content,avatar,create_time,blog_id,parent_comment_id,admincomment)" +
            "values(#{comment.nickname},#{comment.email},#{comment.content},#{comment.avatar},#{comment.createTime}," +
            "#{comment.blogId},#{comment.parentCommentId}, #{comment.admincomment})")
    Boolean saveComment(@Param("comment") Comment comment);

    @Select("select * from t_comment where id = #{pid}")
    Comment getByCommentId(@Param("pid")Long id);

    @Select("select * from t_comment where parent_comment_id = #{pid}")
    List<Comment> getCommentsByParentCommentId(@Param("pid")Long id);

    //判断是否存在评论用户，即只要查找到一条评论即可，共用头像
    @Select("select * from t_comment where nickname = #{nickname} and email=#{email} limit 1")
    Comment getCommentUserByEmailAndNickname(@Param("nickname")String nickname,@Param("email")String email);

    @Delete("delete from t_comment where id = #{cid}")
    Boolean deleteCommentById(@Param("cid")Long id);

    @Select("select * from t_comment order by create_time desc")
    List<Comment> getMessageList();

    @Update("update t_comment set parent_comment_id = -1 where parent_comment_id = #{cid}")
    void modifyParentId(@Param("cid") Long id);
}
