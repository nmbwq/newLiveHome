package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/3/6.
 */

public class ParticipationBean extends BaseBeen {

    private String task_id;
    private String page;

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
