package com.littlecow.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class Type implements Serializable {
    private Long id;
    private String name;
    private List<Blog> blogs; //类型对应的所有博客
}
