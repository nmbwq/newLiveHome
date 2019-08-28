package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/3.
 */

public class PatrolBean extends BaseBeen{
    private String page;
    private String guild_id;
    private String type;
//    他人辅导列表需要参数
    private String register_id;

    public String getRegister_id() {
        return register_id;
    }

    public void setRegister_id(String register_id) {
        this.register_id = register_id;
    }

    public String getGuild_id() {
        return guild_id;
    }

    public void setGuild_id(String guild_id) {
        this.guild_id = guild_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
