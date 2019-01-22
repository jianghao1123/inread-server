package com.in.read.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.in.read.article.entity.Comment;
import com.in.read.article.mapper.CommentMapper;
import com.in.read.article.mapper.NoteInteractionMapper;
import com.in.read.article.service.CommentService;
import com.in.read.framework.base.BaseServiceImpl;
import com.in.read.framework.convert.ConvertUtils;
import com.in.read.framework.security.UserUtil;
import com.in.read.framework.util.BeanUtil;
import com.in.read.pojo.note.comment.CommentAddReq;
import com.in.read.pojo.note.comment.CommentListReq;
import com.in.read.pojo.note.comment.CommentVo;
import com.in.read.pojo.note.user.UserVo;
import com.in.read.user.entity.User;
import com.in.read.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author Generator
 * @since 2019-01-06
 */
@Service
public class CommentServiceImpl
        extends BaseServiceImpl<CommentMapper, Comment>
        implements CommentService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NoteInteractionMapper noteInteractionMapper;

    @Override
    public CommentVo add(CommentAddReq req) {
        Comment comment = new Comment();
        BeanUtil.copyProperties(req, comment);
        comment.setFromUid(UserUtil.getLoginUId());
        comment.setToUid(req.getToUId());
        comment.setNoteId(req.getNoteId());
        comment.setCommentPid(req.getCommentPid());
        baseMapper.insert(comment);
        noteInteractionMapper.incComment(req.getNoteId());
        CommentVo commentVo = ConvertUtils.convert(CommentVo.class, comment);
        User user = userMapper.selectById(UserUtil.getLoginUId());
        commentVo.setFromUser(ConvertUtils.convert(UserVo.class, user));
        if(req.getToUId() > 0){
            user = userMapper.selectById(req.getToUId());
            commentVo.setToUId(req.getToUId());
            if(user != null) {
                commentVo.setToUser(ConvertUtils.convert(UserVo.class, user));
            }
        }
        return commentVo;
    }

    @Override
    public IPage<CommentVo> list(CommentListReq req) {
        IPage<Comment> page = new Page<>(req.getPage(), req.getSize());
        page = baseMapper.selectPage(page, new QueryWrapper<Comment>()
                .lambda().eq(Comment::getNoteId, req.getNoteId())
                .eq(Comment::getToUid, 0)
                .orderByDesc(Comment::getCreateTime));
        return page.convert(comment -> convert(comment));
    }

    private CommentVo convert(Comment comment) {
        // 评论
        CommentVo commentVo = ConvertUtils.convert(CommentVo.class, comment);
        User user = userMapper.selectById(comment.getFromUid());
        if (user != null) {
            commentVo.setFromUser(ConvertUtils.convert(UserVo.class, user));
        }
        List<Comment> replyComments = baseMapper.selectReplyByNoteId(comment.getNoteId(),comment.getId(), 1);
        // 回复
        if(replyComments != null && replyComments.size() > 0){
            commentVo.setReplyItems(new ArrayList<>(1));
            for(Comment replyComment : replyComments) {
                CommentVo reply = ConvertUtils.convert(CommentVo.class, replyComment);
                user = userMapper.selectById(replyComment.getFromUid());
                if (user != null) {
                    reply.setFromUser(ConvertUtils.convert(UserVo.class, user));
                }
                user = userMapper.selectById(replyComment.getToUid());
                if (user != null) {
                    reply.setToUser(ConvertUtils.convert(UserVo.class, user));
                }
                commentVo.getReplyItems().add(reply);
            }
            commentVo.setReplyItemCount(baseMapper.selectCount(new QueryWrapper<Comment>()
                    .lambda()
                    .eq(Comment::getNoteId, comment.getNoteId())
                    .eq(Comment::getCommentPid, comment.getId())));
        }else{
            commentVo.setReplyItems(Collections.emptyList());
        }

        return commentVo;
    }


}
