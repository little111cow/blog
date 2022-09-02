package com.littlecow.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice implements Serializable {
    private String description;

    private String title;

    private Date publishTime;

    private Integer id;

    private Boolean published = false; // 默认不发布
}
