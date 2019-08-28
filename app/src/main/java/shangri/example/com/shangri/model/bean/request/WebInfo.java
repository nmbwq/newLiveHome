package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/30.
 */

public class WebInfo {
    private String index;
    private String name;
    private String comment;

    public WebInfo() {
    }

    public WebInfo(String index, String name, String comment) {
        this.index = index;
        this.name = name;
        this.comment = comment;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
