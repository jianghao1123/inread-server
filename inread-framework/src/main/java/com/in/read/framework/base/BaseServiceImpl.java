package com.in.read.framework.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.in.read.framework.constant.InreadConstant;
import com.in.read.framework.util.BeanUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * Created by luyun on 2019/1/6.
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements BaseService<T> {

    private Class<T> modelClass;

    @SuppressWarnings("unchecked")
    public BaseServiceImpl() {
        Type type = this.getClass().getGenericSuperclass();
        this.modelClass = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[1];
    }

    @Override
    public boolean save(T entity) {
        Date now = new Date();
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
        entity.setStatus(InreadConstant.DB_VALID);
        entity.setDelete(InreadConstant.DB_VALID);
        return super.save(entity);
    }

    @Override
    public boolean updateById(T entity) {
        entity.setUpdateTime(new Date());
        return super.updateById(entity);
    }

    @Override
    public boolean deleteLogic(List<Integer> ids) {
        T entity = BeanUtil.newInstance(modelClass);
        entity.setUpdateTime(new Date());
        return super.update(entity, Wrappers.<T>update().lambda().in(T::getId, ids))
                && super.removeByIds(ids);
    }

}
