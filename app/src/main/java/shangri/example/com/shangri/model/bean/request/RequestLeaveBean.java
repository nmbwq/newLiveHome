package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/7.
 */

public class RequestLeaveBean extends BaseBeen {
    private String guild_id;
    private String anchor_string;
    private String admin_reg_id;
    //管理员对主播操作

    public String getAdmin_reg_id() {
        return admin_reg_id;
    }

    public void setAdmin_reg_id(String admin_reg_id) {
        this.admin_reg_id = admin_reg_id;
    }

    public RequestLeaveBean() {
    }

    public String getGuild_id() {
        return guild_id;
    }

    public void setGuild_id(String guild_id) {
        this.guild_id = guild_id;
    }

    public String getAnchor_string() {
        return anchor_string;
    }

    public void setAnchor_string(String anchor_string) {
        this.anchor_string = anchor_string;
    }
}
