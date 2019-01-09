package com.in.read.user.entity;

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
 * @since 2019-01-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("inread_oauth")
public class Oauth extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer uid;

    /**
     * 三方关联id
     */
    private String unionId;

    private Integer type;


}
