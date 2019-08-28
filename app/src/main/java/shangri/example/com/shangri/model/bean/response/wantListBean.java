package shangri.example.com.shangri.model.bean.response;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/12/25.
 */

public class wantListBean implements Serializable {


    /**
     * current_page : 1
     * total_page : 1
     * resumes : [{"id":1,"register_id":479,"anchor_type":"","job_plat":"0","keep_pay":0,"job_method":0,"nickname":"放开那妹子，让我来！","sex":2,"age":-19,"telephone":"15929242645","create_time":"1537355306","date_of_birth":"2037-09-19","height":0,"weight":0,"live_age":0,"per_style":"","is_open":1,"who_add":1,"browse_amount":0,"sort":0,"pay_low":3000,"pay_high":10000,"room_id":0,"photo_first":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/resume_phone/2018-10-11/5bbf626d2f28c.jpg","type_name":[]},{"id":60,"register_id":506,"anchor_type":"6,8","job_plat":"9","keep_pay":0,"job_method":0,"nickname":"减肥季节成就奖","sex":1,"age":21,"telephone":"15700173371","create_time":"1539271490","date_of_birth":"876499200","height":0,"weight":0,"live_age":0,"per_style":"","is_open":1,"who_add":1,"browse_amount":0,"sort":0,"pay_low":3000,"pay_high":10000,"room_id":8484,"photo_first":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/resume_phone/2018-09-29/5baf64541cd48.jpg","type_name":["电商直播1","电商直播3"]}]
     */

    private int current_page;
    private int total_page;
    private List<ResumesBean> list;

    private int re_number;

    public int getRe_number() {
        return re_number;
    }

    public void setRe_number(int re_number) {
        this.re_number = re_number;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public List<ResumesBean> getResumes() {
        return list;
    }

    public void setResumes(List<ResumesBean> list) {
        this.list = list;
    }

    public static class ResumesBean implements Serializable {
        /**
         * id : 1
         * register_id : 479
         * anchor_type :
         * job_plat : 0
         * keep_pay : 0
         * job_method : 0
         * nickname : 放开那妹子，让我来！
         * sex : 2
         * age : -19
         * telephone : 15929242645
         * create_time : 1537355306
         * date_of_birth : 2037-09-19
         * height : 0
         * weight : 0
         * live_age : 0
         * per_style :
         * is_open : 1
         * who_add : 1
         * browse_amount : 0
         * sort : 0
         * pay_low : 3000
         * pay_high : 10000
         * room_id : 0
         * photo_first : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/resume_phone/2018-10-11/5bbf626d2f28c.jpg
         * type_name : []
         */

        private int id;
        private int register_id;
        private String anchor_type;
        private String job_plat;
        private int keep_pay;
        private int job_method;
        private String nickname;
        private int sex;
        private int age;
        private String telephone;
        private String create_time;
        private String date_of_birth;
        private int height;
        private int weight;
        private int live_age;
        private String per_style;
        private int is_open;
        private int who_add;
        private int browse_amount;
        private String   wanted_status_name;

        private int sort;
        private int pay_low;
        private int pay_high;
        private int room_id;
        private String photo_first;
        private List<String> type_name;
        private List<String> img_url;
        private List<Bitmap> img_bitmap;
        private String info_url;
        private int imgposition=1;

        private int    is_sign;
        private int   is_linkup;

//        是否收藏 1是 0否
        private int   is_collect;
//        是否约聊过 1是 0否
        private int  is_chat;

//        活跃值 ，为空时不显示
        private String active_status;
        //       抢ta人数 0显示 ‘快来抢我’; >0 显示 ‘{rob_num}人正在抢ta
        private int rob_num;
        //        是否已抢 0否 1已抢
        private int is_rob;
        //        约聊id
        private int chat_id;


        public String getActive_status() {
            return active_status;
        }

        public void setActive_status(String active_status) {
            this.active_status = active_status;
        }

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

        public int getChat_id() {
            return chat_id;
        }

        public void setChat_id(int chat_id) {
            this.chat_id = chat_id;
        }

        public ResumesBean() {
        }

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

        public List<Bitmap> getImg_bitmap() {
            return img_bitmap;
        }

        public void setImg_bitmap(List<Bitmap> img_bitmap) {
            this.img_bitmap = img_bitmap;
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

        public int getImgposition() {
            return imgposition;
        }

        public void setImgposition(int imgposition) {
            this.imgposition = imgposition;
        }

        public List<String> getImg_url() {
            return img_url;
        }

        public void setImg_url(List<String> img_url) {
            this.img_url = img_url;
        }

        public String getInfo_url() {
            return info_url;
        }

        public void setInfo_url(String info_url) {
            this.info_url = info_url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRegister_id() {
            return register_id;
        }

        public void setRegister_id(int register_id) {
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

        public int getKeep_pay() {
            return keep_pay;
        }

        public void setKeep_pay(int keep_pay) {
            this.keep_pay = keep_pay;
        }

        public int getJob_method() {
            return job_method;
        }

        public void setJob_method(int job_method) {
            this.job_method = job_method;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
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

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getLive_age() {
            return live_age;
        }

        public void setLive_age(int live_age) {
            this.live_age = live_age;
        }

        public String getPer_style() {
            return per_style;
        }

        public void setPer_style(String per_style) {
            this.per_style = per_style;
        }

        public int getIs_open() {
            return is_open;
        }

        public void setIs_open(int is_open) {
            this.is_open = is_open;
        }

        public int getWho_add() {
            return who_add;
        }

        public void setWho_add(int who_add) {
            this.who_add = who_add;
        }

        public int getBrowse_amount() {
            return browse_amount;
        }

        public void setBrowse_amount(int browse_amount) {
            this.browse_amount = browse_amount;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getPay_low() {
            return pay_low;
        }

        public void setPay_low(int pay_low) {
            this.pay_low = pay_low;
        }

        public int getPay_high() {
            return pay_high;
        }

        public void setPay_high(int pay_high) {
            this.pay_high = pay_high;
        }

        public int getRoom_id() {
            return room_id;
        }

        public void setRoom_id(int room_id) {
            this.room_id = room_id;
        }

        public String getPhoto_first() {
            return photo_first;
        }

        public void setPhoto_first(String photo_first) {
            this.photo_first = photo_first;
        }

        public List<String> getType_name() {
            return type_name;
        }

        public void setType_name(List<String> type_name) {
            this.type_name = type_name;
        }
    }

}
