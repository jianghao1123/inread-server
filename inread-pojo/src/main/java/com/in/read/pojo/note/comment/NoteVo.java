package com.in.read.pojo.note.comment;

import com.in.read.pojo.note.user.UserVo;
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
