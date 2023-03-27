package com.littlecow.blog.service;

import com.littlecow.blog.entity.Link;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface LinkService {
    List<Link> getLinkListPublished();

    List<Link> getLinkListAll();

    @Transactional
    Boolean saveLink(Link link);

    @Transactional
    Boolean delete(Integer id);

    Link getLinkById(Integer id);

    @Transactional
    Boolean updateLink(Link link);
}
