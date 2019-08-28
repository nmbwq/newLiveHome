package shangri.example.com.shangri.model.bean.response;

public class everydayMissionBean {


    /**
     * sign_height : 50
     * deliver_resume : 10
     * is_sign : 0
     * is_send_resume : 0
     */

    private int sign_height;
    private int deliver_resume;
    private int is_sign;
    private int is_send_resume;

    //    每日登录活动状态 0结束 1正常
    private int is_login_welfare;
    //    今日是否已登录领取 0未领取 1已领取
    private int is_today_gives;
    //    每日登录领取值
    private int welfare_gives;

    //    可用波币
    private int use_bb;
    //    可用波币
    private int bb_rate;

    //    提现条件1 200波币）
    private int need_base;
    //    提现条件2 需要注册数3
    private int need_zcs;

    //    提现条件3 需要查看数5
    private int need_cks;

    public int getNeed_base() {
        return need_base;
    }

    public void setNeed_base(int need_base) {
        this.need_base = need_base;
    }

    public int getNeed_zcs() {
        return need_zcs;
    }

    public void setNeed_zcs(int need_zcs) {
        this.need_zcs = need_zcs;
    }

    public int getNeed_cks() {
        return need_cks;
    }

    public void setNeed_cks(int need_cks) {
        this.need_cks = need_cks;
    }

    //    banner
    private String img_url;


    public int getUse_bb() {
        return use_bb;
    }

    public void setUse_bb(int use_bb) {
        this.use_bb = use_bb;
    }

    public int getBb_rate() {
        return bb_rate;
    }

    public void setBb_rate(int bb_rate) {
        this.bb_rate = bb_rate;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getIs_login_welfare() {
        return is_login_welfare;
    }

    public void setIs_login_welfare(int is_login_welfare) {
        this.is_login_welfare = is_login_welfare;
    }

    public int getIs_today_gives() {
        return is_today_gives;
    }

    public void setIs_today_gives(int is_today_gives) {
        this.is_today_gives = is_today_gives;
    }

    public int getWelfare_gives() {
        return welfare_gives;
    }

    public void setWelfare_gives(int welfare_gives) {
        this.welfare_gives = welfare_gives;
    }

    public int getSign_height() {
        return sign_height;
    }

    public void setSign_height(int sign_height) {
        this.sign_height = sign_height;
    }

    public int getDeliver_resume() {
        return deliver_resume;
    }

    public void setDeliver_resume(int deliver_resume) {
        this.deliver_resume = deliver_resume;
    }

    public int getIs_sign() {
        return is_sign;
    }

    public void setIs_sign(int is_sign) {
        this.is_sign = is_sign;
    }

    public int getIs_send_resume() {
        return is_send_resume;
    }

    public void setIs_send_resume(int is_send_resume) {
        this.is_send_resume = is_send_resume;
    }

}
