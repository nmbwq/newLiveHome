package shangri.example.com.shangri.model.bean.event;

/**
 * 浏览数量
 * Created by zuyuli on 2017/7/4.
 */

public class BossBean {
    private String recruit_id;
    //  0取消 1收藏
    int type;
    // 是否已沟通判断
    Boolean isPhone = false;
    //    是否已抢ta
    Boolean isQiangTa = false;
    //抢她成功以后刷新chat_id
    int  chat_id ;
    //    是否是刷新
    Boolean isRefush = false;


    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public Boolean getQiangTa() {
        return isQiangTa;
    }

    public void setQiangTa(Boolean qiangTa) {
        isQiangTa = qiangTa;
    }

    public Boolean getRefush() {
        return isRefush;
    }

    public void setRefush(Boolean refush) {
        isRefush = refush;
    }

    public Boolean getPhone() {
        return isPhone;
    }

    public void setPhone(Boolean phone) {
        isPhone = phone;
    }

    public BossBean(String recruit_id, Boolean isPhone) {
        this.recruit_id = recruit_id;
        this.isPhone = isPhone;
    }

    public BossBean(String recruit_id, int type) {
        this.recruit_id = recruit_id;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BossBean() {
    }

    public BossBean(String recruit_id) {
        this.recruit_id = recruit_id;
    }

    public String getRecruit_id() {
        return recruit_id;
    }

    public void setRecruit_id(String recruit_id) {
        this.recruit_id = recruit_id;
    }
}
