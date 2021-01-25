package com.java.xue.service;

import com.java.xue.domian.entity.User;

import java.util.List;

/**
 * @author xue.zeng
 * @date 2021/1/22 4:51 PM
 */
public interface UserService {
  /**
   * 新增一个用户
   *
   * @param name
   * @param age
   */
  int create(String name, Integer age);

  /**
   * 根据name查询用户
   *
   * @param name
   * @return
   */
  List<User> getByName(String name);

  /**
   * 根据name删除用户
   *
   * @param name
   */
  int deleteByName(String name);

  /**
   * 获取用户总量
   */
  int getAllUsers();

  /**
   * 删除所有用户
   */
  int deleteAllUsers();
}
