package com.in.read.boot.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.in.read.boot.pojo.WeChatLoginCodeReq;
import com.in.read.boot.pojo.WeChatLoginResp;
import com.in.read.common.WXUtils;
import com.in.read.framework.constant.WeChatConstant;
import com.in.read.framework.exception.BusinessException;
import com.in.read.boot.http.RestHttp;
import com.in.read.boot.security.JwtTokenUtil;
import com.in.read.pojo.note.wx.WeChatUserInfo;
import com.in.read.user.constant.OAuthType;
import com.in.read.user.entity.Oauth;
import com.in.read.user.entity.User;
import com.in.read.user.service.OauthService;
import com.in.read.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luyun on 2019/1/8.
 */
@Service
public class WeChatOAuthLoginService {

    @Autowired
    private OauthService oauthService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    public final Logger logger = LoggerFactory.getLogger(WeChatOAuthLoginService.class);

    public String login(WeChatLoginCodeReq req) throws BusinessException {
        try {
            Map<String, String> uriVariables = new HashMap(1);
            uriVariables.put("code",req.getCode());
            WeChatLoginResp resp = RestHttp.get(WeChatLoginResp.class
                    , WeChatConstant.CODE_SESSION_URL
                    , uriVariables);
            if (resp == null) {
                throw new BusinessException("微信登录失败");
            }
            // 如果uid为空
            if (StringUtils.isBlank(resp.getOpenid())) {
                if (StringUtils.isBlank(req.getEncryptedData())
                        || StringUtils.isBlank(req.getIv())
                        || StringUtils.isBlank(resp.getSessionKey())) {
                    throw new BusinessException("登录缺少必要参数");
                }
                String userInfo = WXUtils.getUserInfo(req.getEncryptedData()
                        , resp.getSessionKey()
                        , req.getIv());
                WeChatUserInfo weChatUserInfo = JSON.parseObject(userInfo, WeChatUserInfo.class);
                return getToken(weChatUserInfo);
            } else {
                req.getUserInfo().setOpenId(resp.getOpenid());
                return getToken(req.getUserInfo());
            }
        } catch (RestClientException e) {
            logger.error("请求微信session失败:" + e.getMessage());
        }
        throw new BusinessException("微信登录失败");
    }

    private String getToken(WeChatUserInfo weChatUserInfo) throws BusinessException {
        if (weChatUserInfo == null) {
            throw new BusinessException("获取用户信息失败");
        }
        if (StringUtils.isBlank(weChatUserInfo.getOpenId())) {
            throw new BusinessException("获取用户微信openId失败");
        }

        Oauth oauth = oauthService.getOne(new QueryWrapper<Oauth>().lambda()
                .eq(Oauth::getUnionId, weChatUserInfo.getOpenId())
                .eq(Oauth::getType, OAuthType.WeChat.getType()));
        int uid;
        if (oauth == null) {
            User user = userService.register(weChatUserInfo);
            uid = user.getId();
        } else {
            uid = oauth.getUid();
        }
        return jwtTokenUtil.sign(uid);
    }
}
