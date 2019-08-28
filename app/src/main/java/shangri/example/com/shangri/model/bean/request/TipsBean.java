package shangri.example.com.shangri.model.bean.request;

import java.io.Serializable;

/**
 * 消息公告
 * Created by chengaofu on 2017/6/29.
 */

public class TipsBean implements Serializable {

    private String type;
    private Integer currPage;
    private Integer pageSize;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
