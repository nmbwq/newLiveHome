package shangri.example.com.shangri.model.bean.request;

/**
 * 用户反馈
 * Created by chengaofu on 2017/6/30.
 */

public class MoneyGainBean extends BaseBeen {
//    类型 1邀请好友注册 2好友投递简历 3 简历被查看
    private int  type;
//    对应类型的阶段
    private int stage;
    //  领取的波币数
    private int bb_num;


    public MoneyGainBean() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getBb_num() {
        return bb_num;
    }

    public void setBb_num(int bb_num) {
        this.bb_num = bb_num;
    }
}
