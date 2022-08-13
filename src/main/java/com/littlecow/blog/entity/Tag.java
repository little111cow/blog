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
public class Tag implements Serializable,Comparable<Tag> {
    private Long id;
    private String name;
    private List<Blog> blogs = new ArrayList<>(); //tag对应的所有博客

    @Override
    public int compareTo(Tag o) {
        return o.blogs.size() - this.blogs.size();
    }
}
