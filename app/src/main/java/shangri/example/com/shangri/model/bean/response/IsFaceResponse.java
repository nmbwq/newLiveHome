package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/1.
 */

public class IsFaceResponse implements Serializable {


    /**
     * is_face : 1
     * is_company : 1
     */

    private int is_face;
    private int is_company;
    private String  legal_name;
    private String  company_name;
//    会长剩余发布职位数接口剩余次数
    int  is_send;

    public int getIs_send() {
        return is_send;
    }

    public void setIs_send(int is_send) {
        this.is_send = is_send;
    }

    public String getLegal_name() {
        return legal_name;
    }

    public void setLegal_name(String legal_name) {
        this.legal_name = legal_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    private String url;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIs_face() {
        return is_face;
    }

    public void setIs_face(int is_face) {
        this.is_face = is_face;
    }

    public int getIs_company() {
        return is_company;
    }

    public void setIs_company(int is_company) {
        this.is_company = is_company;
    }
}

