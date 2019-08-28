package shangri.example.com.shangri.model.bean.request;

import java.io.Serializable;

/**
 * 查看公告详情
 * Created by chengaofu on 2017/6/29.
 */

public class TipsDetailBean implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
