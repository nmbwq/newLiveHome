package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/15.
 */

public class InterBBean extends BaseBeen {
    private String page;
    private String order;
    private String search;



    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
