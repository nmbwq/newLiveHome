package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/5.
 */

public class AnchorsBean extends BaseBeen {
    private String guild_id;
    private String page;
    private String search;
    //弹出福利推送提示需要参数
//=1时设置已弹出 其他为获取是否弹出
    private String alert;

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getGuild_id() {
        return guild_id;
    }

    public void setGuild_id(String guild_id) {
        this.guild_id = guild_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
