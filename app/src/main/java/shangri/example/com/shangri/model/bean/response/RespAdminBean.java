package shangri.example.com.shangri.model.bean.response;

import java.util.List;

/**
 * 团队管理
 */
public class RespAdminBean {
    private List<AdminsBean> admins;

    public List<AdminsBean> getAdmins() {
        return admins;
    }

    public void setAdmins(List<AdminsBean> admins) {
        this.admins = admins;
    }

    public static class AdminsBean {
        /**
         * id : 353
         * nickname : 18668121550
         * avatar_url :
         * inspect_num : 0
         * inspect_anchors : 0
         * pre_inspect_num : 0
         * pre_inspect_anchors : 0
         */

        private String id;
        private String nickname;
        private String avatar_url;
        private String inspect_num;
        private String inspect_anchors;
        private String pre_inspect_num;
        private String pre_inspect_anchors;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getInspect_num() {
            return inspect_num;
        }

        public void setInspect_num(String inspect_num) {
            this.inspect_num = inspect_num;
        }

        public String getInspect_anchors() {
            return inspect_anchors;
        }

        public void setInspect_anchors(String inspect_anchors) {
            this.inspect_anchors = inspect_anchors;
        }

        public String getPre_inspect_num() {
            return pre_inspect_num;
        }

        public void setPre_inspect_num(String pre_inspect_num) {
            this.pre_inspect_num = pre_inspect_num;
        }

        public String getPre_inspect_anchors() {
            return pre_inspect_anchors;
        }

        public void setPre_inspect_anchors(String pre_inspect_anchors) {
            this.pre_inspect_anchors = pre_inspect_anchors;
        }

    }
}
