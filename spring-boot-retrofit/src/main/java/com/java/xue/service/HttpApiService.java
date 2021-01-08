package com.java.xue.service;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.github.lianjiatech.retrofit.spring.boot.degrade.Degrade;
import com.github.lianjiatech.retrofit.spring.boot.interceptor.LogStrategy;
import com.github.lianjiatech.retrofit.spring.boot.retry.Retry;
import com.java.xue.annotation.Sign;
import com.java.xue.domain.dto.User;
import com.java.xue.service.fallback.HttpApiFallback;
import com.java.xue.service.fallback.HttpDegradeFallbackFactory;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

/**
 * @author xue.zeng
 * @date 2021/1/8 2:43 PM
 */

/**
 * 请求重试
 * retrofit-spring-boot-starter支持请求重试功能，只需要在接口或者方法上加上@Retry注解即可。 @Retry支持重试次数maxRetries、重试时间间隔intervalMs以及重试规则retryRules配置。重试规则支持三种配置：
 *
 * <p>RESPONSE_STATUS_NOT_2XX：响应状态码不是2xx时执行重试；
 *
 * <p>OCCUR_IO_EXCEPTION：发生IO异常时执行重试；
 *
 * <p>OCCUR_EXCEPTION：发生任意异常时执行重试；
 *
 * <p>默认响应状态码不是2xx或者发生IO异常时自动进行重试。需要的话，你也可以继承BaseRetryInterceptor实现自己的请求重试拦截器，然后将其配置上去。
 *
 * <p>logStrategy = LogStrategy.BODY 把请求第三方接口的收到的返回值,打印出日志
 */
@Retry
@RetrofitClient(
    baseUrl = "${user.baseUrl}",
    fallback = HttpApiFallback.class,
    fallbackFactory = HttpDegradeFallbackFactory.class,
    logStrategy = LogStrategy.BODY)
@Sign(accessKeyId = "${user.accessKeyId}", accessKeySecret = "${user.accessKeySecret}")

/** 默认策略情况下，每5s平均响应时间不得超过500ms，否则启用熔断降级 */
@Degrade(count = 500)
public interface HttpApiService {
  /**
   * 方法上的路径不以/开头
   * <p>
   * <p>@Query("id") Long id 对于Retrofit而言，方法上的/开头表示直接接在domain后面的端点。
   */
  @GET("user/{id}")
  Response<User> user(@Path("id") String id);

  @GET("user")
  Call<List<User>> users();

  @POST("user")
  Call<ResponseBody> addUser(@Body User user);

  @PUT("user")
  Call<ResponseBody> updateUser(@Body User user);

  @DELETE("user/{id}")
  Call<ResponseBody> deleteUser(@Path("id") String id);

  @POST("upload")
  @Multipart
  Call<ResponseBody> upload(@Part MultipartBody.Part file);

  /**
   * 使用@url注解可实现动态URL。
   *
   * <p>注意：@url必须放在方法参数的第一个位置。原有定义@GET、@POST等注解上，不需要定义端点路径！
   *
   * @param url
   * @param name
   * @return
   */
  @GET
  Response<Map<String, Object>> dynamicUrl(@Url String url, @Query("name") String name);
}
