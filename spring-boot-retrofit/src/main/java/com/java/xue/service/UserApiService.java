package com.java.xue.service;

import com.github.lianjiatech.retrofit.spring.boot.annotation.Intercept;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.java.xue.domain.dto.User;
import com.java.xue.interceptor.TimeStampInterceptor;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author xue.zeng
 * @date 2021/1/8 10:14 AM
 */
@RetrofitClient(baseUrl = "${user.baseUrl}")
@Intercept(handler = TimeStampInterceptor.class, include = "/api/**", exclude = "/api/user/save")
public interface UserApiService {
  @GET("user/{id}")
  User getUser(@Path("id") String id);

  @POST("/user/save")
  boolean saveUser(@Body User user);
}
