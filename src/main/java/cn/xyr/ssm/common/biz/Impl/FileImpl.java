package cn.xyr.ssm.common.biz.Impl;


import cn.xyr.ssm.common.biz.FileBiz;
import cn.xyr.ssm.common.model.constant.StringConst;
import cn.xyr.ssm.common.utils.StringUtil;
import cn.xyr.ssm.common.utils.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;


/**
 * @author XYR
 * @time 2018/11/21 16:55
 */
@Service
@Transactional
public class FileImpl implements FileBiz {

    private static final Logger logger = LoggerFactory.getLogger(FileImpl.class);


//    @Resource
//    private Environment env;
    @Value("upload:imagesPath")
    private String url;

    @Override
    public String fileUpload(HttpServletRequest request, MultipartFile file, String... paths) {
        String resultPath;
        String path;
        StringBuffer sb = new StringBuffer("/static/images/");
//                            new StringBuffer(env.getProperty("upload.imagesPath"));
        try {
            if (file == null || file.isEmpty()) {
                throw new BizException("上传文件为空");
            }
            String originalFilename = file.getOriginalFilename();
            String ext = StringUtils.substring(originalFilename, originalFilename.lastIndexOf("."));
            if (StringConst.imgSet.contains(ext.toLowerCase())) {
                throw new BizException("请上传图片格式文件");
            }
            try {
                path = new File(ResourceUtils.getURL("classpath:").getPath()).getAbsolutePath();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new BizException("获取不到项目运行目录");
            }
//            path = request.getSession().getServletContext().getRealPath("/");
//            path = env.getProperty("upload.imagesMappingPath");
            for (String str : paths) {
                sb.append(File.separator).append(str);
            }
            sb.append(File.separator);
            String fileName = StringUtil.getFile();
            resultPath = path + sb.toString();
            File f = new File(resultPath);
            f.mkdirs();//创建父目录
            sb.append(fileName).append(ext);
            resultPath = path + sb.toString();
            f = new File(resultPath);
            file.transferTo(f);//写入文件
            logger.info("文件上传路径:" + resultPath);
        } catch (BizException e) {
            logger.error("文件上传失败", e);
            throw e;
        } catch (Exception e) {
            logger.error("文件上传异常", e);
            throw new BizException("文件上传异常");
        }
        return sb.toString();
    }
}
