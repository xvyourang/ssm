package cn.xyr.ssm.service.web;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author XYR
 * @Create time: @2018/8/8} 23:03
 * @Description:
 */
public interface FileService {
    /**
     * 文件上传
     */
    String fileUpload(HttpServletRequest request, MultipartFile file, String... paths);
}
