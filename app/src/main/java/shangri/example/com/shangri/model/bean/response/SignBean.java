package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/12/30.
 */

public class SignBean implements Serializable {


    /**
     * invitationType : [{"type":16,"num":10},{"type":17,"num":10},{"type":18,"num":20},{"type":19,"num":10},{"type":20,"num":20},{"type":21,"num":10},{"type":22,"num":50}]
     * sitcom_sign_number : 0
     * add_sign_day : 0
     * continuous_sign_day : 0
     * is_sign : 0
     * is_today_sign : 0
     */

    private int sitcom_sign_number;
    private int add_sign_day;
    private int continuous_sign_day;
    private int is_sign;
    private int is_today_sign;
    private List<InvitationTypeBean> invitationType;

//    本次签到赠送的波币数
    private String current_num;

    public String getCurrent_num() {
        return current_num;
    }

    public void setCurrent_num(String current_num) {
        this.current_num = current_num;
    }

    public int getSitcom_sign_number() {
        return sitcom_sign_number;
    }

    public void setSitcom_sign_number(int sitcom_sign_number) {
        this.sitcom_sign_number = sitcom_sign_number;
    }

    public int getAdd_sign_day() {
        return add_sign_day;
    }

    public void setAdd_sign_day(int add_sign_day) {
        this.add_sign_day = add_sign_day;
    }

    public int getContinuous_sign_day() {
        return continuous_sign_day;
    }

    public void setContinuous_sign_day(int continuous_sign_day) {
        this.continuous_sign_day = continuous_sign_day;
    }

    public int getIs_sign() {
        return is_sign;
    }

    public void setIs_sign(int is_sign) {
        this.is_sign = is_sign;
    }

    public int getIs_today_sign() {
        return is_today_sign;
    }

    public void setIs_today_sign(int is_today_sign) {
        this.is_today_sign = is_today_sign;
    }

    public List<InvitationTypeBean> getInvitationType() {
        return invitationType;
    }

    public void setInvitationType(List<InvitationTypeBean> invitationType) {
        this.invitationType = invitationType;
    }

    public static class InvitationTypeBean implements Serializable {
        /**
         * type : 16
         * num : 10
         */

        private int type;
        private int num;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
