package com.in.read.pojo.note.comment;

import lombok.Data;

/**
 * Created by luyun on 2019/1/6.
 */
@Data
public class CommentAddReq {
    private int noteId;
    private String content;
    private int toUId;
}
