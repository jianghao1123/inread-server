package com.in.read.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.in.read.article.entity.Note;
import com.in.read.article.mapper.NoteMapper;
import com.in.read.article.service.NoteService;
import com.in.read.framework.base.BaseServiceImpl;
import com.in.read.framework.constant.InreadConstant;
import com.in.read.framework.exception.ApiErrorCode;
import com.in.read.framework.exception.BusinessException;
import com.in.read.framework.security.UserUtil;
import com.in.read.framework.util.BeanUtil;
import com.in.read.pojo.note.comment.NoteAddReq;
import com.in.read.pojo.note.comment.NotePageReq;
import com.in.read.pojo.note.comment.NoteVo;
import com.in.read.pojo.note.user.UserVo;
import com.in.read.user.entity.User;
import com.in.read.user.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public IPage<NoteVo> list(NotePageReq req) {
        IPage<Note> page = new Page<>(req.getPage(), req.getSize());
        page = baseMapper.selectPage(page, new QueryWrapper<Note>()
                .lambda()
                .eq(Note::getDeleted, InreadConstant.DB_VALID)
                .orderByDesc(Note::getCreateTime));
        return page.convert(note -> convert(note));
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
        NoteVo noteVo = new NoteVo();
        BeanUtils.copyProperties(note, noteVo);
        User user = userMapper.selectById(note.getUid());
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        userVo.setUid(user.getId());
        noteVo.setUser(userVo);
        noteVo.setNoteId(note.getId());
        return noteVo;
    }
}
