package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/3.
 */

public class mineAnchorListDataBean implements Serializable {


    private List<AnchorBean> anchor;

    public List<AnchorBean> getAnchor() {
        return anchor;
    }

    public void setAnchor(List<AnchorBean> anchor) {
        this.anchor = anchor;
    }

    public static class AnchorBean {
        /**
         * anchor_uid : 261409476
         * anchor_name : 丫丫 疯子的小徒弟
         * avatar_url : http://img2.user.kanimg.com/usrimg/261409476/300x300?t=1533288726
         */

        private String anchor_uid;
        private String anchor_name;
        private String avatar_url;
        public boolean open;

        public boolean isOpen() {
            return open;
        }

        public void setOpen(boolean open) {
            this.open = open;
        }

        public String getAnchor_uid() {
            return anchor_uid;
        }

        public void setAnchor_uid(String anchor_uid) {
            this.anchor_uid = anchor_uid;
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

}

