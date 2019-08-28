package shangri.example.com.shangri.model.bean.request;

/**
 * Description:
 * Data：2018/11/9-16:27
 * Author: lin
 */
public class OncilckBottomBean extends BaseBeen {
    String type;
    //来源 IOS android WxMiNi
    String from;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
