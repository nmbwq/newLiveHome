package shangri.example.com.shangri.model.bean.request;

/**
 * Description:请求基本bean
 * Data：2018/11/22-11:14
 * Author: lin
 */
public class NormalRequestBean {
    String token;
    String sys;
    int page;
    String package_id;
    String package_duration;
    String pay_price;
    String pay_method;
    String bought_channel;
    String pay_status;

    public String getPackage_id() {
        return package_id;
    }

    public void setPackage_id(String package_id) {
        this.package_id = package_id;
    }

    public String getPackage_duration() {
        return package_duration;
    }

    public void setPackage_duration(String package_duration) {
        this.package_duration = package_duration;
    }

    public String getPay_price() {
        return pay_price;
    }

    public void setPay_price(String pay_price) {
        this.pay_price = pay_price;
    }

    public String getPay_method() {
        return pay_method;
    }

    public void setPay_method(String pay_method) {
        this.pay_method = pay_method;
    }

    public String getBought_channel() {
        return bought_channel;
    }

    public void setBought_channel(String bought_channel) {
        this.bought_channel = bought_channel;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }
}
