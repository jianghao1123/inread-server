package com.in.read.article.controller;


import com.in.read.article.service.LikeService;
import com.in.read.framework.exception.BusinessException;
import com.in.read.framework.protocol.R;
import com.in.read.pojo.note.NoteLikeReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 点赞 前端控制器
 * </p>
 *
 * @author Generator
 * @since 2019-01-06
 */
@Controller
@RequestMapping("/inread-api/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/note")
    public R like(@Validated @RequestBody NoteLikeReq req) throws BusinessException {
        likeService.like(req);
        return R.ok();
    }
}

