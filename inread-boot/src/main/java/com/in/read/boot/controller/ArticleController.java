package com.in.read.boot.controller;

import com.in.read.article.service.ArticleService;
import com.in.read.framework.exception.ApiErrorCode;
import com.in.read.framework.exception.BusinessException;
import com.in.read.framework.protocol.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * article
 */
@RestController
@RequestMapping("/inread-api/article")
public class ArticleController {

    @Autowired
    public ArticleService articleService;

    @GetMapping("/detail")
    public R getArticleDetail(@RequestParam("noteId") String noteId) throws BusinessException {
        if(!StringUtils.isNumeric(noteId)){
            throw new BusinessException(ApiErrorCode.PARAMETER_ERROR);
        }
        return R.ok(articleService.getArticle(Integer.parseInt(noteId))).cache();
    }
}
