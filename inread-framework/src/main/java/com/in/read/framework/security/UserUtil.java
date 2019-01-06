package com.in.read.framework.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by luyun on 2019/1/6.
 */
public class UserUtil {

    public static final int getLoginUId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null || "anonymousUser".equals(auth.getPrincipal())) {
            return 0;
        }
        UserDetailsImpl details = (UserDetailsImpl) auth.getPrincipal();
        return details.getUid();
    }
}
