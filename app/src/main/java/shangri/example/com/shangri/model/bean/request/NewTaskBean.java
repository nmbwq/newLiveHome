package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/3.
 */

public class NewTaskBean extends BaseBeen{
    private String page;
    private String guild_id;
    private String type;
//    任务状态 -1 全部 0未开始 1进行中 2已结束
    private String task_status;
    //    他人任务需要的参数
    private String register_id;


    public String getRegister_id() {
        return register_id;
    }

    public void setRegister_id(String register_id) {
        this.register_id = register_id;
    }

    public String getTask_status() {
        return task_status;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
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
