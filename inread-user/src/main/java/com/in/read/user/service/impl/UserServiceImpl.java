package com.in.read.user.service.impl;

import com.in.read.pojo.note.wx.WeChatUserInfo;
import com.in.read.user.constant.OAuthType;
import com.in.read.user.entity.Oauth;
import com.in.read.user.entity.OauthInfo;
import com.in.read.user.entity.User;
import com.in.read.user.mapper.OauthInfoMapper;
import com.in.read.user.mapper.OauthMapper;
import com.in.read.user.mapper.UserMapper;
import com.in.read.user.service.UserService;
import com.in.read.framework.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author Generator
 * @since 2019-01-08
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private OauthInfoMapper oauthInfoMapper;

    @Autowired
    private OauthMapper oauthMapper;

    @Override
    @Transactional
    public User register(WeChatUserInfo userInfo) {
        User user = new User();
        user.setAvatar(userInfo.getAvatarUrl());
        user.setUsername(userInfo.getNickName());
        baseMapper.insert(user);

        OauthInfo oauthInfo = new OauthInfo();
        oauthInfo.setCity(userInfo.getCity());
        oauthInfo.setGender(userInfo.getGender());
        oauthInfo.setLanguage(userInfo.getLanguage());
        oauthInfo.setProvince(userInfo.getProvince());
        oauthInfo.setUid(user.getId());
        oauthInfoMapper.insert(oauthInfo);

        Oauth oauth = new Oauth();
        oauth.setType(OAuthType.WeChat.getType());
        oauth.setUnionId(oauth.getUnionId());
        oauth.setUid(user.getId());
        oauthMapper.insert(oauth);
        return user;
    }
}
