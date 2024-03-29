package com.in.read.boot.controller;


import com.in.read.article.service.CommentService;
import com.in.read.boot.security.UserUtil;
import com.in.read.framework.exception.BusinessException;
import com.in.read.framework.protocol.R;
import com.in.read.pojo.note.comment.CommentAddReq;
import com.in.read.pojo.note.comment.CommentListReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author Generator
 * @since 2019-01-06
 */
@RestController
@RequestMapping("/inread-api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public R commentNote(@Validated @RequestBody CommentAddReq req) throws BusinessException {
        return R.ok(commentService.add(UserUtil.getLoginUId(), req));
    }

    @PostMapping("/list")
    public R list(@Validated @RequestBody CommentListReq req) {
        return R.ok(commentService.list(req));
    }
}

