package com.littlecow.blog.service.serviceImpl;

import com.littlecow.blog.entity.Data;
import com.littlecow.blog.mapper.DataMapper;
import com.littlecow.blog.service.DataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DataServiceImpl implements DataService{
    @Resource
    private DataMapper dataMapper;

    @Override
    public Data getData() {
        Data data = new Data();
        data.setBlogs(dataMapper.getBlogs());
        data.setComments(dataMapper.getComments());
        data.setFiles(dataMapper.getFiles());
        data.setNotices(dataMapper.getNotices());
        data.setTags(dataMapper.getTags());
        data.setTypes(dataMapper.getTypes());
        data.setViews(dataMapper.getViews());
        data.setLinks(dataMapper.getLinks());
        return data;
    }
}
