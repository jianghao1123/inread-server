package com.in.read.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.in.read.framework.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author Generator
 * @since 2019-01-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("inread_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String avatar;

    private String username;

    private String summary;


}
