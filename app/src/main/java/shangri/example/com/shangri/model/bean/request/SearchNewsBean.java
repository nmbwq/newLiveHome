package shangri.example.com.shangri.model.bean.request;

/**
 * 资讯搜索
 * Created by chengaofu on 2017/6/28.
 */

public class SearchNewsBean extends BaseBeen {
    private String search;
    private String page;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
