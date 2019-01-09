package com.in.read.user.generator;


import com.in.read.framework.generator.InreadGenerator;

/**
 * Created by luyun on 2019/1/6.
 */
public class UserGenerator {
    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        InreadGenerator generator = new InreadGenerator();
        generator.setPackageName("com.in.read.user");
        generator.setPackageDir("/inread-user/src/main/java");
        generator.setTablePrefix(new String[]{"inread_"});
        generator.setIncludeTables(new String[]{"inread_user","inread_oauth"});
        generator.run();
    }
}
