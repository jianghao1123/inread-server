package com.in.read.article.service.impl;

import com.in.read.article.entity.Like;
import com.in.read.article.mapper.LikeMapper;
import com.in.read.article.mapper.NoteInteractionMapper;
import com.in.read.article.mapper.NoteMapper;
import com.in.read.article.service.LikeService;
import com.in.read.framework.base.BaseServiceImpl;
import com.in.read.framework.constant.InreadConstant;
import com.in.read.framework.security.UserUtil;
import com.in.read.pojo.note.comment.NoteLikeReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void like(NoteLikeReq req) {
        int uid = UserUtil.getLoginUId();
        Like like = null;
//        Like like = baseMapper.selectOne(new QueryWrapper<Like>()
//                .lambda()
//                .eq(Like::getNoteId, req.getNoteId())
//                .eq(Like::getUid, uid));
        if (like == null) {
            like = new Like();
        }
        like.setNoteId(req.getNoteId());
        like.setUid(uid);
        like.setDeleted(InreadConstant.DB_VALID);
        like.setStatus(InreadConstant.DB_VALID);
        if (like.getId() == null) {
            baseMapper.insert(like);
        } else {
            baseMapper.updateById(like);
        }
        noteInteractionMapper.incLike(req.getNoteId());
    }
}
