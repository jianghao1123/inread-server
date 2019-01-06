package com.in.read.article.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.in.read.framework.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 点赞
 * </p>
 *
 * @author Generator
 * @since 2019-01-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("inread_like")
public class Like extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private int uid;

    private int noteId;

}
