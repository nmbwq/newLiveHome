package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 * Created by chengaofu on 2017/6/28.
 */

public class PageInfoBean implements Serializable {

    private String currPage;
    private String pageNum;
    private String pageSize;
    private String recNum;

    public void setCurrPage(String currPage) {
        this.currPage = currPage;
    }

    public String getCurrPage() {
        return currPage;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setRecNum(String recNum) {
        this.recNum = recNum;
    }

    public String getRecNum() {
        return recNum;
    }

}
