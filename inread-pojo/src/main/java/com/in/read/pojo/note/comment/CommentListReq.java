package com.in.read.pojo.note.comment;

import com.in.read.pojo.note.PageReq;
import lombok.Data;

/**
 * Created by luyun on 2019/1/6.
 */
@Data
public class CommentListReq extends PageReq{
    private int noteId;
    private int commentId;
}
