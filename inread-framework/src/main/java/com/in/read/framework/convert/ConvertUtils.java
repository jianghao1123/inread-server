package com.in.read.framework.convert;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by luyun on 2018/8/7.
 */
public class ConvertUtils {


    public static final <T, K> T convert(Class<T> clz, K k) {
        try {
            T t = clz.newInstance();
            BeanUtils.copyProperties(k, t);
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final <T, K> Collection<T> convert(Class<T> clz, Collection<K> ks) {
        List<T> ts = new ArrayList<>();
        if (ks != null && ks.size() != 0) {
            for (K k : ks) {
                T t = convert(clz, k);
                if (t != null) {
                    ts.add(t);
                }
            }
        }
        return ts;
    }
}
