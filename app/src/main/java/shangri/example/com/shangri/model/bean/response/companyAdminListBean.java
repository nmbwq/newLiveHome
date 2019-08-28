package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class companyAdminListBean implements Serializable {


    private List<AdminBean> admin;

    public List<AdminBean> getAdmin() {
        return admin;
    }

    public void setAdmin(List<AdminBean> admin) {
        this.admin = admin;
    }

    public static class AdminBean {
        /**
         * admin_reg_id : 353
         * nickname : 18668121550
         * check_status : 1
         * avatar_url :
         */

        private String admin_reg_id;
        private String nickname;
        private String check_status;
        private String avatar_url;

        public String getAdmin_reg_id() {
            return admin_reg_id;
        }

        public void setAdmin_reg_id(String admin_reg_id) {
            this.admin_reg_id = admin_reg_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getCheck_status() {
            return check_status;
        }

        public void setCheck_status(String check_status) {
            this.check_status = check_status;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }
    }
}



