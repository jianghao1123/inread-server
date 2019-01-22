package com.in.read.article.service;

import com.in.read.article.entity.Like;
import com.in.read.framework.base.BaseService;
import com.in.read.framework.exception.BusinessException;
import com.in.read.pojo.note.comment.CommentLikeReq;
import com.in.read.pojo.note.note.NoteLikeReq;

/**
 * <p>
 * 点赞 服务类
 * </p>
 *
 * @author Generator
 * @since 2019-01-06
 */
public interface LikeService extends BaseService<Like> {
    void like(NoteLikeReq req) throws BusinessException;
    void like(CommentLikeReq req) throws BusinessException;
}
