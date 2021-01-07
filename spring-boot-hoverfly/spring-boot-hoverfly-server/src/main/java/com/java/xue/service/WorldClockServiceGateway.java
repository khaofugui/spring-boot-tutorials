package com.java.xue.service;

import java.time.LocalTime;

public interface WorldClockServiceGateway {
  LocalTime getTime(String timezone);
}
