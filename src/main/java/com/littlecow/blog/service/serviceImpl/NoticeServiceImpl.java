package com.littlecow.blog.service.serviceImpl;

import com.littlecow.blog.entity.Notice;
import com.littlecow.blog.mapper.NoticeMapper;
import com.littlecow.blog.service.NoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService{
    @Resource
   private NoticeMapper noticeMapper;

    @Override
    public List<Notice> getNoticeList() {
        return noticeMapper.getNoticeList();
    }

    @Override
    public List<Notice> getNoticeListPublished() {
        return noticeMapper.getNoticeListPublished();
    }

    @Transactional
    @Override
    public Boolean addNotice(Notice notice) {
        notice.setPublishTime(new Date());
        return noticeMapper.addNotice(notice);
    }

    @Transactional
    @Override
    public Boolean editNotice(Notice notice) {
        notice.setPublishTime(new Date());
        return noticeMapper.editNotice(notice);
    }

    @Transactional
    @Override
    public Boolean deleteNoticeById(Integer id) {
        return noticeMapper.deleteNoticeById(id);
    }

    @Override
    public Notice getNoticeById(Integer id) {
        return noticeMapper.getNoticeById(id);
    }
}
