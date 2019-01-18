package com.in.read.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.in.read.article.entity.Like;
import com.in.read.article.mapper.LikeMapper;
import com.in.read.article.mapper.NoteInteractionMapper;
import com.in.read.article.service.LikeService;
import com.in.read.framework.base.BaseServiceImpl;
import com.in.read.framework.constant.InreadConstant;
import com.in.read.framework.exception.BusinessException;
import com.in.read.framework.security.UserUtil;
import com.in.read.pojo.note.comment.NoteLikeReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 点赞 服务实现类
 * </p>
 *
 * @author Generator
 * @since 2019-01-06
 */
@Service
public class LikeServiceImpl extends BaseServiceImpl<LikeMapper, Like> implements LikeService {

    @Autowired
    private NoteInteractionMapper noteInteractionMapper;

    @Override
    @Transactional
    public void like(NoteLikeReq req) throws BusinessException {
        int uid = UserUtil.getLoginUId();
        Like like = baseMapper.selectOne(new QueryWrapper<Like>()
                .lambda()
                .eq(Like::getNoteId, req.getNoteId())
                .eq(Like::getUid, uid));
        if (like != null) {
            throw new BusinessException("您已经赞过了！");
        }
        like = new Like();
        like.setNoteId(req.getNoteId());
        like.setUid(uid);
        like.setDeleted(InreadConstant.DB_VALID);
        like.setStatus(InreadConstant.DB_VALID);
        baseMapper.insert(like);
        noteInteractionMapper.incLike(req.getNoteId());

    }
}
