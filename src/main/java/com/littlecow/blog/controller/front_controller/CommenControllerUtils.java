package com.littlecow.blog.controller.front_controller;

import com.littlecow.blog.entity.Blog;
import com.littlecow.blog.entity.Tag;
import com.littlecow.blog.entity.Type;
import com.littlecow.blog.entity.User;
import com.littlecow.blog.service.TagsService;
import com.littlecow.blog.service.TypesService;
import com.littlecow.blog.service.UserLoginService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
public class CommenControllerUtils {
    @Resource
    private  TagsService tagsService;

    @Resource
    private  TypesService typesService;

    @Resource
    private  UserLoginService userLoginService;

    public  List<Blog>  setBlogTypeTagUser(List<Blog> blogList){
        for(Blog blog:blogList){
            User user = userLoginService.getUserById(blog.getUserId());
            Type t = typesService.getTypeById(blog.getTypeId());
            List<Tag> tags = tagsService.getTagByIds(blog.getTagIds());
            blog.setTags(tags);
            blog.setUser(user);
            blog.setType(t);
        }
        return blogList;
    }

    /*根据tagid 获得对应的所有博客列表*/
    public  List<Blog> getBlogsByTagId(Long id,List<Blog> blogs){
        List<Blog> blogList = new ArrayList<>();
        for(Blog blog:blogs){
            if(blog.containsTagId(id)){
                blogList.add(blog);
            }
        }
        return blogList;
    }

    /*根据年份归档，返回归档map*/
    public  Map<String,Set<Blog>> archivesByCreateYear(List<Blog> blogList){
        Map<String,Set<Blog>> archiveMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        for(Blog blog:blogList){
            Date date =  blog.getCreateTime();
            Integer year = date.getYear()+1900;  //这是一个过时方法，jdk官方的解释需要加上1900
            Integer month = date.getMonth() + 1;
            String key = year + "/" + month;
            Set<Blog> list = archiveMap.getOrDefault(key,new TreeSet<>(new Comparator<Blog>() {
                @Override
                public int compare(Blog o1, Blog o2) {
                    return (o2.getCreateTime().getTime()+"").compareTo(o1.getCreateTime().getTime()+"");
                }
            }));
            list.add(blog);
            archiveMap.put(key,list);
        }
        return archiveMap;
    }

}
