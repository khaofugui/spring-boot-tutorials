package com.java.xue.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xue.zeng
 * @date 2020/12/28 12:08 PM
 */
@Data
@ApiModel(value = "用户DTO")
public class UserDTO {
  @ApiModelProperty(value = "id")
  private Long id;
  @ApiModelProperty(value = "用户名", required = true, example = "xue.zeng")
  private String userName;
  @ApiModelProperty(value = "密码", required = true, example = "123456")
  private String password;
}
