package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/7.
 */

public class MessageBean extends BaseBeen {
    private String type;
    private String page;

    public MessageBean() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
