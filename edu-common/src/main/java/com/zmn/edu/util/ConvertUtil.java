package com.zmn.edu.util;

import net.sf.cglib.beans.BeanCopier;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 类描述: 属性拷贝工具类
 *
 * @author mujunlin
 * @since 2022/6/10 22:53
 */
public class ConvertUtil {

    /**
     * 将原对象的属性值，拷贝到目标对象对应的属性中
     *
     * @param source 原对象
     * @param target 目标对象
     * @return 属性拷贝完成的目标对象
     */
    public static <S, T> T convert(S source, T target) {
        if (source == null || target == null) {
            return null;
        }
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);
        return target;
    }

    public static <S, T> T convert(S source, Class<T> target) {
        try {
            return convert(source, target.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 拷贝列表
     */
    public static <S, T> List<T> convertList(List<S> source, Class<T> target) {
        if (source == null) {
            return null;
        }
        return source.stream().map(item -> {
            T result = null;
            try {
                result = target.newInstance();
                convert(item, result);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return result;
        }).collect(Collectors.toList());
    }

}
