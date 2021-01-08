package com.java.xue.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.java.xue.domain.dto.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

/**
 * @author xue.zeng
 * @date 2021/1/8 10:05 AM
 */
@Service
public class UserService {
  private static final Map<String, User> MOKE_DB_DATA = Maps.newHashMap();

  static {
    User user1 = User.builder().id("0000001").userName("userName1").password("111111").build();
    User user2 = User.builder().id("0000002").userName("userName2").password("222222").build();
    User user3 = User.builder().id("0000003").userName("userName3").password("333333").build();

    MOKE_DB_DATA.put("0000001", user1);
    MOKE_DB_DATA.put("0000002", user2);
    MOKE_DB_DATA.put("0000003", user3);
  }

  public void addUser(User user) {
    String key = String.valueOf(System.currentTimeMillis());
    user.setId(key);
    MOKE_DB_DATA.put(key, user);
  }

  public void updateUser(User user) {
    MOKE_DB_DATA.replace(user.getId(), user);
  }

  public void deleteUser(String id) {
    MOKE_DB_DATA.remove(id);
  }

  public Collection<User> AllUser() {
    return Lists.newArrayList(MOKE_DB_DATA.values());
  }

  public User user(String id) {
    return MOKE_DB_DATA.get(id);
  }
}
