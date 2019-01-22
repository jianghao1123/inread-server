package com.in.read.pojo.note.comment;

import com.in.read.pojo.note.user.UserVo;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by luyun on 2019/1/6.
 */
@Data
public class CommentVo extends CommentAddReq {
    private UserVo fromUser;
    private UserVo toUser;
    // 回复列表
    private List<CommentVo> replyItems;
    private int replyItemCount;
    private Date createTime;
    private Integer id;
    private int likeNum;
}
