package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/5.
 */

public class ChoiceAnchorsBean implements Serializable {


        /**
         * current_page : 1
         * total_page : 1
         * anchors : [{"register_guild_id":"1","anchor_name":"Coco💐阿标"},{"register_guild_id":"2","anchor_name":"🛡盾上加鹿🦌"},{"register_guild_id":"3","anchor_name":"四爷"},{"register_guild_id":"4","anchor_name":"大大-"},{"register_guild_id":"5","anchor_name":"习惯了一个人"}]
         */

        private String current_page;
        private int total_page;
        private List<AnchorsBean> anchors;

        public String getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(String current_page) {
            this.current_page = current_page;
        }

        public int getTotal_page() {
            return total_page;
        }

        public void setTotal_page(int total_page) {
            this.total_page = total_page;
        }

        public List<AnchorsBean> getAnchors() {
            return anchors;
        }

        public void setAnchors(List<AnchorsBean> anchors) {
            this.anchors = anchors;
        }

        public static class AnchorsBean {
            /**
             * register_guild_id : 1
             * anchor_name : Coco💐阿标
             */

            private String register_guild_id;
            private String anchor_name;
            private String uid;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getRegister_guild_id() {
                return register_guild_id;
            }

            public void setRegister_guild_id(String register_guild_id) {
                this.register_guild_id = register_guild_id;
            }

            public String getAnchor_name() {
                return anchor_name;
            }

            public void setAnchor_name(String anchor_name) {
                this.anchor_name = anchor_name;
            }
        }

}
