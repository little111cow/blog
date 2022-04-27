package com.littlecow.blog.service;

import com.littlecow.blog.entity.Type;

import java.util.List;

public interface TypesService {

    List<Type> getTypeList();

    boolean addType(Type type);

    Type getTypeByName(String typename);

    boolean deleteTypeById(Long id);

    Type getTypeById(Long typeid);

    boolean updateTypeById(Type type);
}
