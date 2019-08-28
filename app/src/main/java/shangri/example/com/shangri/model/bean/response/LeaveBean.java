package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class LeaveBean implements Serializable {

    private int id;
    private String uid;
    private String name;
    private String avatar_url;

    boolean flag;


    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public LeaveBean() {
    }


    public LeaveBean(String name, boolean flag) {
        this.name = name;
        this.flag = flag;
    }

    public LeaveBean(int id, String uid, String name, String avatar_url, boolean flag) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.avatar_url = avatar_url;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}


