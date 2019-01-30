package com.in.read.pojo.note.note;

import com.in.read.pojo.note.PageReq;
import lombok.Data;

/**
 * Created by luyun on 2019/1/6.
 */
@Data
public class NotePageReq extends PageReq {
    private int type;
    private int maxStamp;
}
