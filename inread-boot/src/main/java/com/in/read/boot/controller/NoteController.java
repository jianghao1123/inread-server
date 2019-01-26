package com.in.read.boot.controller;


import com.in.read.article.service.NoteInteractionService;
import com.in.read.article.service.NoteService;
import com.in.read.framework.constant.ErrorCode;
import com.in.read.framework.exception.ApiErrorCode;
import com.in.read.framework.exception.BusinessException;
import com.in.read.framework.protocol.R;
import com.in.read.pojo.note.note.NoteAddReq;
import com.in.read.pojo.note.note.NotePageReq;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author Generator
 * @since 2019-01-06
 */
@RestController
@RequestMapping("/inread-api/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteInteractionService noteInteractionService;

    @PostMapping("/list")
    public R getPartnerOrderList(@Validated @RequestBody NotePageReq req) {
        return R.ok(noteService.list(req));
    }

    @PostMapping("/add")
    public R add(@Validated @RequestBody NoteAddReq req) throws BusinessException {
        noteService.add(req);
        return R.ok();
    }

    @PostMapping("/share")
    public R share(@Validated @RequestBody Map<String,Object> requestBody) throws BusinessException {
        if(requestBody.get("noteId") == null){
            throw new BusinessException(ApiErrorCode.PARAMETER_ERROR);
        }
        if(!StringUtils.isNumeric(requestBody.get("noteId").toString())){
            return R.ok();
        }
        noteInteractionService.incShare(Integer.parseInt(requestBody.get("noteId").toString()));
        return R.ok();
    }

}

