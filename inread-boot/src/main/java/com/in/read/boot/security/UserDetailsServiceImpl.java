package com.in.read.boot.security;

import com.in.read.framework.security.UserDetailsImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * Created by luyun on 2018/8/1.
 */
@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (!StringUtils.isNumeric(s)) {
            return null;
        }
        int uid = Integer.valueOf(s);
        if(uid <= 0){
            return null;
        }
//        User user = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getId, uid));
//        //获取角色
//        if (user == null)
//            return null;
        return new UserDetailsImpl(uid);
    }
}
