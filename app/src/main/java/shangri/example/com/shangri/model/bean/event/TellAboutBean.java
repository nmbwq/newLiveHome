package shangri.example.com.shangri.model.bean.event;

/**
 * 约聊拨打电话
 * Created by zuyuli on 2017/7/4.
 */

public class TellAboutBean {
    //1拨打电话  2 点击简历图片
    String type;

    public TellAboutBean() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TellAboutBean(String type) {
        this.type = type;
    }
}
