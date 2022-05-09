package com.littlecow.blog.service.serviceImpl;

import com.littlecow.blog.entity.Type;
import com.littlecow.blog.mapper.TypesMapper;
import com.littlecow.blog.service.TypesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypesServiceImpl implements TypesService {
    @Resource
    private TypesMapper typesMapper;

    @Override
    public Type getTypeByName(String typename) {
        return typesMapper.getTypeByName(typename);
    }

    @Override
    @Transactional
    public boolean updateTypeById(Type type) {
        return typesMapper.updateTypeById(type.getName(),type.getId());
    }

    @Override
    public Type getTypeById(Long typeid) {
        return typesMapper.getTypeById(typeid);
    }

    @Override
    @Transactional
    public boolean deleteTypeById(Long id) {
        return typesMapper.deleteTypeById(id);
    }

    @Override
    @Transactional //事务注解
    public boolean addType(Type type) {
        return typesMapper.addType(type.getName());
    }

    @Override
    public List<Type> getTypeList() {
        return typesMapper.getTypeList();
    }

    @Override
    public List<Type> getTypeListLimit(Integer num) {
        return typesMapper.getTypeListLimit(num);
    }

}
