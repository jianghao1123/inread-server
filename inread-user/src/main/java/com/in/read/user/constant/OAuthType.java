package com.in.read.user.constant;

/**
 * Created by luyun on 2019/1/9.
 */
public enum  OAuthType {
    WeChat(0);

    int type;
    OAuthType(int type){
        this.type = type;
    }

    public int getType(){
        return type;
    }
}
