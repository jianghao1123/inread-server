package com.in.read.pojo.note;

import lombok.Data;

/**
 * Created by luyun on 2019/1/6.
 */
@Data
public class NoteLikeReq {
    private int noteId;

    public static final int LIKE = 1;
}
