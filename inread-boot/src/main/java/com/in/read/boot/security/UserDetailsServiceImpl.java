package com.in.read.boot.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.in.read.framework.security.UserDetailsImpl;
import com.in.read.user.entity.User;
import com.in.read.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * Created by luyun on 2018/8/1.
 */
@Service
@Qualifier("UserDetailsServiceImpl")
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
