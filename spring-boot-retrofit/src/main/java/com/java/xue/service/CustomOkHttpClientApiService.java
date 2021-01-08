package com.java.xue.service;

import com.github.lianjiatech.retrofit.spring.boot.annotation.OkHttpClientBuilder;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.java.xue.domain.dto.User;
import okhttp3.OkHttpClient;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.concurrent.TimeUnit;

/**
 * @author xue.zeng
 * @date 2021/1/8 12:36 PM
 */
@RetrofitClient(baseUrl = "${user.baseUrl}")
public interface CustomOkHttpClientApiService {

  /** 方法必须使用@OkHttpClientBuilder注解标记 */
  @OkHttpClientBuilder
  static OkHttpClient.Builder okHttpClientBuilder() {
    return new OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS);
  }

  @GET("user/{id}")
  User getUser(@Path("id") String id);
}
