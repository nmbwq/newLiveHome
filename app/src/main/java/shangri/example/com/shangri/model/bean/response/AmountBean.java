package shangri.example.com.shangri.model.bean.response;

public class AmountBean {


    /**
     * balance : 82
     * has_resume : 1
     * has_receive : 1
     * code : 388006
     * invitation_img_url :
     * has_apply : 1
     */

    private String balance;
    private int has_resume;
    private int has_receive;
    private String code;
    private String invitation_img_url;
    private int has_apply;

    private String share_url;

    private String qrcode_url;


    private int bb_balance;
    private int bb_rate;
    private int need_base;
    private int need_zcs;
    private int need_cks;
    //    注册好礼
    private int register_nice_gift;
    //    邀请注册奖励数
    private int invite_register;

    //    邀请人上传简历奖励数
    private int invite_resume;
    //    简历被查看奖励数
    private int resume_viewed;
    //    等级名称
    private String level_name;
    //    等级id
    private int level_id;
    //    总波币数
    String sum_bb;
    //    主播累计提现金额
    String grand_total;

    //    是否关注微信 1是 0否
    private int is_focus_wx;
    //关注的微信号
    private String wx;
    //关注的公众号
    private String gzh;
    // 	关注赠送的波币数
    private int focus_gift;


    public int getIs_focus_wx() {
        return is_focus_wx;
    }

    public void setIs_focus_wx(int is_focus_wx) {
        this.is_focus_wx = is_focus_wx;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public String getGzh() {
        return gzh;
    }

    public void setGzh(String gzh) {
        this.gzh = gzh;
    }

    public int getFocus_gift() {
        return focus_gift;
    }

    public void setFocus_gift(int focus_gift) {
        this.focus_gift = focus_gift;
    }

    public String getSum_bb() {
        return sum_bb;
    }

    public void setSum_bb(String sum_bb) {
        this.sum_bb = sum_bb;
    }

    public String getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(String grand_total) {
        this.grand_total = grand_total;
    }

    public int getRegister_nice_gift() {
        return register_nice_gift;
    }

    public void setRegister_nice_gift(int register_nice_gift) {
        this.register_nice_gift = register_nice_gift;
    }

    public int getInvite_register() {
        return invite_register;
    }

    public void setInvite_register(int invite_register) {
        this.invite_register = invite_register;
    }

    public int getInvite_resume() {
        return invite_resume;
    }

    public void setInvite_resume(int invite_resume) {
        this.invite_resume = invite_resume;
    }

    public int getResume_viewed() {
        return resume_viewed;
    }

    public void setResume_viewed(int resume_viewed) {
        this.resume_viewed = resume_viewed;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public int getLevel_id() {
        return level_id;
    }

    public void setLevel_id(int level_id) {
        this.level_id = level_id;
    }

    public int getBb_balance() {
        return bb_balance;
    }

    public void setBb_balance(int bb_balance) {
        this.bb_balance = bb_balance;
    }

    public int getBb_rate() {
        return bb_rate;
    }

    public void setBb_rate(int bb_rate) {
        this.bb_rate = bb_rate;
    }

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

    public String getQrcode_url() {
        return qrcode_url;
    }

    public void setQrcode_url(String qrcode_url) {
        this.qrcode_url = qrcode_url;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public int getHas_resume() {
        return has_resume;
    }

    public void setHas_resume(int has_resume) {
        this.has_resume = has_resume;
    }

    public int getHas_receive() {
        return has_receive;
    }

    public void setHas_receive(int has_receive) {
        this.has_receive = has_receive;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInvitation_img_url() {
        return invitation_img_url;
    }

    public void setInvitation_img_url(String invitation_img_url) {
        this.invitation_img_url = invitation_img_url;
    }

    public int getHas_apply() {
        return has_apply;
    }

    public void setHas_apply(int has_apply) {
        this.has_apply = has_apply;
    }

}
