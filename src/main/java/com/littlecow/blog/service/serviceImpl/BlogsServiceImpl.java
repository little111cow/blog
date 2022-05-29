package com.littlecow.blog.service.serviceImpl;

import com.littlecow.blog.entity.Blog;
import com.littlecow.blog.mapper.BlogsMapper;
import com.littlecow.blog.service.BlogsService;
import com.littlecow.blog.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class BlogsServiceImpl implements BlogsService {
    @Resource
    private BlogsMapper blogsMapper;

    @Override
    @Transactional
    public boolean addBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        return blogsMapper.addBlog(blog);
    }

    @Override
    public List<Blog> getBlogsByRecommendFlag(Boolean recommend,Integer nums) {
        return blogsMapper.getBlogsByRecommendFlag(recommend,nums);
    }

    @Override
    public List<Blog> searchGlobal(String query) {
        return blogsMapper.searchGlobal("%"+query+"%"); //拼接字符串实现模糊查询
    }

    @Override
    public List<Blog> getBlogsByTypeId(Long type_id) {
        return blogsMapper.getBlogsByTypeId(type_id);
    }

    @Override
    public List<Blog> getBlogByCondition(Blog blog) {
        String title = blog.getTitle();
        String newTitle;
        if(title != null && !title.equals("")){
            newTitle = "%"+title+"%";  //支持title模糊查询
            blog.setTitle(newTitle);
        }
        if(title != null && title.equals("")){
            blog.setTitle(null);
        }
        if(!blog.getRecommend()){
            blog.setRecommend(null);
        }
        if(!blog.getPublished()){
            blog.setPublished(null);
        }
        List<Blog> blogList = blogsMapper.getBlogByCondition(blog);
        return blogList;
    }

    @Override
    @Transactional
    public Boolean updateViews(Blog blog) {
        Long id = blog.getId();
        Integer views = blog.getViews()+1;
        return blogsMapper.updateViewsById(id,views);
    }

    @Override
    @Transactional
    public boolean updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        blog.setTypeId(blog.getType().getId());  //需要从type中获取到typeid用来更新
        if(blog.getRecommend() == null){  //取消勾选设置为false
            blog.setRecommend(false);
        }
        if(blog.getShareStatement() == null){ //取消勾选设置为false
            blog.setShareStatement(false);
        }
        if(blog.getCommentabled() == null){ //取消勾选设置为false
            blog.setCommentabled(false);
        }
        if(blog.getAppreciation() == null){ //取消勾选设置为false
            blog.setAppreciation(false);
        }
        return  blogsMapper.updateBlog(blog);
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogsMapper.getBlogById(id);
    }

    @Override
    /*通过id获取博客并将博客由markdown转换为html*/
    public Blog getBlogAndConvert(Long id) {
        Blog blog = getBlogById(id);
        Blog blog1 = new Blog();
        BeanUtils.copyProperties(blog,blog1);
        blog1.setContent(MarkdownUtils.markdownToHtmlExtensions(blog1.getContent()));  //将markdown转换为html
        return blog1;
    }

    @Override
    @Transactional
    public boolean deleteBlogById(Long id) {
        return blogsMapper.deleteBlogById(id);
    }

    @Override
    public List<Blog> getBlogList() {
        return blogsMapper.getBlogList();
    }

    @Override
    public List<Blog> getBlogListPublished() {
        return blogsMapper.getBlogListPublished();
    }

}
