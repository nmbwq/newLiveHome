package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class partSelectBean implements Serializable {



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
             * uid : 8933227
             * nickname : 八角惯了一个人大
             * status : 1
             * target_value : 30000
             * reality_value : 80000
             */

            private int register_id;
            private String uid;
            private String nickname;
            private int status;
            private int target_value;
            private int reality_value;
            //后太需要返回的
            private int percent;
            private String avatar_url;


            public int getPercent() {
                return percent;
            }

            public void setPercent(int percent) {
                this.percent = percent;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public int getRegister_id() {
                return register_id;
            }

            public void setRegister_id(int register_id) {
                this.register_id = register_id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getTarget_value() {
                return target_value;
            }

            public void setTarget_value(int target_value) {
                this.target_value = target_value;
            }

            public int getReality_value() {
                return reality_value;
            }

            public void setReality_value(int reality_value) {
                this.reality_value = reality_value;
            }
        }

}



