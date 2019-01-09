package com.in.read.framework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by luyun on 2018/8/1.
 */
@EnableWebSecurity
@EnableTransactionManagement
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 登录
     */
    private static final String LOGIN_ENTRY_POINT = "/inread-api/login";

    private static final String OAUTH_LOGIN_ENTRY_POINT = "/inread-api/oauth/login";

    /**
     * 刷新token
     */
    private static final String TOKEN_REFRESH_ENTRY_POINT = "/inread-api/refresh_token";

    /**
     * api
     */
    private static final String TOKEN_ENTRY_POINT = "/inread-api/**";

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenAuthenticationProvider tokenAuthenticationProvider;
    @Autowired
    private AuthenticationFailureHandler failureHandler;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(tokenAuthenticationProvider);
    }

    private TokenAuthenticationProcessingFilter buildTokenProcessingFilter() throws Exception {
        TokenAuthenticationProcessingFilter filter = new TokenAuthenticationProcessingFilter(TOKEN_ENTRY_POINT, failureHandler);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENTRY_POINT).permitAll() // Login end-point
                .antMatchers(OAUTH_LOGIN_ENTRY_POINT).permitAll()
                .antMatchers(TOKEN_REFRESH_ENTRY_POINT).permitAll() // Token refresh end-point
                .antMatchers("/inread-api/note/list").permitAll()
                .anyRequest().authenticated() // Protected API End-points
                .and()
                .addFilterBefore(buildTokenProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

}
