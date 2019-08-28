package shangri.example.com.shangri.model.bean.event;

/**
 * 浏览数量
 * Created by zuyuli on 2017/7/4.
 */

public class ChangePoint {
    public  String guild_id;

    public String getGuild_id() {
        return guild_id;
    }

    public void setGuild_id(String guild_id) {
        this.guild_id = guild_id;
    }

    public ChangePoint(String guild_id) {
        this.guild_id = guild_id;
    }

    public ChangePoint() {
    }
}
