package com.littlecow.blog.service.serviceImpl;

import com.littlecow.blog.entity.Comment;
import com.littlecow.blog.mapper.CommentMapper;
import com.littlecow.blog.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    @Override
    public Comment getCommentUserByEmailAndNickname(String nickname, String email) {
        return commentMapper.getCommentUserByEmailAndNickname(nickname,email);
    }

    @Override
    @Transactional
    public Boolean deleteCommentById(Long id) {
        commentMapper.modifyParentId(id);
        return commentMapper.deleteCommentById(id);
    }

    @Override
    public List<Comment> getMessageList() {
        return commentMapper.getMessageList();
    }

    @Override
    public List<Comment> getCommentByBlogId(Long id) {
        List<Comment> commentList = commentMapper.getCommentByBlogId(id,-1L);  //获取当前博客没有父节点的所有评论
        for(Comment comment:commentList){
            List<Comment> comments = commentMapper.getCommentsByParentCommentId(comment.getId()); //获得父id为当前评论id的所有回复评论
            for(Comment comment1:comments){
                comment1.setParentComment(comment);
            }
            comment.setReplyComments(comments);
        }
        return commentList;
    }

    @Override
    @Transactional
    public Boolean saveComment(Comment comment) {
        //获得父评论id
        Long parent_id = comment.getParentComment().getId();
        if(parent_id != -1){ //父id为-1表示没有父节点
            comment.setParentComment(commentMapper.getByCommentId(parent_id));
            comment.setParentCommentId(parent_id);
        }else{
            comment.setParentCommentId(-1L);
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentMapper.saveComment(comment);
    }
}
