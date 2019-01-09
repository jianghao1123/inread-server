package com.in.read.boot.pojo;

import com.in.read.pojo.note.wx.WeChatUserInfo;
import lombok.Data;

/**
 * Created by luyun on 2019/1/8.
 */
@Data
public class WeChatLoginCodeReq {
    private String code;
    private String encryptedData;
    private String iv;
    private WeChatUserInfo userInfo;
}
