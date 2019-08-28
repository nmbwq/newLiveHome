package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/9.
 */

public class JoinGuildBean extends BaseBeen {
    private String guild_id;
    private String uid;
    private String nickname;


    //快速绑定公会需要的信息
    private String plat_id;
    private String plat_name;
    private String guild_name;
    private String q_guild_id;
    private String anchor_id;
    private String table_flag;
    private  String  anchor_type;
    //主播绑定快速公会

    private String platfrom_id;

    public String getAnchor_type() {
        return anchor_type;
    }

    public void setAnchor_type(String anchor_type) {
        this.anchor_type = anchor_type;
    }

    public String getPlatfrom_id() {
        return platfrom_id;
    }

    public void setPlatfrom_id(String platfrom_id) {
        this.platfrom_id = platfrom_id;
    }

    public String getTable_flag() {
        return table_flag;
    }

    public void setTable_flag(String table_flag) {
        this.table_flag = table_flag;
    }

    public String getPlat_id() {
        return plat_id;
    }

    public void setPlat_id(String plat_id) {
        this.plat_id = plat_id;
    }

    public String getPlat_name() {
        return plat_name;
    }

    public void setPlat_name(String plat_name) {
        this.plat_name = plat_name;
    }

    public String getGuild_name() {
        return guild_name;
    }

    public void setGuild_name(String guild_name) {
        this.guild_name = guild_name;
    }

    public String getQ_guild_id() {
        return q_guild_id;
    }

    public void setQ_guild_id(String q_guild_id) {
        this.q_guild_id = q_guild_id;
    }

    public String getAnchor_id() {
        return anchor_id;
    }

    public void setAnchor_id(String anchor_id) {
        this.anchor_id = anchor_id;
    }

    public String getGuild_id() {
        return guild_id;
    }

    public void setGuild_id(String guild_id) {
        this.guild_id = guild_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
