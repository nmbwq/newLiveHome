package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/30.
 */

public class AddInfo extends BaseBeen {
    private String role;
    private String nickname;
    private String sex;
    private String platfrom_name;
    private String guild_name;
    private String tag_ids;
    private String tags_content;
    private String cover_url;
    private String anchor_content;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getTag_ids() {
        return tag_ids;
    }

    public void setTag_ids(String tag_ids) {
        this.tag_ids = tag_ids;
    }

    public String getTags_content() {
        return tags_content;
    }

    public void setTags_content(String tags_content) {
        this.tags_content = tags_content;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getAnchor_content() {
        return anchor_content;
    }

    public void setAnchor_content(String anchor_content) {
        this.anchor_content = anchor_content;
    }
}
