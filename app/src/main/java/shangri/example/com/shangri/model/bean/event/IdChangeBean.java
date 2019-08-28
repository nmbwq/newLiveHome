package shangri.example.com.shangri.model.bean.event;

/**
 * 浏览数量
 * Created by zuyuli on 2017/7/4.
 */

public class IdChangeBean {

    public  String Liveimage;

    public IdChangeBean(String liveimage) {
        Liveimage = liveimage;
    }

    public String getLiveimage() {
        return Liveimage;
    }

    public void setLiveimage(String liveimage) {
        Liveimage = liveimage;
    }

    public IdChangeBean() {
    }
}
