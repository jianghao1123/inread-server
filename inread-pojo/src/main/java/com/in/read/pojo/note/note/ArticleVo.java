package com.in.read.pojo.note.note;

import lombok.Data;

@Data
public class ArticleVo {

    private int id;

    private String content;

    private Integer type;

    private NoteVo note;
}
