package com.java.xue.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HoverflyServiceResponse {
  private String message;
  private String responseTime;
  private String transactionId;
}
