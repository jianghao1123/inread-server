package com.in.read.pojo.note.note;

import lombok.Data;

@Data
public class ArticleAddReq {
    private String content;
    private int type;
    private int id;
}
