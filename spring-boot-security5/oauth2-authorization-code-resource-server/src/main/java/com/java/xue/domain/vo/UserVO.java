package com.java.xue.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xue.zeng
 * @date 2021/1/18 6:28 PM
 */
@Data
@Builder
public class UserVO implements Serializable {
  private static final long serialVersionUID = 2076287443343803781L;
  String username;
  String email;
}
