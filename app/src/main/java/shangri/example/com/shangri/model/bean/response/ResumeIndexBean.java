package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * 登陆返回的用户信息
 * Created by chengaofu on 2017/6/21.
 */

public class ResumeIndexBean implements Serializable {


    /**
     * resume : {"id":"56","register_id":"47","anchor_type":"3,9","job_plat":"1","keep_pay":"0","job_method":"1","nickname":"hfghjhghjg","sex":"1","age":"2","telephone":"13185025851","create_time":"1539269314","date_of_birth":"1463875200","height":"170","weight":"50","live_age":"1","per_style":"1111","is_open":"1","who_add":"1","browse_amount":"0","sort":"522","pay_low":"4000","pay_high":"7000","check_mark":"","room_id":"12564367","type_name":["电商直播","电商直播4"],"plat_name":"花椒直播"}
     */

    public int status;

    private ResumeBean resume;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ResumeBean getResume() {
        return resume;
    }

    public void setResume(ResumeBean resume) {
        this.resume = resume;
    }

    public static class ResumeBean implements Serializable {
        /**
         * id : 56
         * register_id : 47
         * anchor_type : 3,9
         * job_plat : 1
         * keep_pay : 0
         * job_method : 1
         * nickname : hfghjhghjg
         * sex : 1
         * age : 2
         * telephone : 13185025851
         * create_time : 1539269314
         * date_of_birth : 1463875200
         * height : 170
         * weight : 50
         * live_age : 1
         * per_style : 1111
         * is_open : 1
         * who_add : 1
         * browse_amount : 0
         * sort : 522
         * pay_low : 4000
         * pay_high : 7000
         * check_mark :
         * room_id : 12564367
         * type_name : ["电商直播","电商直播4"]
         * plat_name : 花椒直播
         */

        private String id;
        private String register_id;
        private String anchor_type;
        private String job_plat;
        private String keep_pay;
        private String job_method;
        private String nickname;
        private String sex;
        private String age;
        private String telephone;
        private String create_time;
        private String date_of_birth;
        private String height;
        private String weight;
        private String live_age;
        private String per_style;
        private String is_open;
        private String who_add;
        private String browse_amount;
        private String sort;
        private String pay_low;
        private String pay_high;
        private String check_mark;
        private String room_id;
        private String plat_name;
        private List<String> type_name;
        private List<PhotoBean> photo;

        private int is_sign;
        private int is_linkup;

        private int is_collect;


        private int status;
        private String check_time;
        private String check_description;
        private String wanted_status_name;
        private String wanted_status;


        public String getWanted_status_name() {
            return wanted_status_name;
        }

        public void setWanted_status_name(String wanted_status_name) {
            this.wanted_status_name = wanted_status_name;
        }

        public String getWanted_status() {
            return wanted_status;
        }

        public void setWanted_status(String wanted_status) {
            this.wanted_status = wanted_status;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCheck_time() {
            return check_time;
        }

        public void setCheck_time(String check_time) {
            this.check_time = check_time;
        }

        public String getCheck_description() {
            return check_description;
        }

        public void setCheck_description(String check_description) {
            this.check_description = check_description;
        }

        public int getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(int is_collect) {
            this.is_collect = is_collect;
        }

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

        public List<PhotoBean> getPhoto() {
            return photo;
        }

        public void setPhoto(List<PhotoBean> photo) {
            this.photo = photo;
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

        public String getCheck_mark() {
            return check_mark;
        }

        public void setCheck_mark(String check_mark) {
            this.check_mark = check_mark;
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

        public List<String> getType_name() {
            return type_name;
        }

        public void setType_name(List<String> type_name) {
            this.type_name = type_name;
        }

        public static class PhotoBean implements Serializable {
            /**
             * place : 1
             * img_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/resume_phone/2018-09-29/5baf64541cd48.jpg
             */

            private int place;
            private String img_url;

            public int getPlace() {
                return place;
            }

            public void setPlace(int place) {
                this.place = place;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }
        }
    }
}



