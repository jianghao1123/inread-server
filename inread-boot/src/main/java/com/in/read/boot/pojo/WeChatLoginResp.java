package com.in.read.boot.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Created by luyun on 2019/1/8.
 */
@Data
public class WeChatLoginResp {
    private String openid;// 用户唯一标识
    @JSONField(name = "session_key")
    private String sessionKey;// 会话密钥
    @JSONField(name = "unionid")
    private String unionId; // 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
    @JSONField(name = "errcode")
    private int errCode;// 错误码
    @JSONField(name = "errmsg")
    private String errMsg;// 错误信息
}
