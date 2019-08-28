package shangri.example.com.shangri.model.bean.response;

import com.google.gson.annotations.SerializedName;

import shangri.example.com.shangri.model.bean.request.BaseBeen;

/**
 * 登陆返回的用户信息
 * Created by chengaofu on 2017/6/21.
 */

public class CountBean extends BaseBeen {


    private int admins;
    private int anchors;
    private int messages;

    public CountBean() {
    }

    public int getAdmins() {
        return admins;
    }

    public void setAdmins(int admins) {
        this.admins = admins;
    }

    public int getAnchors() {
        return anchors;
    }

    public void setAnchors(int anchors) {
        this.anchors = anchors;
    }

    public int getMessages() {
        return messages;
    }

    public void setMessages(int messages) {
        this.messages = messages;
    }
}



