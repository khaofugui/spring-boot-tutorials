package com.java.xue.interceptor;

import com.github.lianjiatech.retrofit.spring.boot.interceptor.BasePathMatchInterceptor;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * 注解式拦截器步骤:<br>
 *
 * <p>1、继承BasePathMatchInterceptor编写拦截处理器；
 *
 * <p>2、接口上使用@Intercept进行标注。如需配置多个拦截器，在接口上标注多个@Intercept注解即可！
 *
 * @author xue.zeng
 * @date 2021/1/8 12:42 PM
 */
public class TimeStampInterceptor extends BasePathMatchInterceptor {
  @Override
  protected Response doIntercept(final Chain chain) throws IOException {
    final Request request = chain.request();
    final HttpUrl url = request.url();
    final long timestamp = System.currentTimeMillis();
    final HttpUrl newUrl = url.newBuilder().addQueryParameter("timestamp", String.valueOf(timestamp)).build();
    final Request newRequest = request.newBuilder().url(newUrl).build();
    return chain.proceed(newRequest);
  }
}
