package com.in.read.article.mapper;

import com.in.read.article.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author Generator
 * @since 2019-01-06
 */
public interface CommentMapper extends BaseMapper<Comment> {
    List<Comment> selectByNoteId(@Param("noteId") int nodeId, @Param("limitSize")int limitSize);

    List<Comment> selectReplyByNoteId(@Param("noteId") int nodeId, @Param("limitSize")int limitSize);
}
