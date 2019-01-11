package cn.xyr.ssm.common.model.dto;

import cn.xyr.ssm.common.utils.enumtype.ResCodeEnum;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import java.util.HashMap;

/**
 * 前端数据返回model
 *
 * @author A
 */
public class WebDTO<T> extends HashMap<String, Object> {
    //返回码
    private String resCode;
    //返回码描述
    private String resMsg;
    //具体数据
    private T data;
    //总记录数
    private Integer total;
    //总页数
    private int totalPages;

    private PageList<T> list;

    public void setResCode(ResCodeEnum resCode) {
        setResCode(resCode.getResCode());
        setResMsg(resCode.getResMessage());
    }

    public String getResCode() {
        return this.resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
        put("resCode", resCode);
    }

    public String getResMsg() {
        return this.resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
        put("resMsg", resMsg);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
        put("data", data);
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
        put("total", total);
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        put("totalPages", totalPages);
    }


    public PageList<T> getList() {
        return list;
    }

    public void setList(PageList<T> list) {
        this.list = list;
        setTotalPages(list.getPaginator().getTotalPages());
        setTotal(list.getPaginator().getTotalCount());
    }
}
