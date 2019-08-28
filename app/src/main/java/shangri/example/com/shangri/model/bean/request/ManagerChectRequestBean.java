package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/3/28.
 */

public class ManagerChectRequestBean extends BaseBeen {
    public String admin_reg_id;
    public String guild_id;
    public String status;
    public String mask;
    //    查看联系我的公司 需要参数
    public String page;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getAdmin_reg_id() {
        return admin_reg_id;
    }

    public void setAdmin_reg_id(String admin_reg_id) {
        this.admin_reg_id = admin_reg_id;
    }

    public String getGuild_id() {
        return guild_id;
    }

    public void setGuild_id(String guild_id) {
        this.guild_id = guild_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }
}
