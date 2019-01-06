package com.in.read.article.mapper;

import com.in.read.article.entity.Note;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 文章表 Mapper 接口
 * </p>
 *
 * @author Generator
 * @since 2019-01-06
 */
public interface NoteMapper extends BaseMapper<Note> {

    void incLike(@Param("nodeId") int nodeId);

    void incComment(@Param("nodeId") int nodeId);
}
