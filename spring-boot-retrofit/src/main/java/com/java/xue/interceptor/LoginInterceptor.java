package com.java.xue.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author xue.zeng
 * @date 2021/1/8 1:20 PM
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String accessKeyId = request.getHeader("accessKeyId");
    String accessKeySecret = request.getHeader("accessKeySecret");

    if (null == accessKeyId
        || "".equals(accessKeyId)
        || null == accessKeySecret
        || "".equals(accessKeySecret)) {
      error(response, "缺少头部信息");
      return false;
    }
    return true;
  }

  /**
   * 错误信息
   *
   * @param response
   * @param msg
   */
  private void error(HttpServletResponse response, String msg) {
    response.setCharacterEncoding(StandardCharsets.UTF_8.name());
    response.setHeader("Content-Type", "application/json");
    try {
      PrintWriter writer = response.getWriter();
      // 将返回的错误提示压入流中
      writer.write(msg);
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
