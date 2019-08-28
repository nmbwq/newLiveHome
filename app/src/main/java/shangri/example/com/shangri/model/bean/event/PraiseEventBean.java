package shangri.example.com.shangri.model.bean.event;

/**
 * 详情里点赞  返回列表更新
 * Created by chengaofu on 2017/7/4.
 */

public class PraiseEventBean {

    private Integer type; //界面名称类型 Constant中有
    private Long praiseCount; //点赞的数量
    private Integer praisePosition; //点赞的位置

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Long praiseCount) {
        this.praiseCount = praiseCount;
    }

    public Integer getPraisePosition() {
        return praisePosition;
    }

    public void setPraisePosition(Integer praisePosition) {
        this.praisePosition = praisePosition;
    }

    public PraiseEventBean() {
    }

    public PraiseEventBean(Integer type, Integer praisePosition) {
        this.type = type;
        this.praisePosition = praisePosition;
    }
}
