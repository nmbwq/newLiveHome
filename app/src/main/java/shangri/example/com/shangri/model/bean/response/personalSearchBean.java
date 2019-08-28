package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class personalSearchBean implements Serializable {



        private List<PersonalsBean> personals;

        public List<PersonalsBean> getPersonals() {
            return personals;
        }

        public void setPersonals(List<PersonalsBean> personals) {
            this.personals = personals;
        }

        public static class PersonalsBean {
            /**
             * register_id : 55
             * nickname : b习惯了一个人大
             * avatar_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-04/151507391341954725.jpg
             * type : 2
             * uid : 8933227
             */

            private int register_id;
            private String nickname;
            private String avatar_url;
            private int type;
            private String uid;

            public int getRegister_id() {
                return register_id;
            }

            public void setRegister_id(int register_id) {
                this.register_id = register_id;
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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }
        }

}



