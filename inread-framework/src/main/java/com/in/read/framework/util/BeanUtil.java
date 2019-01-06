package com.in.read.framework.util;

/**
 * Created by luyun on 2019/1/6.
 */
public class BeanUtil extends org.springframework.beans.BeanUtils {

    /**
     * 实例化对象
     * @param clazz 类
     * @param <T> 泛型标记
     * @return 对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<?> clazz) {
        return (T) instantiateClass(clazz);
    }

    /**
     * 实例化对象
     * @param clazzStr 类名
     * @param <T> 泛型标记
     * @return 对象
     */
    public static <T> T newInstance(String clazzStr) {
        try {
            Class<?> clazz = Class.forName(clazzStr);
            return newInstance(clazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
