package shangri.example.com.shangri.model.bean.request;

/**
 * 微信登录，返回的code
 */
public class WeChatBean {
    public String openid = "";
    public String nickname = "";
    public String sex = "";
    public String city = "";
    public String province = "";
    public String country = "";
    public String headimgurl = "";
    public String unionid = "";
    public String privilege = "";

    @Override
    public String toString() {
        return "WeChatBean{" +
                "openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", unionid='" + unionid + '\'' +
                ", privilege='" + privilege + '\'' +
                '}';
    }
}
