package com.littlecow.blog.service;

import com.littlecow.blog.entity.Blog;
import com.littlecow.blog.entity.BlogAndTags;
import com.littlecow.blog.entity.Tag;

import java.util.List;


public interface BlogAndTagsService {

    List<BlogAndTags> getBlogAndTagsByBlogId(Long bid);

    List<BlogAndTags> getBlogAndTagsByTagId(Long tid);
}
