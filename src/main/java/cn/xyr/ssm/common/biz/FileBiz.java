package cn.xyr.ssm.common.biz;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author
 * @Create time: @2018/8/8} 23:03
 * @Description:
 */
public interface FileBiz {
    /**
     * 文件上传
     */
    String fileUpload(HttpServletRequest request, MultipartFile file, String... paths);
}
