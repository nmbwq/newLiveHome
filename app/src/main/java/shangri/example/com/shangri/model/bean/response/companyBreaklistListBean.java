package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class companyBreaklistListBean implements Serializable {


    private List<AnchorsBean> anchors;
    private List<AdminsBean> admins;

    public List<AnchorsBean> getAnchors() {
        return anchors;
    }

    public void setAnchors(List<AnchorsBean> anchors) {
        this.anchors = anchors;
    }

    public List<AdminsBean> getAdmins() {
        return admins;
    }

    public void setAdmins(List<AdminsBean> admins) {
        this.admins = admins;
    }

    public static class AnchorsBean {
        /**
         * id : 11569
         * uid : 66697599
         * anchor_name : Max王大帅
         * avatar_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497090394139368.jpg
         */

        private int id;
        private String uid;
        private String anchor_name;
        private String avatar_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getAnchor_name() {
            return anchor_name;
        }

        public void setAnchor_name(String anchor_name) {
            this.anchor_name = anchor_name;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }
    }

    public static class AdminsBean {
        /**
         * id : 2
         * admin_reg_id : 254
         * nickname : 战神
         * avatar_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497090394139368.jpg
         */

        private int id;
        private int admin_reg_id;
        private String nickname;
        private String avatar_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAdmin_reg_id() {
            return admin_reg_id;
        }

        public void setAdmin_reg_id(int admin_reg_id) {
            this.admin_reg_id = admin_reg_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }
    }

}


