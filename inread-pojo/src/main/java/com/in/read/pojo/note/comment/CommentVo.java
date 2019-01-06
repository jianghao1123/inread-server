package com.in.read.pojo.note.comment;

import com.in.read.pojo.note.UserVo;
import lombok.Data;

/**
 * Created by luyun on 2019/1/6.
 */
@Data
public class CommentVo extends CommentAddReq {
    private UserVo fromUser;
    // coding
    private UserVo toUser;
}
