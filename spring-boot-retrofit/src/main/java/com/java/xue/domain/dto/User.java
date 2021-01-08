package com.java.xue.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xue.zeng
 * @date 2021/1/7 6:58 PM
 */
@Data
@Builder
public class User implements Serializable {
  String id;
  String userName;
  String password;
}
