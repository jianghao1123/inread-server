package com.in.read.pojo.note;

import lombok.Data;

/**
 * Created by luyun on 2019/1/6.
 */
@Data
public class NoteAddReq {
    private String content;
    private String book;
    private String author;
    private Integer noteId;
}
