package com.littlecow.blog.service.serviceImpl;

import com.littlecow.blog.entity.Tag;
import com.littlecow.blog.mapper.TagsMapper;
import com.littlecow.blog.service.TagsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagsServiceImpl implements TagsService {
    @Resource
    private TagsMapper tagsMapper;

    @Override
    public List<Tag> getTagList() {
        return tagsMapper.getTagList();
    }

    @Override
    public List<Tag> getTagListLimit(Integer num) {
        return tagsMapper.getTagListLimit(num);
    }

    /*根据blog的tagids获得所有的tag列表*/
    @Override
    public List<Tag> getTagByIds(String idsStr) {
        List<Tag> list = new ArrayList<>();
        if( idsStr != null&&!idsStr.equals("")) {
            String[] ids = idsStr.trim().split(",");
            for (String id : ids) {
                list.add(tagsMapper.getTagById(Long.parseLong(id)));
            }
        }
        return list;
    }

    @Override
    @Transactional
    public boolean editTag(Tag tag, Long id) {
        return tagsMapper.editTag(tag.getName(),id);
    }

    @Override
    public Tag getTagById(Long id) {
        return tagsMapper.getTagById(id);
    }

    @Override
    @Transactional
    public boolean deleteTagById(Long id) {
        return tagsMapper.deleteTagById(id);
    }

    @Override
    @Transactional
    public boolean addTag(Tag tag) {
        return tagsMapper.addTag(tag.getName());
    }

    @Override
    public Tag getTagByName(String name) {
        return tagsMapper.getTagByName(name);
    }
}
