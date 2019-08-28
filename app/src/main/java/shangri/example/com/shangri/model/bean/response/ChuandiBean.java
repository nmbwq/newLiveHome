package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/3.
 */

public class ChuandiBean implements Serializable {
    int status;
    String title;
    private List<NewTaskDataBean.TasksBean.NoJoinBean> join;
    private List<NewTaskDataBean.TasksBean.NoJoinBean> no_join;
    private List<NewTaskDataBean.TasksBean.NoJoinBean> over_list;
    private List<NewTaskDataBean.TasksBean.NoJoinBean> ing_list;

    public ChuandiBean(String title, List<NewTaskDataBean.TasksBean.NoJoinBean> join, List<NewTaskDataBean.TasksBean.NoJoinBean> no_join, List<NewTaskDataBean.TasksBean.NoJoinBean> over_list, List<NewTaskDataBean.TasksBean.NoJoinBean> ing_list) {
        this.title = title;
        this.join = join;
        this.no_join = no_join;
        this.over_list = over_list;
        this.ing_list = ing_list;
    }


    public ChuandiBean(int status, String title, List<NewTaskDataBean.TasksBean.NoJoinBean> join, List<NewTaskDataBean.TasksBean.NoJoinBean> no_join, List<NewTaskDataBean.TasksBean.NoJoinBean> over_list, List<NewTaskDataBean.TasksBean.NoJoinBean> ing_list) {
        this.status = status;
        this.title = title;
        this.join = join;
        this.no_join = no_join;
        this.over_list = over_list;
        this.ing_list = ing_list;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ChuandiBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<NewTaskDataBean.TasksBean.NoJoinBean> getJoin() {
        return join;
    }

    public void setJoin(List<NewTaskDataBean.TasksBean.NoJoinBean> join) {
        this.join = join;
    }

    public List<NewTaskDataBean.TasksBean.NoJoinBean> getNo_join() {
        return no_join;
    }

    public void setNo_join(List<NewTaskDataBean.TasksBean.NoJoinBean> no_join) {
        this.no_join = no_join;
    }

    public List<NewTaskDataBean.TasksBean.NoJoinBean> getOver_list() {
        return over_list;
    }

    public void setOver_list(List<NewTaskDataBean.TasksBean.NoJoinBean> over_list) {
        this.over_list = over_list;
    }

    public List<NewTaskDataBean.TasksBean.NoJoinBean> getIng_list() {
        return ing_list;
    }

    public void setIng_list(List<NewTaskDataBean.TasksBean.NoJoinBean> ing_list) {
        this.ing_list = ing_list;
    }
}

