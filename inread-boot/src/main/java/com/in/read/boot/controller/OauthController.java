package com.in.read.boot.controller;


import com.in.read.boot.pojo.WeChatLoginCodeReq;
import com.in.read.boot.service.WeChatOAuthLoginService;
import com.in.read.framework.exception.BusinessException;
import com.in.read.framework.protocol.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Generator
 * @since 2019-01-08
 */
@Controller
@RequestMapping("/inread-api/oauth")
public class OauthController {

    @Autowired
    private WeChatOAuthLoginService weChatOAuthLoginService;

    @PostMapping("/login")
    public R login(@Validated @RequestBody WeChatLoginCodeReq req) throws BusinessException {
        return R.ok(weChatOAuthLoginService.login(req));
    }

}

