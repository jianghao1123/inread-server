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
 * @since 2019-01-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("inread_oauth_info")
public class OauthInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer uid;

    private String province;

    private String city;

    private Integer gender;

    private String language;


}
