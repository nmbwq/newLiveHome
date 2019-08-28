package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class taskSelectListBean implements Serializable {


    private List<PersonsBean> persons;

    public List<PersonsBean> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonsBean> persons) {
        this.persons = persons;
    }

    public static class PersonsBean {
        /**
         * register_id : 55
         * anchor_name : 习惯了一个人大
         * avatar_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-04/151507391341954725.jpg
         * percent : 267
         * status : 0
         * pre_target_value : 30000
         * pre_reality_value : 80000
         * value : 0
         */

        private int register_id;
        private String anchor_name;

        private String nickname;

        private String avatar_url;
        private int percent;
        private int status;
        private int pre_target_value;
        private int pre_reality_value;
        private int value;

        private int tag;

        private boolean flag=false;

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getTag() {
            return tag;
        }

        public void setTag(int tag) {
            this.tag = tag;
        }

        public int getRegister_id() {
            return register_id;
        }

        public void setRegister_id(int register_id) {
            this.register_id = register_id;
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

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getPre_target_value() {
            return pre_target_value;
        }

        public void setPre_target_value(int pre_target_value) {
            this.pre_target_value = pre_target_value;
        }

        public int getPre_reality_value() {
            return pre_reality_value;
        }

        public void setPre_reality_value(int pre_reality_value) {
            this.pre_reality_value = pre_reality_value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

}



