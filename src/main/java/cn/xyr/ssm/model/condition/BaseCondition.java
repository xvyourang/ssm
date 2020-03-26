package cn.xyr.ssm.model.condition;


import lombok.Data;

/**
 * 分页条件查询基类
 * 与物理分页插件配合使用
 *
 * @author xyr
 * @since 2020/3/26
 */
@Data
public class BaseCondition {

    /**
     * 当前页面，默认第一页
     */
    private Integer pageNum = 1;

    /**
     * 若无赋值默认为每页10条数据
     */
    private Integer pageSize = 10;
    /**
     * 排序
     * 例如 id desc
     */
    private String orderBy = null;
}
