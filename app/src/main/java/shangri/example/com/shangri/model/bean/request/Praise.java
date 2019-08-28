package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/6.
 */

public class Praise extends BaseBeen {
    private String article_id;
    private String page;
    private String recruit_id;
    private String status;

    //    会长沟通主播接口需要参数
    private String resume_message_id;
    private String anchor_id;
    private String resume_id;


    public String getResume_message_id() {
        return resume_message_id;
    }

    public void setResume_message_id(String resume_message_id) {
        this.resume_message_id = resume_message_id;
    }

    public String getAnchor_id() {
        return anchor_id;
    }

    public void setAnchor_id(String anchor_id) {
        this.anchor_id = anchor_id;
    }

    public String getResume_id() {
        return resume_id;
    }

    public void setResume_id(String resume_id) {
        this.resume_id = resume_id;
    }

    public String getRecruit_id() {
        return recruit_id;
    }

    public void setRecruit_id(String recruit_id) {
        this.recruit_id = recruit_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }
}
