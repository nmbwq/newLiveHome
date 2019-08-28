package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/2/1.
 */

public class AddPlatfrom extends BaseBeen {

    private String platfrom_id;
    private String platfrom_name;
    private String anchor_content;
    private String guild_name;
    private String cover_url;

    public String getPlatfrom_id() {
        return platfrom_id;
    }

    public void setPlatfrom_id(String platfrom_id) {
        this.platfrom_id = platfrom_id;
    }

    public String getPlatfrom_name() {
        return platfrom_name;
    }

    public void setPlatfrom_name(String platfrom_name) {
        this.platfrom_name = platfrom_name;
    }

    public String getAnchor_content() {
        return anchor_content;
    }

    public void setAnchor_content(String anchor_content) {
        this.anchor_content = anchor_content;
    }

    public String getGuild_name() {
        return guild_name;
    }

    public void setGuild_name(String guild_name) {
        this.guild_name = guild_name;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }
}
