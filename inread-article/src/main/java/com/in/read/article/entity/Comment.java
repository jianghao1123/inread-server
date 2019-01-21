package com.in.read.article.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.in.read.framework.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 评论
 * </p>
 *
 * @author Generator
 * @since 2019-01-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("inread_comment")
public class Comment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private int noteId;

    /**
     * 评论人id
     */
    private int fromUid;

    /**
     * 被评论者，为0对note评论
     */
    private int toUid;

    private int type;

    /**
     * 评论内容
     */
    private String content;


}
