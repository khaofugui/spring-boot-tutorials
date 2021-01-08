package com.java.xue.service.fallback;

import com.java.xue.domain.dto.User;
import com.java.xue.service.HttpApiService;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;
import java.util.Map;

/**
 * @author xue.zeng
 * @date 2021/1/8 2:45 PM
 */
public class HttpApiFallback implements HttpApiService {
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
}
