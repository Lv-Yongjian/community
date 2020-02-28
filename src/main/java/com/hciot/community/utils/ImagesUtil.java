package com.hciot.community.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @description: 图片工具类
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
@Slf4j
public class ImagesUtil {

    /**
     * 获取文件扩展名
     *
     * @param filename 文件名
     * @return java.lang.String
     * @author Lv-YongJian
     * @date 2020/2/28 16:34
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * Java文件操作 获取不带扩展名的文件名
     *
     * @param filename 文件名
     * @return java.lang.String
     * @author Lv-YongJian
     * @date 2020/2/28 16:35
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    /**
     * 将图片名解析成图片的上传路径
     *
     * @param file     文件
     * @param filePath 文件名
     * @return java.lang.String
     * @author Lv-YongJian
     * @date 2020/2/28 16:35
     */
    public static String uploadImage(MultipartFile file, String filePath) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String suffix = getExtensionName(file.getOriginalFilename());
        String name = getFileNameNoEx(file.getOriginalFilename());
        try {
            String fileName = name + "-" + uuid + "." + suffix;
            String path = filePath + fileName;
            File image = new File(path);
            // 检测是否存在目录
            if (!image.getParentFile().exists()) {
                image.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(image);// 文件写入
            return fileName;
        } catch (Exception e) {
            log.error("image upload fail {}", e);
        }
        return null;
    }

}
