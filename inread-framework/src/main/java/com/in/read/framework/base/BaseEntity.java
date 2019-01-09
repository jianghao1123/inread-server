package com.in.read.framework.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * Created by luyun on 2019/1/5.
 */
@Data
public class BaseEntity {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态[1:正常]
     */
    private int status;

    /**
     * 状态[0:未删除,1:删除]
     */
    @TableLogic
    private int deleted;
}
