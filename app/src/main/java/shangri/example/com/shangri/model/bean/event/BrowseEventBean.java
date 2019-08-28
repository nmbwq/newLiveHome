package shangri.example.com.shangri.model.bean.event;

/**
 * 浏览数量
 * Created by zuyuli on 2017/7/4.
 */

public class BrowseEventBean {
    private Long browseCount; //浏览数量
    private Integer browsePosition; //浏览的位置
    private int type;
    private String task_id;
    boolean isChexiao=false;
    //适配器的位置
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public BrowseEventBean(String task_id, boolean isChexiao, int position) {
        this.task_id = task_id;
        this.isChexiao = isChexiao;
        this.position = position;
    }

    public BrowseEventBean(String task_id, boolean isChexiao) {
        this.task_id = task_id;
        this.isChexiao = isChexiao;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public boolean isChexiao() {
        return isChexiao;
    }

    public void setChexiao(boolean chexiao) {
        isChexiao = chexiao;
    }

    public Integer getBrowsePosition() {
        return browsePosition;
    }

    public void setBrowsePosition(Integer browsePosition) {
        this.browsePosition = browsePosition;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public Long getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(Long browseCount) {
        this.browseCount = browseCount;
    }

    public BrowseEventBean() {
    }
}
