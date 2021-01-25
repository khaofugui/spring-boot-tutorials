package com.java.xue;

import com.java.xue.domian.entity.User;
import com.java.xue.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author xue.zeng
 * @date 2021/1/22 5:01 PM
 */
@Slf4j
@SpringBootTest
public class FlywayApplicationTest {
  @Autowired
  private UserService userSerivce;

  @Test
  public void test() throws Exception {
    userSerivce.deleteAllUsers();

    // 插入5个用户
    userSerivce.create("Test1", 22);
    userSerivce.create("Test2", 11);
    userSerivce.create("Test3", 41);
    userSerivce.create("Test4", 12);
    userSerivce.create("Test5", 34);

    // 查询名为Oscar的用户，判断年龄是否匹配
    List<User> userList = userSerivce.getByName("Test4");
    Assertions.assertEquals(12, userList.get(0).getAge().intValue());

    // 查数据库，应该有5个用户
    Assertions.assertEquals(5, userSerivce.getAllUsers());

    // 删除两个用户
    userSerivce.deleteByName("Test2");
    userSerivce.deleteByName("Test5");

    // 查数据库，应该有5个用户
    Assertions.assertEquals(3, userSerivce.getAllUsers());
  }

}
