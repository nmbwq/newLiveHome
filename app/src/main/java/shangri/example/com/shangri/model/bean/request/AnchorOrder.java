package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/6.
 */

public class AnchorOrder extends BaseBeen {
    private String resume_id;
//    是否活动 1是 2否
    private String type;
//    活动id
    private String active_id;
    private String price;

    public AnchorOrder() {
    }

    public String getResume_id() {
        return resume_id;
    }

    public void setResume_id(String resume_id) {
        this.resume_id = resume_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActive_id() {
        return active_id;
    }

    public void setActive_id(String active_id) {
        this.active_id = active_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
