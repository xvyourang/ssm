package cn.xyr.ssm.model.condition;


import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/**
 * 分页条件查询基类
 * 与物理分页插件配合使用
 */
public class BaseCondition {

    /**
     * The Page no. 若无赋值默认为第一页
     */
    private Integer pageNo = 1;

    /**
     * The Page limit. 若无赋值默认为每页10条数据
     */
    private Integer pageLimit = 10;
    /**
     * 排序方式
     * 升序ASC，降序DESC
     */
    private String sort = null;
    /**
     * 排序字段
     * 需和数据库字段相同
     */
    private String sortParam = null;

    /**
     * Gets page bounds.
     * 获取分页器
     *
     * @return the page bounds
     */
    public PageBounds getPageBounds() {
        PageBounds pageBounds = new PageBounds(pageNo, pageLimit, true);
        if (sortParam != null && sort != null) {
            pageBounds.setOrders(Order.formString(sortParam + "." + sort));
        }
        return pageBounds;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public Integer getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(Integer pageLimit) {
        this.pageLimit = pageLimit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortParam() {
        return sortParam;
    }

    public void setSortParam(String sortParam) {
        this.sortParam = sortParam;
    }
}
