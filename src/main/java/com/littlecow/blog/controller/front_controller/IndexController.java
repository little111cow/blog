package com.littlecow.blog.controller.front_controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.littlecow.blog.Contants;
import com.littlecow.blog.entity.Blog;
import com.littlecow.blog.entity.Tag;
import com.littlecow.blog.entity.Type;
import com.littlecow.blog.entity.User;
import com.littlecow.blog.service.BlogsService;
import com.littlecow.blog.service.TagsService;
import com.littlecow.blog.service.TypesService;
import com.littlecow.blog.service.UserLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.*;

/**
 * create by liujian 2022.4.27
 * */

@Controller
public class IndexController {
    @Resource
    private BlogsService blogsService;

    @Resource
    private TagsService tagsService;

    @Resource
    private TypesService typesService;

    @Resource
    private UserLoginService userLoginService;

    @RequestMapping({"/index","/"})
    @SuppressWarnings("all")
    public String index(@RequestParam(defaultValue = "1") int pagenum, Model model){
        PageHelper.startPage(pagenum, Contants.PAGE_SIZE);  //跟着后边一个select语句会被分页
        List<Blog> blogList = blogsService.getBlogList();
        for(Blog blog:blogList){
            User user = userLoginService.getUserById(blog.getUserId());
            Type t = typesService.getTypeById(blog.getTypeId());
            blog.setUser(user);
            blog.setType(t);
        }
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute(Contants.PAGE_INFO,pageInfo);

        List<Tag> tags = tagsService.getTagListLimit(Contants.RECOMMEND_NUMS);
        List<Blog> allBlogs = blogsService.getBlogList();
        for(Tag tag:tags){
            //通过tagid获取到对应的所有博客
            List<Blog> blogs = getBlogsByTagId(tag.getId(),allBlogs);
            tag.setBlogs(blogs);
        }
        model.addAttribute("tags",tags);

        List<Type> types = typesService.getTypeListLimit(Contants.RECOMMEND_NUMS);
        for(Type type:types){
            List<Blog> blogs = blogsService.getBlogsByTypeId(type.getId());
            type.setBlogs(blogs);
        }
        model.addAttribute("types",types);

        List<Blog> recommendBlogs = blogsService.getBlogsByRecommendFlag(true,Contants.RECOMMEND_NUMS);
        model.addAttribute("recommendBlogs",recommendBlogs);
        return "index";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/archives")
    public String archives(Model model){
        List<Blog> blogList = blogsService.getBlogList();
        int blogCount = blogList.size();
        Map<Integer,Set<Blog>> archiveMap = archivesByUpdateYear(blogList);
        model.addAttribute("archiveMap",archiveMap);
        model.addAttribute("blogCount",blogCount);
        return "archives";
    }

    /*根据tagid 获得对应的所有博客列表*/
    private List<Blog> getBlogsByTagId(Long id,List<Blog> blogs){
        List<Blog> blogList = new ArrayList<>();
        for(Blog blog:blogs){
            if(blog.containsTagId(id)){
                blogList.add(blog);
            }
        }
        return blogList;
    }

    /*根据年份归档，返回归档map*/
    private Map<Integer,Set<Blog>> archivesByUpdateYear(List<Blog> blogList){
        Map<Integer,Set<Blog>> archiveMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for(Blog blog:blogList){
            Date date =  blog.getUpdateTime();
            Integer year = date.getYear()+1900;  //这是一个过时方法，jdk官方的解释需要加上1900
            Set<Blog> list = archiveMap.getOrDefault(year,new TreeSet<>(new Comparator<Blog>() {
                @Override
                public int compare(Blog o1, Blog o2) {
                    return (int)(o2.getUpdateTime().getTime()-o1.getUpdateTime().getTime());
                }
            }));
            list.add(blog);
            archiveMap.put(year,list);
        }
        return archiveMap;
    }

}
