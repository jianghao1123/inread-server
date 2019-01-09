package com.in.read.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.in.read.user.entity.Oauth;
import com.in.read.user.mapper.OauthMapper;
import com.in.read.user.service.OauthService;
import com.in.read.framework.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Generator
 * @since 2019-01-08
 */
@Service
public class OauthServiceImpl extends BaseServiceImpl<OauthMapper, Oauth> implements OauthService {

    @Override
    public Oauth selectByUnionIdAndType(int unionId, int type) {
        return baseMapper.selectOne(new QueryWrapper<Oauth>().lambda()
                .eq(Oauth::getUid, unionId)
                .eq(Oauth::getType, type));
    }
}
