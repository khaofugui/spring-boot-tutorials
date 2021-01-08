package com.java.xue.service;

import com.java.xue.util.DateUtil;
import com.java.xue.util.ThumbnailsUtil;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author xue.zeng
 * @date 2021/1/8 5:04 PM
 */
@Slf4j
@Service
public class ThumbnailsServiceImpl implements ThumbnailsService {
  @Value("thumbnails.image.dir:")
  String imageDir;

  @Override
  public String changeSize(MultipartFile resource, int width, int height) {
    final String originalFilename = resource.getOriginalFilename();
    if (StringUtils.isBlank(originalFilename)) {
      log.warn("Failed to get resource filename. {}", resource);
      return null;
    }

    String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
    String filePath = saveFile(resource, fileSuffix);
    String toFile =
        filePath.substring(0, filePath.lastIndexOf("/") + 1) + UUID.randomUUID() + fileSuffix;
    ThumbnailsUtil.changeSize(filePath, width, height, toFile);
    return toFile;
  }

  @Override
  public String changeScale(MultipartFile resource, double scale) {
    final String originalFilename = resource.getOriginalFilename();
    if (StringUtils.isBlank(originalFilename)) {
      log.warn("Failed to get resource filename. {}", resource);
      return null;
    }

    String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
    String filePath = saveFile(resource, fileSuffix);
    String toFile =
        filePath.substring(0, filePath.lastIndexOf("/") + 1) + UUID.randomUUID() + fileSuffix;
    ThumbnailsUtil.changeScale(filePath, scale, toFile);
    return toFile;
  }

  @Override
  public String watermark(
      MultipartFile resource, Positions p, MultipartFile shuiyin, float opacity) {
    final String originalFilename = resource.getOriginalFilename();
    if (StringUtils.isBlank(originalFilename)) {
      log.warn("Failed to get resource filename. {}", resource);
      return null;
    }

    String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
    String filePath = saveFile(resource, fileSuffix);
    String watermark = saveFile(shuiyin, fileSuffix);
    String toFile =
        filePath.substring(0, filePath.lastIndexOf("/") + 1) + UUID.randomUUID() + fileSuffix;
    ThumbnailsUtil.watermark(filePath, p, watermark, opacity, toFile);
    return toFile;
  }

  @Override
  public String rotate(MultipartFile resource, double rotate) {
    final String originalFilename = resource.getOriginalFilename();
    if (StringUtils.isBlank(originalFilename)) {
      log.warn("Failed to get resource filename. {}", resource);
      return null;
    }

    String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
    String filePath = saveFile(resource, fileSuffix);
    String toFile =
        filePath.substring(0, filePath.lastIndexOf("/") + 1) + UUID.randomUUID() + fileSuffix;
    ThumbnailsUtil.rotate(filePath, rotate, toFile);
    return toFile;
  }

  @Override
  public String region(MultipartFile resource, Positions p, int width, int height) {
    final String originalFilename = resource.getOriginalFilename();
    if (StringUtils.isBlank(originalFilename)) {
      log.warn("Failed to get resource filename. {}", resource);
      return null;
    }

    String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
    String filePath = saveFile(resource, fileSuffix);
    String toFile =
        filePath.substring(0, filePath.lastIndexOf("/") + 1) + UUID.randomUUID() + fileSuffix;
    ThumbnailsUtil.region(filePath, p, width, height, toFile);
    return toFile;
  }

  protected String saveFile(MultipartFile file, String fileSuffix) {
    String outputDir =
        imageDir.concat(File.separator).concat(DateUtil.FormatCurrentDateTime("yyyy-MM-dd"));

    File dir = new File(outputDir);
    if (!dir.exists()) {
      dir.mkdirs();
    }
    String fileName = UUID.randomUUID() + fileSuffix;
    String toFile = outputDir.concat(File.separator).concat(fileName);
    try {
      file.transferTo(new File(outputDir, fileName));
    } catch (IOException e) {
      log.error("File uploading failed.", e);
    }
    return toFile;
  }
}
