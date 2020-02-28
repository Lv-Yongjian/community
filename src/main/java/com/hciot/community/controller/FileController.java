package com.hciot.community.controller;

import com.hciot.community.dto.FileDTO;
import com.hciot.community.exception.CustomizeErrorCode;
import com.hciot.community.exception.CustomizeException;
import com.hciot.community.utils.ImagesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 上传图片控制层
 * @projectName: community
 * @author: Lv-YongJian
 * @createTime: 2020/2/27 18:38
 * @version: 1.0
 */
@Controller
@Slf4j
public class FileController {

    @Value("${file.path}")
    private String imagePath;

    @Value("${file.maxSize}")
    private Long imageMaxSize;

    /**
     * 上传图片
     *
     * @param request 客户端的请求
     * @return com.hciot.community.dto.FileDTO
     * @author Lv-YongJian
     * @date 2020/2/28 10:55
     */
    @ResponseBody
    @RequestMapping("/file/upload")
    public FileDTO upload(HttpServletRequest request) {
        //获取通过 editormd 富文本上传的图片
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        try {
            //判断图片大小是否大于5M
            if (file.getSize() > imageMaxSize * 1024 * 1024) {
                log.error("image too big {}", file.getName());
                throw new CustomizeException(CustomizeErrorCode.IMAGE_TOO_BIG);
            }
            //上传图片到本地并返回图片名称
            String imageName = ImagesUtil.uploadImage(file, imagePath);
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(1);
            fileDTO.setUrl("/images/" + imageName); //图片的相对路径
            return fileDTO;
        } catch (Exception e) {
            //上传失败时返回的 FileDTO 对象
            log.error("images upload error {}", e);
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(0);
            fileDTO.setMessage("上传失败！");
            return fileDTO;
        }
    }

}
