package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/9.
 */

public class AddSeccussBean implements Serializable {


    /**
     * guild_id : 56215788965534
     */

    private long guild_id;

    public long getGuild_id() {
        return guild_id;
    }

    public void setGuild_id(long guild_id) {
        this.guild_id = guild_id;
    }
}
