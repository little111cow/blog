package com.littlecow.blog.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@lombok.Data
@Component
public class Data {
    int blogs;

    int views;

    int comments;

    int links;

    int notices;

    int files;

    int tags;

    int types;
}
