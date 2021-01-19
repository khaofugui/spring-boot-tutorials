package com.java.xue.controller;

import com.java.xue.domain.vo.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xue.zeng
 * @date 2021/1/18 6:31 PM
 */
@RestController
public class UserController {
  @GetMapping("/user/{username}")
  public UserVO user(@PathVariable(name = "username") String userName){
    return UserVO.builder().username(userName).email(userName.concat("@accenture.com")).build();
  }
}
