package com.in.read.article.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.in.read.framework.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Generator
 * @since 2019-01-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("inread_note_interaction")
public class NoteInteraction extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer noteId;

    private Integer likeNum;

    private Integer commentNum;

    private Integer shareNum;

}
