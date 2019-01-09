package com.in.read.framework.constant;

/**
 * Created by luyun on 2019/1/8.
 */
public interface WeChatConstant {
    String APP_ID = "wxb0e5b93775bef15c";
    String APP_SECRET = "241db367973b31c548a61e9f26273ae3";
    String CODE_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session?appid="
            + APP_ID + "&secret="
            + APP_SECRET
            + "&js_code=JSCODE&grant_type=";
}
