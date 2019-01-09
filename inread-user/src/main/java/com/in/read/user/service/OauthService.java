package com.in.read.user.service;

import com.in.read.user.entity.Oauth;
import com.in.read.framework.base.BaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Generator
 * @since 2019-01-08
 */
public interface OauthService extends BaseService<Oauth> {
    Oauth selectByUnionIdAndType(int unionId, int type);
}
