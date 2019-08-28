package shangri.example.com.shangri.model.bean.response;

import java.util.List;

/**
 * Description:
 * Data：2018/11/6-16:34
 * Author: lin
 */
public class WelfareGuildBean {
    List<GuildList> resume;

    public List<GuildList> getResume() {
        return resume;
    }

    public void setResume(List<GuildList> resume) {
        this.resume = resume;
    }

    public class GuildList {
        String register_id;
        String resume_id;
        String nickname;
        String anchor_type;
        String resume_telephone;
        List<String> type_name;
        String avatar_url;
        String create_time;

        String img_url;

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getRegister_id() {
            return register_id;
        }

        public void setRegister_id(String register_id) {
            this.register_id = register_id;
        }

        public String getResume_id() {
            return resume_id;
        }

        public void setResume_id(String resume_id) {
            this.resume_id = resume_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAnchor_type() {
            return anchor_type;
        }

        public void setAnchor_type(String anchor_type) {
            this.anchor_type = anchor_type;
        }

        public String getResume_telephone() {
            return resume_telephone;
        }

        public void setResume_telephone(String resume_telephone) {
            this.resume_telephone = resume_telephone;
        }

        public List<String> getType_name() {
            return type_name;
        }

        public void setType_name(List<String> type_name) {
            this.type_name = type_name;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }
    }
}
