package com.in.read.article.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.in.read.article.entity.Comment;
import com.in.read.framework.base.BaseService;
import com.in.read.framework.exception.BusinessException;
import com.in.read.pojo.note.comment.CommentAddReq;
import com.in.read.pojo.note.comment.CommentListReq;
import com.in.read.pojo.note.comment.CommentVo;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author Generator
 * @since 2019-01-06
 */
public interface CommentService extends BaseService<Comment> {
    CommentVo add(int userId, CommentAddReq req) throws BusinessException;
    IPage<CommentVo> list(CommentListReq req);
}
