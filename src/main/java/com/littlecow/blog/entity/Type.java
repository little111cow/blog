package com.littlecow.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class Type implements Serializable, Comparable<Type> {
    private Long id;
    private String name;
    private List<Blog> blogs = new ArrayList<>(); //类型对应的所有博客

    // 按照博客数量比较大小
    @Override
    public int compareTo(Type o) {
        return o.blogs.size()-this.blogs.size();
    }
}
