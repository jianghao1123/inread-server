package com.in.read.boot.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.in.read.framework.constant.InreadConstant;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * Created by luyun on 2018/7/31.
 */
public class MyMetaObjectHandler implements MetaObjectHandler {

    public void insertFill(MetaObject metaObject) {
        // 更多查看源码测试用例
        Object createTime = getFieldValByName("createTime", metaObject);
        Object updateTime = getFieldValByName("updateTime", metaObject);
        if (createTime == null) {
            setFieldValByName("createTime", new Date(), metaObject);//mybatis-plus版本2.0.9+
        }
        if (updateTime == null) {
            setFieldValByName("updateTime", new Date(), metaObject);//mybatis-plus版本2.0.9+
        }

        Object delete = getFieldValByName("delete", metaObject);
        Object status = getFieldValByName("status", metaObject);
        if (delete == null) {
            setFieldValByName("delete", InreadConstant.DB_VALID, metaObject);//mybatis-plus版本2.0.9+
        }
        if (status == null) {
            setFieldValByName("status", InreadConstant.DB_VALID, metaObject);//mybatis-plus版本2.0.9+
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新填充
        //mybatis-plus版本2.0.9+
        setFieldValByName("updateTime", new Date(), metaObject);
    }
}
