package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 *  资讯列表返回
 * Created by chengaofu on 2017/6/28.
 */

public class NewsListInfoBean implements Serializable {
    private List<PageDataBean> pageData;
    private PageInfoBean pageInfo;
    public void setPageData(List<PageDataBean> pageData) {
        this.pageData = pageData;
    }
    public List<PageDataBean> getPageData() {
        return pageData;
    }

    public void setPageInfo(PageInfoBean pageInfo) {
        this.pageInfo = pageInfo;
    }
    public PageInfoBean getPageInfo() {
        return pageInfo;
    }

}
