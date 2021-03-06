package com.littlecow.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class Blog implements Serializable {

    private Long id;

    private String title;

    private String content;

    private String firstPicture;

    private String flag;

    private Integer views;

    private Boolean appreciation;

    private Boolean shareStatement;

    private Boolean commentabled;

    private Boolean published;

    private Boolean recommend;

    private Date createTime;  //开启驼峰命名转换的

    private Date updateTime;   //开启驼峰命名转换的，若改为和数据库里的名称一样，前端会找不到字段

    private Long typeId;

    private Long userId;

    private String description;

    private String tagIds;

    private List<Tag> tags;

    @Resource
    private Type type;

    @Resource
    private  User user;

    //判断该博客是否包含某个Tag
    public boolean containsTagId(Long tagid){
        if(tagIds == null || "".equals(tagIds)){
            return false;
        }
         Set<String> tagIdList = new HashSet<>();
        if(!tagIds.equals("")) {
            String[] ids = tagIds.trim().split(",");
            for (String id : ids) {
                tagIdList.add(id);
            }
        }
        return tagIdList.contains(tagid+"");
    }

}
