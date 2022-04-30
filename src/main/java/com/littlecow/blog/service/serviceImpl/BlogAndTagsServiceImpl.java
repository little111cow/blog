package com.littlecow.blog.service.serviceImpl;

import com.littlecow.blog.entity.BlogAndTags;
import com.littlecow.blog.mapper.BlogAndTagsMapper;
import com.littlecow.blog.service.BlogAndTagsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogAndTagsServiceImpl implements BlogAndTagsService {
    @Resource
    private BlogAndTagsMapper blogAndTagsMapper;

    @Override
    public List<BlogAndTags> getBlogAndTagsByBlogId(Long bid) {
        return blogAndTagsMapper.getBlogAndTagsByBlogId(bid);
    }

    @Override
    public List<BlogAndTags> getBlogAndTagsByTagId(Long tid) {
        return blogAndTagsMapper.getBlogAndTagsByTagId(tid);
    }

}
