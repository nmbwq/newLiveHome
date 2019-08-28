package shangri.example.com.shangri.model.bean.request;

import java.io.Serializable;

/**
 * 资讯详情
 * Created by chengaofu on 2017/6/29.
 */

public class NewsDetailBean implements Serializable {

    private Long id;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
