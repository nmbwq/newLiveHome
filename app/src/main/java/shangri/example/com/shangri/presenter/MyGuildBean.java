package shangri.example.com.shangri.presenter;

import shangri.example.com.shangri.model.bean.request.BaseBeen;

/**
 * Created by Administrator on 2018/1/26.
 */

public class MyGuildBean extends BaseBeen {

    private String purview;

    public String getPurview() {
        return purview;
    }

    public void setPurview(String purview) {
        this.purview = purview;
    }
}
