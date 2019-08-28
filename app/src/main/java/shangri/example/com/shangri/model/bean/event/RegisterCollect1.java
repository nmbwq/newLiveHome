package shangri.example.com.shangri.model.bean.event;

/**
 * Created by Administrator on 2018/4/17.
 */

public class RegisterCollect1 {

    private Integer type; //界面名称类型 Constant中有
    private Integer collectPosition; //收藏的位置

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCollectPosition() {
        return collectPosition;
    }

    public void setCollectPosition(Integer collectPosition) {
        this.collectPosition = collectPosition;
    }

    public RegisterCollect1(Integer type, Integer collectPosition) {
        this.type = type;
        this.collectPosition = collectPosition;
    }

    public RegisterCollect1() {
    }
}
