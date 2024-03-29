package com.in.read.article.mapper;

import com.in.read.article.entity.NoteInteraction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Generator
 * @since 2019-01-16
 */
public interface NoteInteractionMapper extends BaseMapper<NoteInteraction> {

    void incLike(@Param("noteId") int nodeId);

    void incComment(@Param("noteId") int nodeId);

    void incShare(@Param("noteId") int nodeId);
}
