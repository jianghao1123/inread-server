package com.in.read.article.generator;


import com.in.read.framework.generator.InreadGenerator;

/**
 * Created by luyun on 2019/1/6.
 */
public class ArticleGenerator {
    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        InreadGenerator generator = new InreadGenerator();
        generator.setPackageName("com.in.read.article");
        generator.setPackageDir("/inread-article/src/main/java");
        generator.setTablePrefix(new String[]{"inread_"});
        generator.setIncludeTables(new String[]{"inread_note","inread_comment","inread_like","inread_user","inread_note_interaction"});
        generator.run();
    }
}
