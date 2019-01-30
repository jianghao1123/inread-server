package com.in.read.article.service;

import com.in.read.article.entity.Article;
import com.in.read.framework.base.BaseService;
import com.in.read.pojo.note.note.ArticleVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Generator
 * @since 2019-01-24
 */
public interface ArticleService extends BaseService<Article> {

    String ARTICLE_DETAIL_CACHE = "ARTICLE_DETAIL_CACHE";

    ArticleVo getArticle(int noteId);
}
