package com.java.xue.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.xue.service.WorldClockServiceGateway;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ExternalWorldClockServiceGateway implements WorldClockServiceGateway {
  private OkHttpClient client;

  public ExternalWorldClockServiceGateway() {
    this.client = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();
  }
  @Override
  public LocalTime getTime(String timezone) {
    final Request request = new Request.Builder()
            .url("http://worldclockapi.com/api/json/"+ timezone + "/now")
            .build();

    try (Response response = client.newCall(request).execute()) {
      final String content = response.body().string();
      final JSONObject worldTimeObject = JSON.parseObject(content);
      final String currentTime = worldTimeObject.get("currentDateTime").toString();
      final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
      LocalDateTime localDateTime = LocalDateTime.parse(currentTime, formatter);
      return localDateTime.toLocalTime();

    } catch(IOException e) {
      throw new IllegalStateException(e);
    }
  }
}
