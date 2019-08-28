package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/2.
 */

public class BannerDetalBean extends BaseBeen{
    private String catagory_id;
    private String page;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
    public String getCatagory_id() {
        return catagory_id;
    }

    public void setCatagory_id(String catagory_id) {
        this.catagory_id = catagory_id;
    }
}
