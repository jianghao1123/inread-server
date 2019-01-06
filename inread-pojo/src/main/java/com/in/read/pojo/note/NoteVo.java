package com.in.read.pojo.note;

import lombok.Data;

/**
 * Created by luyun on 2019/1/6.
 */
@Data
public class NoteVo extends NoteAddReq {
    private UserVo user;
    private int commentNum;
    private int likeNum;
}
