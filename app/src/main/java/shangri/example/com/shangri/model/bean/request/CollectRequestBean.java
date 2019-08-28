package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/15.
 */

public class CollectRequestBean extends BaseBeen {
    private String page;
    private String op;

    public CollectRequestBean() {
    }

    public String getPage() {

        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
}
