package shangri.example.com.shangri.model.bean.response;

import com.google.gson.annotations.SerializedName;

import shangri.example.com.shangri.model.bean.request.BaseBeen;

/**
 * 登陆返回的用户信息
 * Created by chengaofu on 2017/6/21.
 */

public class UserInfoBean extends BaseBeen {


        /**
         * register : {"token":"n6fg7IB7Ma4Oio734oj6","register_role":2,"avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2017-12-04/151236575792711235.jpg","sex":"M","nickname":"13588306343","telephone":"13588306343","signature":"","avatar_thumb_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2017-12-04/151236575792711235.jpg?x-oss-process=style/app_avatar_thumbnail"}
         */

        private RegisterBean register;

        public RegisterBean getRegister() {
            return register;
        }

        public void setRegister(RegisterBean register) {
            this.register = register;
        }

        public static class RegisterBean {
            /**
             * token : n6fg7IB7Ma4Oio734oj6
             * register_role : 2
             * avatar_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2017-12-04/151236575792711235.jpg
             * sex : M
             * nickname : 13588306343
             * telephone : 13588306343
             * signature :
             * avatar_thumb_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2017-12-04/151236575792711235.jpg?x-oss-process=style/app_avatar_thumbnail
             */

            @SerializedName("token")
            private String tokenX;
            private int register_role;
            private String avatar_url;
            private String sex;
            private String nickname;
            private String telephone;
            private String signature;
            private String avatar_thumb_url;
            private String wxinfo_id;

            public String getWxinfo_id() {
                return wxinfo_id;
            }

            public void setWxinfo_id(String wxinfo_id) {
                this.wxinfo_id = wxinfo_id;
            }

            public String getTokenX() {
                return tokenX;
            }

            public void setTokenX(String tokenX) {
                this.tokenX = tokenX;
            }

            public int getRegister_role() {
                return register_role;
            }

            public void setRegister_role(int register_role) {
                this.register_role = register_role;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getAvatar_thumb_url() {
                return avatar_thumb_url;
            }

            public void setAvatar_thumb_url(String avatar_thumb_url) {
                this.avatar_thumb_url = avatar_thumb_url;
            }
        }
    }
//    private String register;
//
//    public String getRegister_role() {
//        return register;
//    }
//    public void setRegister_role(String register_role) {
//        this.register = register_role;
//    }


