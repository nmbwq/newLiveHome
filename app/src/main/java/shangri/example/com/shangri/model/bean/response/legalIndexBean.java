package shangri.example.com.shangri.model.bean.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import shangri.example.com.shangri.model.bean.request.BaseBeen;

/**
 * 登陆返回的用户信息
 * Created by chengaofu on 2017/6/21.
 */

public class legalIndexBean implements Serializable {

    int complete_count = 0;
    int wait_count = 0;

    int is_face = 0;
    int is_company = 0;

    //上传图片返回的地址
    String file_url;

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public int getIs_company() {
        return is_company;
    }

    public void setIs_company(int is_company) {
        this.is_company = is_company;
    }

    public int getIs_face() {
        return is_face;
    }

    public void setIs_face(int is_face) {
        this.is_face = is_face;
    }

    public legalIndexBean() {
    }

    public int getComplete_count() {
        return complete_count;
    }

    public void setComplete_count(int complete_count) {
        this.complete_count = complete_count;
    }

    public int getWait_count() {
        return wait_count;
    }

    public void setWait_count(int wait_count) {
        this.wait_count = wait_count;
    }
}



