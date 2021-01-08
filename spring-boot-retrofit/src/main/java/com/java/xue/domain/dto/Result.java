package com.java.xue.domain.dto;

/**
 * @author xue.zeng
 * @date 2021/1/8 10:29 AM
 */
import com.java.xue.domain.enumerate.ResponseEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Result<T> implements Serializable {
  private static final long serialVersionUID = 7694032958760634992L;

  private Integer code;
  private String message;
  private T data;
  private String url;
  private LocalDateTime timestamp;

  public Result() {}

  public Result(Integer code, String message) {
    this.code = code;
    this.message = message;
    this.timestamp = LocalDateTime.now();
  }

  public Result(Integer code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
    this.timestamp = LocalDateTime.now();
  }

  public static <T> Result<T> success() {
    return new Result<T>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMessage());
  }

  public static <T> Result<T> success(T data) {
    Result result = success();
    result.setData(data);
    return result;
  }

  public static <T> Result<T> failure(ResponseEnum responseEnum) {
    return new Result<T>(responseEnum.getCode(), responseEnum.getMessage());
  }

  public static <T> Result<T> failure(ResponseEnum responseEnum, T data) {
    Result<T> result = failure(responseEnum);
    result.setData(data);
    return result;
  }

  public static <T> Result<T> failure(Integer code, String message, T data) {
    Result<T> result = new Result<T>(code, message);
    result.setData(data);
    return result;
  }

  public static <T> Result<T> failure(Integer code, String message, T data, String url) {
    Result<T> result = failure(code, message, data);
    result.setUrl(url);
    return result;
  }
}
