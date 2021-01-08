package com.java.xue.domain.enumerate;

/**
 * 枚举接口
 *
 * @author xue.zeng
 * @date 2021/1/8 10:31 AM
 */
public interface BaseEnum<E extends Enum<E>, T> {
  T getValue();
}