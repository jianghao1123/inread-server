package com.in.read.boot.controller;


import com.in.read.article.service.NoteService;
import com.in.read.framework.exception.BusinessException;
import com.in.read.framework.protocol.R;
import com.in.read.pojo.note.comment.NoteAddReq;
import com.in.read.pojo.note.comment.NotePageReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author Generator
 * @since 2019-01-06
 */
@Controller
@RequestMapping("/inread-api/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/list")
    public R getPartnerOrderList(@Validated @RequestBody NotePageReq req) {
        return R.ok(noteService.list(req));
    }

    @PostMapping("/add")
    public R add(@Validated @RequestBody NoteAddReq req) throws BusinessException {
        noteService.add(req);
        return R.ok();
    }

}

