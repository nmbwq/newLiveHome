package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/3/2.
 */

public class ParticipateTaskBean extends BaseBeen {
    private String task_id;
    private String self_aims;

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getSelf_aims() {
        return self_aims;
    }

    public void setSelf_aims(String self_aims) {
        this.self_aims = self_aims;
    }
}
