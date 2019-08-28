package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/4.
 */

public class timeBean implements Serializable{


        /**
         * exist : 2
         * member : {"member_time":"1531560247","member_status":1}
         */

        private String exist;
        private MemberBean member;

        public String getExist() {
            return exist;
        }

        public void setExist(String exist) {
            this.exist = exist;
        }

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public static class MemberBean {
            /**
             * member_time : 1531560247
             * member_status : 1
             */

            private String member_time;
            private int member_status;

            public String getMember_time() {
                return member_time;
            }

            public void setMember_time(String member_time) {
                this.member_time = member_time;
            }

            public int getMember_status() {
                return member_status;
            }

            public void setMember_status(int member_status) {
                this.member_status = member_status;
            }
        }

}
