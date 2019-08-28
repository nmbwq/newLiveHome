package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/3/28.
 */

public class AddNewBean extends BaseBeen {
    public String guild_id;
    public String theme;
    public String content;
    public String start_time;
    public String end_time;
    public String expect_aims;
    public String hide_expect;
    public String expire_remind;
    public String type;
    public String assigns;
    public String task_id="";

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

    public String getHide_expect() {
        return hide_expect;
    }

    public void setHide_expect(String hide_expect) {
        this.hide_expect = hide_expect;
    }

    public String getExpire_remind() {
        return expire_remind;
    }

    public void setExpire_remind(String expire_remind) {
        this.expire_remind = expire_remind;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAssigns() {
        return assigns;
    }

    public void setAssigns(String assigns) {
        this.assigns = assigns;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }
}
