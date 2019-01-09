package com.in.read.article.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.in.read.article.entity.Note;
import com.in.read.framework.base.BaseService;
import com.in.read.framework.exception.BusinessException;
import com.in.read.pojo.note.comment.NoteAddReq;
import com.in.read.pojo.note.comment.NotePageReq;
import com.in.read.pojo.note.comment.NoteVo;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author Generator
 * @since 2019-01-06
 */
public interface NoteService extends BaseService<Note> {
    IPage<NoteVo> list(NotePageReq req);
    void add(NoteAddReq req) throws BusinessException;
}
