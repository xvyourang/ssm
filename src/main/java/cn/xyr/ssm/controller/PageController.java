package cn.xyr.ssm.controller;

import cn.xyr.ssm.common.model.dto.WebDTO;
import cn.xyr.ssm.common.utils.enumtype.ResCodeEnum;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xyr
 * @time 2018/10/24 11:15
 */
@Controller
public class PageController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(PageController.class);


    @RequestMapping("/")
    @ResponseBody
    public WebDTO index() {
        System.out.println("访问了主页");
        WebDTO dto = new WebDTO();
        dto.setResCode(ResCodeEnum.SUCCESS);
        dto.setData("欢迎你！！！");
        return dto;
    }

    @RequestMapping("/demo")
    @ResponseBody
    public WebDTO demo() {
        WebDTO dto = new WebDTO();
        PageList list = new PageList();
        dto.setResCode(ResCodeEnum.SUCCESS);
        dto.setList(list);
        return dto;
    }
    @RequestMapping("/erweima")
    public String erwima() {
        return "erweima";
    }
}
