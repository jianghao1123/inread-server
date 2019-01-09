package com.in.read.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.in.read.article.entity.Comment;
import com.in.read.article.entity.User;
import com.in.read.article.mapper.CommentMapper;
import com.in.read.article.mapper.NoteMapper;
import com.in.read.article.mapper.UserMapper;
import com.in.read.article.service.CommentService;
import com.in.read.framework.base.BaseServiceImpl;
import com.in.read.framework.convert.ConvertUtils;
import com.in.read.framework.security.UserUtil;
import com.in.read.framework.util.BeanUtil;
import com.in.read.pojo.note.comment.CommentAddReq;
import com.in.read.pojo.note.comment.CommentListReq;
import com.in.read.pojo.note.comment.CommentVo;
import com.in.read.pojo.note.user.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private NoteMapper noteMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public CommentVo add(CommentAddReq req) {
        Comment comment = new Comment();
        BeanUtil.copyProperties(req, comment);
        comment.setFromUId(UserUtil.getLoginUId());
        baseMapper.insert(comment);
        noteMapper.incComment(req.getNodeId());
        CommentVo commentVo = ConvertUtils.convert(CommentVo.class, comment);
        User user = userMapper.selectById(UserUtil.getLoginUId());
        commentVo.setFromUser(ConvertUtils.convert(UserVo.class, user));
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
        CommentVo commentVo = ConvertUtils.convert(CommentVo.class, comment);
        User user = userMapper.selectById(comment.getFromUId());
        if (user != null) {
            commentVo.setFromUser(ConvertUtils.convert(UserVo.class, user));
        }
        return commentVo;
    }


}
