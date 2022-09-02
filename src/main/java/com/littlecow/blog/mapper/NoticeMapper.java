package com.littlecow.blog.mapper;

import com.littlecow.blog.entity.Notice;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NoticeMapper {
    @Select("select * from t_notice where published = true order by publishTime desc")
    List<Notice> getNoticeListPublished();

    @Select("select * from t_notice order by publishTime desc")
    List<Notice> getNoticeList();

    @Insert("insert into t_notice(title, description, publishTime, published) " +
            "values(#{notice.title},#{notice.description},#{notice.publishTime},#{notice.published})")
    Boolean addNotice(@Param("notice") Notice notice);

    @Update({"<script>",
            "update t_notice",
            "<set>",
            "<if test = 'notice.description != null'>description = #{notice.description}, </if>",
            "<if test = 'notice.publishTime != null'>publishTime = #{notice.publishTime}, </if>",
            "<if test = 'notice.title != null'>title = #{notice.title}, </if>",
            "<if test = 'notice.published != null'>published = #{notice.published}, </if>",
            "</set>",
            "where id = #{notice.id}",
            "</script>"})
    Boolean editNotice(@Param("notice") Notice notice);

    @Select("select * from t_notice where id = #{nid}")
    Notice getNoticeById(@Param("nid")Integer id);

    @Delete("delete from t_notice where id = #{nid}")
    Boolean deleteNoticeById(@Param("nid")Integer id);
}