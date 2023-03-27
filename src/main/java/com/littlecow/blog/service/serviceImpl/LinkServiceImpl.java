package com.littlecow.blog.service.serviceImpl;

import com.littlecow.blog.entity.Link;
import com.littlecow.blog.mapper.LinkMapper;
import com.littlecow.blog.service.LinkService;
import com.littlecow.blog.util.RandomAvatarUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class LinkServiceImpl implements LinkService {
    @Resource
    private LinkMapper linkMapper;

    @Override
    @Transactional
    public Boolean updateLink(Link link) {
        link.setCreateTime(new Date());
        return linkMapper.updateLink(link);
    }

    @Override
    public Link getLinkById(Integer id) {
        return linkMapper.getLinkById(id);
    }

    @Override
    @Transactional
    public Boolean delete(Integer id) {
        return linkMapper.delete(id);
    }

    @Override
    public List<Link> getLinkListAll() {
        return linkMapper.getLinkListAll();
    }

    @Override
    @Transactional
    public Boolean saveLink(Link link) {
        link.setCreateTime(new Date());
        if (link.getCoverPictureUrl() == null) {
            link.setCoverPictureUrl(RandomAvatarUtils.RandPicture());
        }
        if (link.getNickName() == null) {
            link.setNickName(UUID.randomUUID().toString());
        }
        return linkMapper.saveLink(link);
    }

    @Override
    public List<Link> getLinkListPublished() {
        return linkMapper.getLinkListPublished();
    }
}
