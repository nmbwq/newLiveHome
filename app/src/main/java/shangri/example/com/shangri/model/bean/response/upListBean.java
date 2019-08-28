package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * 登陆返回的用户信息
 * Created by chengaofu on 2017/6/21.
 */

public class upListBean implements Serializable {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable{
        /**
         * id : 1
         * anchor_id : 47
         * recruit_id : 590
         * resume_message_id : 17
         * avatar_url : http://cdn1.tooohappy.com/avatar/2018-01-03/151497090394139368.jpg
         * nickname : 来了
         * type_name : 户外直播
         * telephone : 13185025851
         * resume_url :
         */

        private int id;
        private int anchor_id;
        private int recruit_id;
        private int resume_message_id;
        private int resume_id;

        private String avatar_url;
        private String nickname;
        private List<String> type_name;
        private String telephone;
        private String resume_url;

        private String resume_telephone;

        String  info_url;


        public int getResume_id() {
            return resume_id;
        }

        public void setResume_id(int resume_id) {
            this.resume_id = resume_id;
        }

        public String getInfo_url() {
            return info_url;
        }

        public void setInfo_url(String info_url) {
            this.info_url = info_url;
        }

        public String getResume_telephone() {
            return resume_telephone;
        }

        public void setResume_telephone(String resume_telephone) {
            this.resume_telephone = resume_telephone;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAnchor_id() {
            return anchor_id;
        }

        public void setAnchor_id(int anchor_id) {
            this.anchor_id = anchor_id;
        }

        public int getRecruit_id() {
            return recruit_id;
        }

        public void setRecruit_id(int recruit_id) {
            this.recruit_id = recruit_id;
        }

        public int getResume_message_id() {
            return resume_message_id;
        }

        public void setResume_message_id(int resume_message_id) {
            this.resume_message_id = resume_message_id;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public List<String> getType_name() {
            return type_name;
        }

        public void setType_name(List<String> type_name) {
            this.type_name = type_name;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getResume_url() {
            return resume_url;
        }

        public void setResume_url(String resume_url) {
            this.resume_url = resume_url;
        }
    }
}



