package com.java.xue.service.fallback;

import com.github.lianjiatech.retrofit.spring.boot.degrade.FallbackFactory;
import com.java.xue.domain.dto.User;
import com.java.xue.service.HttpApiService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;
import java.util.Map;

/**
 * @author xue.zeng
 * @date 2021/1/8 2:46 PM
 */
@Component
@Slf4j
public class HttpDegradeFallbackFactory  implements FallbackFactory<HttpApiService> {
  @Override
  public HttpApiService create(Throwable cause) {
    log.error("触发熔断了! ", cause.getMessage(), cause);
    return new HttpApiService() {
      @Override
      public Response<User> user(String id) {
        return null;
      }

      @Override
      public Call<List<User>> users() {
        return null;
      }

      @Override
      public Call<ResponseBody> addUser(User user) {
        return null;
      }

      @Override
      public Call<ResponseBody> updateUser(User user) {
        return null;
      }

      @Override
      public Call<ResponseBody> deleteUser(String id) {
        return null;
      }

      @Override
      public Call<ResponseBody> upload(MultipartBody.Part file) {
        return null;
      }

      @Override
      public Response<Map<String, Object>> dynamicUrl(String url, String name) {
        return null;
      }
    };
  }
}
