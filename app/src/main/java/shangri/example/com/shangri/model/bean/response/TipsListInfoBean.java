package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * 消息公告的返回对象
 * Created by chengaofu on 2017/6/29.
 */

public class TipsListInfoBean implements Serializable {

    private List<TipsPageDataBean> pageData;
    private PageInfoBean pageInfo;

    public List<TipsPageDataBean> getPageData() {
        return pageData;
    }

    public void setPageData(List<TipsPageDataBean> pageData) {
        this.pageData = pageData;
    }

    public PageInfoBean getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfoBean pageInfo) {
        this.pageInfo = pageInfo;
    }
}
