package com.in.read.pojo.note.note;

import com.in.read.pojo.note.comment.CommentVo;
import com.in.read.pojo.note.user.UserVo;
import lombok.Data;

import java.util.List;

/**
 * Created by luyun on 2019/1/6.
 */
@Data
public class NoteVo extends NoteAddReq {
    private UserVo user;
    private int commentNum;
    private int likeNum;
    private int shareNum;
    private String createDate;
    private long timestamp;
    private int articleId;

    private List<CommentVo> comments;
}
