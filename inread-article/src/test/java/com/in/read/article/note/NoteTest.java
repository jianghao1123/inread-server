package com.in.read.article.note;

import com.in.read.article.service.NoteService;
import com.in.read.framework.exception.BusinessException;
import com.in.read.pojo.note.comment.NoteAddReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by luyun on 2019/1/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteTest {

    @Autowired
    private NoteService noteService;

    @Test
    public void testAddNote() throws BusinessException {
        NoteAddReq noteAddReq = new NoteAddReq();
        noteAddReq.setAuthor("乔治•奥威尔");
        noteAddReq.setContent("在他的小办公室的墙上有三个口子。听写器右边的一个小口是送书面指示的气力输送管；左边大一些的口子是送报纸的；旁边墙上温斯顿伸手可及的地方有一个椭圆形的大口子，上面蒙着铁丝网，这是供处理废纸用的。整个大楼里到处都有这样的口子，为数成千上万，不仅每间屋子里都有，而且每条过道上相隔不远就有一个。这种口子外号叫忘怀洞。这样叫不无理由。凡是你想起有什么文件应该销毁，甚至你看到什么地方有一张废纸的时候，你就会顺手掀起近旁忘怀洞的盖子，把那文件或废纸丢进去，让一股暖和的气流把它吹卷到大楼下面不知什么地方的大锅炉中去烧掉。");
        noteService.add(noteAddReq);
    }
}
