package com.littlecow.blog.service;

import com.littlecow.blog.entity.Blog;

import java.util.List;

public interface BlogsService {
    List<Blog> getBlogList();

    boolean addBlog(Blog blog);

    boolean deleteBlogById(Long id);

    Blog getBlogById(Long id);

    boolean updateBlog(Blog blog);

    List<Blog> getBlogByCondition(Blog blog);

    List<Blog> getBlogsByTypeId(Long type_id);

    /*推荐的博客列表*/
    List<Blog> getBlogsByRecommendFlag(Boolean recommend,Integer nums);
}
