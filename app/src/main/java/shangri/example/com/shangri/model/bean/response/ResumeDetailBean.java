package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * Data：2018/11/4-14:24
 * Author: lin
 */
public class ResumeDetailBean implements Serializable {
    ResumeBean resume;

    public ResumeBean getResume() {
        return resume;
    }

    public void setResume(ResumeBean resume) {
        this.resume = resume;
    }

    public static class ResumeBean implements Serializable {
        String id;
        String register_id;
        String anchor_type;
        String job_plat;
        String keep_pay;
        String job_method;
        String nickname;
        String sex;
        String age;
        String telephone;
        String create_time;
        String date_of_birth;
        String height;
        String weight;
        String live_age;
        String per_style;
        String is_open;
        String who_add;
        String browse_amount;
        String sort;
        String pay_low;
        String pay_high;
        String room_id;
        String plat_name;
        List<Photo> photo;
        List<String> type_name;

        int is_collect;

        int  is_chat;
//        是否抢过 1是0否
        int  is_rob;
        String  chat_id;
        //几人在抢
        int  rob_num;


        public int getRob_num() {
            return rob_num;
        }

        public void setRob_num(int rob_num) {
            this.rob_num = rob_num;
        }

        public int getIs_rob() {
            return is_rob;
        }

        public void setIs_rob(int is_rob) {
            this.is_rob = is_rob;
        }

        public String getChat_id() {
            return chat_id;
        }

        public void setChat_id(String chat_id) {
            this.chat_id = chat_id;
        }

        private String wanted_status_name;

        public String getWanted_status_name() {
            return wanted_status_name;
        }

        public void setWanted_status_name(String wanted_status_name) {
            this.wanted_status_name = wanted_status_name;
        }

        public int getIs_chat() {
            return is_chat;
        }

        public void setIs_chat(int is_chat) {
            this.is_chat = is_chat;
        }

        public int getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(int is_collect) {
            this.is_collect = is_collect;
        }

        int is_sign;
        int is_linkup;

        public int getIs_sign() {
            return is_sign;
        }

        public void setIs_sign(int is_sign) {
            this.is_sign = is_sign;
        }

        public int getIs_linkup() {
            return is_linkup;
        }

        public void setIs_linkup(int is_linkup) {
            this.is_linkup = is_linkup;
        }

        public List<String> getType_name() {
            return type_name;
        }

        public void setType_name(List<String> type_name) {
            this.type_name = type_name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRegister_id() {
            return register_id;
        }

        public void setRegister_id(String register_id) {
            this.register_id = register_id;
        }

        public String getAnchor_type() {
            return anchor_type;
        }

        public void setAnchor_type(String anchor_type) {
            this.anchor_type = anchor_type;
        }

        public String getJob_plat() {
            return job_plat;
        }

        public void setJob_plat(String job_plat) {
            this.job_plat = job_plat;
        }

        public String getKeep_pay() {
            return keep_pay;
        }

        public void setKeep_pay(String keep_pay) {
            this.keep_pay = keep_pay;
        }

        public String getJob_method() {
            return job_method;
        }

        public void setJob_method(String job_method) {
            this.job_method = job_method;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getDate_of_birth() {
            return date_of_birth;
        }

        public void setDate_of_birth(String date_of_birth) {
            this.date_of_birth = date_of_birth;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getLive_age() {
            return live_age;
        }

        public void setLive_age(String live_age) {
            this.live_age = live_age;
        }

        public String getPer_style() {
            return per_style;
        }

        public void setPer_style(String per_style) {
            this.per_style = per_style;
        }

        public String getIs_open() {
            return is_open;
        }

        public void setIs_open(String is_open) {
            this.is_open = is_open;
        }

        public String getWho_add() {
            return who_add;
        }

        public void setWho_add(String who_add) {
            this.who_add = who_add;
        }

        public String getBrowse_amount() {
            return browse_amount;
        }

        public void setBrowse_amount(String browse_amount) {
            this.browse_amount = browse_amount;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getPay_low() {
            return pay_low;
        }

        public void setPay_low(String pay_low) {
            this.pay_low = pay_low;
        }

        public String getPay_high() {
            return pay_high;
        }

        public void setPay_high(String pay_high) {
            this.pay_high = pay_high;
        }

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }

        public String getPlat_name() {
            return plat_name;
        }

        public void setPlat_name(String plat_name) {
            this.plat_name = plat_name;
        }

        public List<Photo> getPhoto() {
            return photo;
        }

        public void setPhoto(List<Photo> photo) {
            this.photo = photo;
        }

        public static class Photo implements Serializable {
            String place;
            String img_url;

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }
        }
    }
}
