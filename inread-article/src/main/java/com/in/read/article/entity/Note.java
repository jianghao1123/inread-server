package com.in.read.article.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.in.read.framework.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author Generator
 * @since 2019-01-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("inread_note")
public class Note extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private int uid;

    private String content;

    private String book;
    private String author;

    private int likeNum;
    private int commentNum;

}
