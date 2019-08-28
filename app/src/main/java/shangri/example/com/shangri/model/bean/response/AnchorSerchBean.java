package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class AnchorSerchBean implements Serializable {


    private List<AnchorBean> anchor;

    public List<AnchorBean> getAnchor() {
        return anchor;
    }

    public void setAnchor(List<AnchorBean> anchor) {
        this.anchor = anchor;
    }

    public static class AnchorBean {
        /**
         * anchor_id : 10027267
         * anchor_name : 🐘盟余文乐！
         */

        private String anchor_id;
        private String anchor_name;

        public String getAnchor_id() {
            return anchor_id;
        }

        public void setAnchor_id(String anchor_id) {
            this.anchor_id = anchor_id;
        }

        public String getAnchor_name() {
            return anchor_name;
        }

        public void setAnchor_name(String anchor_name) {
            this.anchor_name = anchor_name;
        }
    }
}



