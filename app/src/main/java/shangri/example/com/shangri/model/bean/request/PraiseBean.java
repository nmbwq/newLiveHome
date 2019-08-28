package shangri.example.com.shangri.model.bean.request;

import java.io.Serializable;

/**
 * 点赞
 * Created by chengaofu on 2017/6/28.
 */

public class PraiseBean implements Serializable {

    private Long infoId;
    private Long userId;
    private Byte praiseOrNot;
    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }
    public Long getInfoId() {
        return infoId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getUserId() {
        return userId;
    }

    public void setPraiseOrNot(Byte praiseOrNot) {
        this.praiseOrNot = praiseOrNot;
    }
    public Byte getPraiseOrNot() {
        return praiseOrNot;
    }
}
