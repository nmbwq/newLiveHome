package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/4.
 */

public class companyMyBean implements Serializable{
    private int has_company;
    //获取的对比度
    private int score;
    //实名认证是否成功
    private int ok;
    //实名认证时候成功
    private int face_id;
    //url免责协议
    public String url;
    //签署流程返回地址
    public String type;




    //    法务照片缓存 接口参数
    public String file_url="";

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getFace_id() {
        return face_id;
    }

    public void setFace_id(int face_id) {
        this.face_id = face_id;
    }

    public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHas_company() {
        return has_company;
    }

    public void setHas_company(int has_company) {
        this.has_company = has_company;
    }
}
