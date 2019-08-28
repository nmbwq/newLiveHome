package shangri.example.com.shangri.model.bean.request;

/**
 * 请求资讯列表
 * Created by chengaofu on 2017/6/28.
 */

public class NewsListBean {

    private Long typeId;
    private Integer currPage;
    private Integer pageSize;
    private Long userId;
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
    public Long getTypeId() {
        return typeId;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }
    public Integer getCurrPage() {
        return currPage;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getPageSize() {
        return pageSize;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getUserId() {
        return userId;
    }

}
