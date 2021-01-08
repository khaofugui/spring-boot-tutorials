package com.java.xue.service;

import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xue.zeng
 * @date 2021/1/8 5:04 PM
 */
public interface ThumbnailsService {
  String changeSize(MultipartFile resource, int width, int height);

  String changeScale(MultipartFile resource, double scale);

  String watermark(MultipartFile resource, Positions p, MultipartFile shuiyin, float opacity);

  String rotate(MultipartFile resource, double rotate);

  String region(MultipartFile resource, Positions p, int width, int height);
}
