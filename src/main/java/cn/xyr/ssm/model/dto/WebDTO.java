package cn.xyr.ssm.model.dto;

import cn.xyr.ssm.common.utils.enumtype.ResCodeEnum;
import com.github.pagehelper.Page;

import java.util.HashMap;

/**
 * 前端数据返回model
 *
 * @author XYR
 * @since 2020/3/26
 */
public class WebDTO<T> extends HashMap<String, Object> {
    /**
     * 返回码字段
     */
    public static final String RES_CODE = "resCode";
    /**
     * 返回信息字段
     */
    public static final String RES_MSG = "resMsg";

    /**
     * 返回码
     */
    private String resCode;
    /**
     * 返回码描述
     */
    private String resMsg;
    /**
     * 具体数据
     */
    private T data;
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 总页数
     */
    private Integer totalPages;
    /**
     * 包含数据和分页信息的list对象
     */
    private Page<T> list;

    public WebDTO() {
    }

    public WebDTO(T t) {
        this.data = t;
        put("data", t);
        setResCode(ResCodeEnum.SUCCESS);
    }

    public void setResCode(ResCodeEnum resCode) {
        setResCode(resCode.getResCode());
        setResMsg(resCode.getResMessage());
    }

    public String getResCode() {
        return this.resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
        put(RES_CODE, resCode);
    }

    public String getResMsg() {
        return this.resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
        put(RES_MSG, resMsg);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
        put("data", data);
    }

    public Long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
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


    public Page<T> getList() {
        return list;
    }

    public void setList(Page<T> list) {
        this.list = list;
        setTotalPages(list.getPages());
        setTotal(list.getTotal());
    }

    @Override
    public String toString() {
        return "WebDTO{" +
                "resCode='" + resCode + '\'' +
                ", resMsg='" + resMsg + '\'' +
                ", data=" + data +
                ", total=" + total +
                ", totalPages=" + totalPages +
                ", list=" + list +
                '}';
    }
}
