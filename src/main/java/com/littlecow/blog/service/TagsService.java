package com.littlecow.blog.service;

import com.littlecow.blog.entity.Tag;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TagsService {
    List<Tag> getTagList();

    Tag getTagByName(String name);

    @Transactional
    boolean addTag(Tag tag);

    @Transactional
    boolean deleteTagById(Long id);

    @Transactional
    boolean editTag(Tag tag,Long id);

    Tag getTagById( Long id);

    List<Tag> getTagByIds(String idsStr);
}
