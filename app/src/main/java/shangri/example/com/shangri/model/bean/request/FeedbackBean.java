package shangri.example.com.shangri.model.bean.request;

/**
 * 用户反馈
 * Created by chengaofu on 2017/6/30.
 */

public class FeedbackBean extends BaseBeen {

    private String content;
    private String contact;
    private String images;

    //    主播邀请详细列表需要参数
    private String no_resume; /*默认0 0-全部 1-无简历*/
    private String page;

    public String getNo_resume() {
        return no_resume;
    }

    public void setNo_resume(String no_resume) {
        this.no_resume = no_resume;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
