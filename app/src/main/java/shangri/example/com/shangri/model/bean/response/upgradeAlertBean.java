package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/5.
 */

public class upgradeAlertBean implements Serializable {


    /**
     * level : 3
     * is_upgrade : 0
     * rewards : 18
     */

    private int level;
    private int is_upgrade;
    private int rewards;
    //    是否弹出 0不弹出 1需要弹出
    private int is_pop;
    //    登录赠送数
    private int gives;

    public int getIs_pop() {
        return is_pop;
    }

    public void setIs_pop(int is_pop) {
        this.is_pop = is_pop;
    }

    public int getGives() {
        return gives;
    }

    public void setGives(int gives) {
        this.gives = gives;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getIs_upgrade() {
        return is_upgrade;
    }

    public void setIs_upgrade(int is_upgrade) {
        this.is_upgrade = is_upgrade;
    }

    public int getRewards() {
        return rewards;
    }

    public void setRewards(int rewards) {
        this.rewards = rewards;
    }

}
