package com.littlecow.blog.service;

import com.littlecow.blog.entity.Notice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoticeService {
    List<Notice> getNoticeListPublished();

    List<Notice> getNoticeList();

    Boolean addNotice(Notice notice);

    Boolean editNotice(Notice notice);

    Notice getNoticeById(Integer id);

    Boolean deleteNoticeById(Integer id);
}
