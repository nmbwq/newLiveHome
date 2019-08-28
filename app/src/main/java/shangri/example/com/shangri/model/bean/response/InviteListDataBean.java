package shangri.example.com.shangri.model.bean.response;

import java.util.List;

import shangri.example.com.shangri.model.bean.request.BaseBeen;

/**
 * Created by Administrator on 2017/12/30.
 */

public class InviteListDataBean extends BaseBeen {


    /**
     * current_page : 1
     * total_page : 1
     * bills : [{"id":3,"register_id":357,"title":"邀请朋友","nickname":"13372552140","telephone":"13372552140","avatar_url":"","operate_num":2,"status":1,"create_time":1541574411}]
     */

    private int current_page;
    private int total_page;
    //    邀请好友最高可获得(元)
    private String height_rewards;
    //    	好友可获得(元)
    private String friend_rewards;

    //    分享的h5地址URL
    private String share_url;
    //     	分享底图
    private String invitation_img_url;
    //     	二维码地址
    private String qrcode_url;
    //    邀请码（没有时为空）
    private String code;
//    邀请主播数
    private String  invite_number;
    //     	已邀请获取波币数
    private String  have_to_earn;

    public String getInvite_number() {
        return invite_number;
    }

    public void setInvite_number(String invite_number) {
        this.invite_number = invite_number;
    }

    public String getHave_to_earn() {
        return have_to_earn;
    }

    public void setHave_to_earn(String have_to_earn) {
        this.have_to_earn = have_to_earn;
    }

    private List<BillsBean> bills;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHeight_rewards() {
        return height_rewards;
    }

    public void setHeight_rewards(String height_rewards) {
        this.height_rewards = height_rewards;
    }

    public String getFriend_rewards() {
        return friend_rewards;
    }

    public void setFriend_rewards(String friend_rewards) {
        this.friend_rewards = friend_rewards;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getInvitation_img_url() {
        return invitation_img_url;
    }

    public void setInvitation_img_url(String invitation_img_url) {
        this.invitation_img_url = invitation_img_url;
    }

    public String getQrcode_url() {
        return qrcode_url;
    }

    public void setQrcode_url(String qrcode_url) {
        this.qrcode_url = qrcode_url;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public List<BillsBean> getBills() {
        return bills;
    }

    public void setBills(List<BillsBean> bills) {
        this.bills = bills;
    }

    public static class BillsBean {
        /**
         * id : 3
         * register_id : 357
         * title : 邀请朋友
         * nickname : 13372552140
         * telephone : 13372552140
         * avatar_url :
         * operate_num : 2
         * status : 1
         * create_time : 1541574411
         */

        private int id;
        private int register_id;
        private String title;
        private String nickname;
        private String telephone;
        private String avatar_url;
        private int operate_num;
        private int status;
        private int create_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRegister_id() {
            return register_id;
        }

        public void setRegister_id(int register_id) {
            this.register_id = register_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public int getOperate_num() {
            return operate_num;
        }

        public void setOperate_num(int operate_num) {
            this.operate_num = operate_num;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }
    }

}
