package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/9.
 */

public class BindingGuildBean extends BaseBeen {
    private String platfrom_name;
    private String guild_name;
    private String platfrom_account;
    private String platfrom_password;
    private String platfrom_link;
    private String ratio;
    //升级公会接口需要的参数
    private String guild_id;

    public String getGuild_id() {
        return guild_id;
    }

    public void setGuild_id(String guild_id) {
        this.guild_id = guild_id;
    }

    public BindingGuildBean() {
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getPlatfrom_name() {
        return platfrom_name;
    }

    public void setPlatfrom_name(String platfrom_name) {
        this.platfrom_name = platfrom_name;
    }

    public String getGuild_name() {
        return guild_name;
    }

    public void setGuild_name(String guild_name) {
        this.guild_name = guild_name;
    }

    public String getPlatfrom_account() {
        return platfrom_account;
    }

    public void setPlatfrom_account(String platfrom_account) {
        this.platfrom_account = platfrom_account;
    }

    public String getPlatfrom_password() {
        return platfrom_password;
    }

    public void setPlatfrom_password(String platfrom_password) {
        this.platfrom_password = platfrom_password;
    }

    public String getPlatfrom_link() {
        return platfrom_link;
    }

    public void setPlatfrom_link(String platfrom_link) {
        this.platfrom_link = platfrom_link;
    }
}
