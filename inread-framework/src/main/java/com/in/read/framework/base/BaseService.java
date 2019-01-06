package com.in.read.framework.base;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * Created by luyun on 2019/1/6.
 */
public interface BaseService<T> extends IService<T> {

    /**
     * 逻辑删除
     *
     * @param ids id集合(逗号分隔)
     * @return
     */
    boolean deleteLogic(List<Integer> ids);

}
