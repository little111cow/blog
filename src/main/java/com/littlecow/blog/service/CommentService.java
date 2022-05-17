package com.littlecow.blog.service;

import com.littlecow.blog.entity.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface CommentService {
    List<Comment> getCommentByBlogId(Long id);

    @Transactional
    Boolean saveComment(Comment comment);

    Comment getCommentUserByEmailAndNickname(String nickname,String email);
}
