package shangri.example.com.shangri.model.bean.response;

public class WeChatInfoBean {


    /**
     * wxinfo_id : 1
     * wx_nickname : abc
     * token : 3435345dsfgaqg
     */

    private String wxinfo_id;
    private String wx_nickname;
    private String token;

    public String getWxinfo_id() {
        return wxinfo_id;
    }

    public void setWxinfo_id(String wxinfo_id) {
        this.wxinfo_id = wxinfo_id;
    }

    public String getWx_nickname() {
        return wx_nickname;
    }

    public void setWx_nickname(String wx_nickname) {
        this.wx_nickname = wx_nickname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
