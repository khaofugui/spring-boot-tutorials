package com.java.xue.controller;

import com.java.xue.domain.dto.Result;
import com.java.xue.domain.dto.User;
import com.java.xue.service.UserApiService;
import com.java.xue.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * @author xue.zeng
 * @date 2021/1/8 10:13 AM
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {
  @Autowired UserService userService;

  @Autowired
  UserApiService userApiService;

  @PostMapping("/add")
  public String addUser(@RequestBody User user) {
    userService.addUser(user);
    return "add success";
  }

  @PutMapping("/update")
  public String updateUser(@RequestBody User user) {
    userService.updateUser(user);
    return "update success";
  }

  @DeleteMapping("/{id}/delete")
  public String deleteUser(@PathVariable String id) {
    userService.deleteUser(id);
    return "delete success";
  }

  @GetMapping("/{id}")
  public User user(@PathVariable String id) {
    return userService.user(id);
  }

  @GetMapping("/users")
  public List<User> users() {
    return (List<User>) userService.AllUser();
  }

  @GetMapping("/{id}/retrofit")
  public Result<User> userApi(@PathVariable String id) {
    User user = userApiService.getUser(id);
    return Result.success(user);
  }

  @PostMapping("/upload")
  public String upload(@RequestParam("file") MultipartFile file)
      throws UnsupportedEncodingException {
    if (file.isEmpty()) {
      return "上传失败，请选择文件";
    }

    String fileName =
        URLDecoder.decode(
            Objects.requireNonNull(file.getOriginalFilename()), StandardCharsets.UTF_8.name());
    String filePath = "C:/";
    File dest = new File(filePath + fileName);
    try {
      file.transferTo(dest);
      log.info("上传成功");
      return "上传成功";
    } catch (IOException e) {
      log.error(e.toString(), e);
    }
    return "上传失败！";
  }
}
