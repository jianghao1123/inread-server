package com.in.read.framework.security;

import com.in.read.framework.exception.TokenExpiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;


/**
 * Created by luyun on 2018/3/11.
 * <p>
 * 登录处理，验证用户名和密码
 */
@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    @Qualifier("UserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");
        UserDetails userDetails;
        if(!StringUtils.isEmpty(authentication.getPrincipal())) {
            String token = String.valueOf(authentication.getPrincipal());
            int uid = jwtTokenUtil.validate(token);
            userDetails = userDetailsService
                    .loadUserByUsername(String.valueOf(uid));
            if (userDetails == null
                    || !userDetails.isEnabled()) {
                return null;
            }
        }else{
            return null;
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails == null ? null : userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
