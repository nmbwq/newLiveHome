package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/6.
 */

public class Collect extends BaseBeen {
    private String train_id;

    //资讯分享量增加的参数
    private String id;
    private String share_type;
    //    购买次数减少接口
    private String resume_id;
    //购买次数减少（波豆减少）接口
    private String bd_num;

    // 获取文章|培训 评论数量  需要参数
    int type;

    //新版求职列表全部需要的参数
    private String page;
    private String data_type;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getBd_num() {
        return bd_num;
    }

    public void setBd_num(String bd_num) {
        this.bd_num = bd_num;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getResume_id() {
        return resume_id;
    }

    public void setResume_id(String resume_id) {
        this.resume_id = resume_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShare_type() {
        return share_type;
    }

    public void setShare_type(String share_type) {
        this.share_type = share_type;
    }

    public Collect() {
    }

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }
}
