package shangri.example.com.shangri.model.bean.event;

/**
 * 浏览数量
 * Created by zuyuli on 2017/7/4.
 */

public class SelectManBean {
    public String guildid;
    public String guild_name;
    public String mSearchContent;

    public SelectManBean(String guildid, String guild_name, String mSearchContent) {
        this.guildid = guildid;
        this.guild_name = guild_name;
        this.mSearchContent = mSearchContent;
    }

    public SelectManBean() {
    }

    public String getGuildid() {
        return guildid;
    }

    public void setGuildid(String guildid) {
        this.guildid = guildid;
    }

    public String getGuild_name() {
        return guild_name;
    }

    public void setGuild_name(String guild_name) {
        this.guild_name = guild_name;
    }

    public String getmSearchContent() {
        return mSearchContent;
    }

    public void setmSearchContent(String mSearchContent) {
        this.mSearchContent = mSearchContent;
    }
}
