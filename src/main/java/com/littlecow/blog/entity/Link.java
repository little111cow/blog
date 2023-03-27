package com.littlecow.blog.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Link {
    private Integer id;
    private String email;
    private String nickName;
    private String url;
    private String description;
    private String coverPictureUrl;
    private Boolean published = false;
    private Date createTime;
}
