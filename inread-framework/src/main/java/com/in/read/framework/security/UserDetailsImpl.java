package com.in.read.framework.security;

import com.in.read.framework.constant.InreadConstant;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * Created by luyun on 2018/8/1.
 */
@Data
public class UserDetailsImpl implements UserDetails {

    private int uid;

    private String userName;

    private GrantedAuthority authorities;


    private int status;

    public UserDetailsImpl(int uid) {
        this.uid = uid;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status == InreadConstant.DB_VALID;
    }

    @Override
    public String getPassword() {
        return null;
    }
}
