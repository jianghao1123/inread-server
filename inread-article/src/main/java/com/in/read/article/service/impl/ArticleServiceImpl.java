package com.in.read.article.service.impl;

import com.in.read.article.entity.Article;
import com.in.read.article.mapper.ArticleMapper;
import com.in.read.article.service.ArticleService;
import com.in.read.article.service.NoteService;
import com.in.read.framework.base.BaseServiceImpl;
import com.in.read.framework.convert.ConvertUtils;
import com.in.read.pojo.note.note.ArticleVo;
import com.in.read.pojo.note.note.NoteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Generator
 * @since 2019-01-24
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private NoteService noteService;

    @Override
    @Cacheable(value = ARTICLE_DETAIL_CACHE, key = "'ARTICLE_DETAIL_CACHE_' + #noteId")
    public ArticleVo getArticle(int noteId) {
        NoteVo noteVo = noteService.getNote(noteId);
        if(noteVo != null) {
            Article article = baseMapper.selectById(noteVo.getArticleId());
            if(article != null){
                ArticleVo articleVo = ConvertUtils.convert(ArticleVo.class, article);
                articleVo.setNote(noteVo);
                return articleVo;
            }
        }
        return null;
    }
}
