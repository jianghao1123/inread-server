package com.in.read.user.service;

import com.in.read.pojo.note.wx.WeChatUserInfo;
import com.in.read.user.entity.User;
import com.in.read.framework.base.BaseService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author Generator
 * @since 2019-01-08
 */
public interface UserService extends BaseService<User> {
    User register(WeChatUserInfo userInfo);
}
