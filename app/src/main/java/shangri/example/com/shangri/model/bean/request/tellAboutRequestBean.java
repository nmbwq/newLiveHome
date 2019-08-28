package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/3/28.
 */

public class tellAboutRequestBean extends BaseBeen {
    public String chat_id;
    public String page;


    //交换电话微信接口需要参数
    public String type;
    public String telephone;
    public String wx;

    //会长约聊主播接口需要参数

    public String resume_id;



    public String getResume_id() {
        return resume_id;
    }

    public void setResume_id(String resume_id) {
        this.resume_id = resume_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
