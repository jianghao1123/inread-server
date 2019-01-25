package com.in.read.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.in.read.article.entity.Comment;
import com.in.read.article.entity.Like;
import com.in.read.article.entity.Note;
import com.in.read.article.entity.NoteInteraction;
import com.in.read.article.mapper.CommentMapper;
import com.in.read.article.mapper.LikeMapper;
import com.in.read.article.mapper.NoteInteractionMapper;
import com.in.read.article.mapper.NoteMapper;
import com.in.read.article.service.NoteService;
import com.in.read.common.DateUtil;
import com.in.read.framework.base.BaseServiceImpl;
import com.in.read.framework.constant.InreadConstant;
import com.in.read.framework.convert.ConvertUtils;
import com.in.read.framework.exception.ApiErrorCode;
import com.in.read.framework.exception.BusinessException;
import com.in.read.framework.security.UserUtil;
import com.in.read.framework.util.BeanUtil;
import com.in.read.pojo.note.comment.CommentVo;
import com.in.read.pojo.note.note.NoteAddReq;
import com.in.read.pojo.note.note.NotePageReq;
import com.in.read.pojo.note.note.NoteVo;
import com.in.read.pojo.note.user.UserVo;
import com.in.read.user.entity.User;
import com.in.read.user.mapper.UserMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author Generator
 * @since 2019-01-06
 */
@Service
public class NoteServiceImpl extends BaseServiceImpl<NoteMapper, Note> implements NoteService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NoteInteractionMapper noteInteractionMapper;

    @Autowired
    private LikeMapper likeMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public IPage<NoteVo> list(NotePageReq req) {
        IPage<Note> page;
        if(req.isAppend() && req.getTimestamp() > 0){
            page = new Page<>(1, req.getSize());
            page = baseMapper.selectPage(page, new QueryWrapper<Note>()
                    .lambda()
                    .eq(Note::getDeleted, InreadConstant.DB_VALID)
                    .ge(Note::getCreateTime, new Date(req.getTimestamp()))
                    .orderByDesc(Note::getCreateTime));
        }else {
            page = new Page<>(req.getPage(), req.getSize());
            page = baseMapper.selectPage(page, new QueryWrapper<Note>()
                    .lambda()
                    .eq(Note::getDeleted, InreadConstant.DB_VALID)
                    .orderByDesc(Note::getCreateTime));
        }
        return page.convert(note -> convert(note));
    }

    @Override
    public NoteVo getNote(int id) {
        Note note = baseMapper.selectById(id);
        if(note != null){
            return convert(note);
        }
        return null;
    }

    @Override
    public void add(NoteAddReq req) throws BusinessException {
        Note note;
        if (req.getNoteId() == null) {
            note = new Note();
            BeanUtil.copyProperties(req, note);
            note.setUid(UserUtil.getLoginUId());
            baseMapper.insert(note);
        } else {
            note = baseMapper.selectById(req.getNoteId());
            if (note == null) {
                throw new BusinessException(ApiErrorCode.PARAMETER_ERROR);
            }
            if (note.getUid() != UserUtil.getLoginUId()) {
                throw new BusinessException(ApiErrorCode.ACCESS_ERROR);
            }
            note.setAuthor(req.getAuthor());
            note.setBook(req.getBook());
            note.setContent(req.getContent());
            baseMapper.updateById(note);
        }
    }

    private NoteVo convert(Note note) {
        NoteVo noteVo = ConvertUtils.convert(NoteVo.class, note);
        User user = userMapper.selectById(note.getUid());
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        noteVo.setUser(userVo);
        noteVo.setNoteId(note.getId());
        noteVo.setCreateDate(DateUtil.date2Str(note.getCreateTime(), DateUtil.MD_FORMAT));
        if(!StringUtils.isEmpty(noteVo.getBook())) {
            noteVo.setTitle(noteVo.getTitle() + "·" + noteVo.getBook());
        }

        NoteInteraction noteInteraction = noteInteractionMapper.selectOne(new QueryWrapper<NoteInteraction>()
                .lambda()
                .eq(NoteInteraction::getNoteId, note.getId()));
        if(noteInteraction != null){
            noteVo.setCommentNum(noteInteraction.getCommentNum());
            noteVo.setLikeNum(noteInteraction.getLikeNum());
            noteVo.setShareNum(noteInteraction.getShareNum());
        }
        noteVo.setTimestamp(note.getCreateTime().getTime());
        List<Comment> comments = commentMapper.selectByNoteId(note.getId(), 1);
        if(comments != null && comments.size() > 0){
            List<CommentVo> commentVos = new ArrayList<>(1);
            for(Comment comment : comments) {
                CommentVo commentVo = new CommentVo();
                commentVo.setContent(comment.getContent());
                commentVo.setNoteId(note.getId());
                commentVo.setToUid(comment.getToUid());
                User commentUser = userMapper.selectById(comment.getFromUid());
                if(commentUser != null){
                    UserVo commentUserVo = new UserVo();
                    BeanUtils.copyProperties(commentUser, commentUserVo);
                    commentVo.setFromUser(commentUserVo);
                }
//                if(comment.getToUid() > 0) {
//                    commentUser = userMapper.selectById(comment.getToUid());
//                    UserVo commentUserVo = new UserVo();
//                    BeanUtils.copyProperties(commentUser, commentUserVo);
//                    commentVo.setToUser(commentUserVo);
//                }
                commentVos.add(commentVo);
            }
            noteVo.setComments(commentVos);
        }else{
            noteVo.setComments(Collections.emptyList());
        }
        return noteVo;
    }
}
