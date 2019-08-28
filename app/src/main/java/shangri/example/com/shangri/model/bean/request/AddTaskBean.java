package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/3/2.
 */

public class AddTaskBean extends BaseBeen {
    private String guild_id;
    private String theme;
    private String content;
    private String start_time;
    private String end_time;
    private String expect_aims;

    public String getGuild_id() {
        return guild_id;
    }

    public void setGuild_id(String guild_id) {
        this.guild_id = guild_id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getExpect_aims() {
        return expect_aims;
    }

    public void setExpect_aims(String expect_aims) {
        this.expect_aims = expect_aims;
    }
}
