package cn.xyr.ssm.controller;

import cn.xyr.ssm.model.dto.WebDTO;
import cn.xyr.ssm.model.po.User;
import cn.xyr.ssm.common.utils.enumtype.ResCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xyr
 * @time 2018/10/24 11:15
 */
@RestController
public class PageController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(PageController.class);


    @RequestMapping("/")
    public WebDTO<String> index() {
        System.out.println("访问了主页");
        WebDTO<String> dto = new WebDTO<String>();
        dto.setResCode(ResCodeEnum.SUCCESS);
        dto.setData("欢迎你！！！");
        return dto;
    }

    @RequestMapping("/demo")
    public WebDTO demo() {
        WebDTO dto = new WebDTO();
        dto.setResCode(ResCodeEnum.SUCCESS);
        return dto;
    }

    @RequestMapping("/test01")
    public WebDTO<User> test01(User user, int count) {
        log.info("count" + count);
        return new WebDTO<>(user);
    }
}
