package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/9.  约聊功能   会长抢主播功能
 */

public class AllListBean implements Serializable {


    /**
     * list : [{"id":399,"register_id":47,"anchor_type":"1,3,21","keep_pay":0,"job_method":0,"nickname":"赵思","sex":2,"age":21,"telephone":"","create_time":"1542771234","date_of_birth":"0","height":0,"weight":0,"live_age":1,"per_style":"","is_open":1,"who_add":2,"browse_amount":0,"sort":1259,"pay_low":8000,"pay_high":25000,"check_mark":"","job_plat":"","room_id":0,"type":1,"is_sign":2,"status":1,"wanted_status":0,"check_time":null,"check_description":"","update_time":"0","photo_first":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-11-21/5bf4d222d1334.jpg","img_url":["http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-11-21/5bf4d222d1334.jpg","http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-11-21/5bf4d222a43d5.jpg","http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-11-21/5bf4d222f1806.jpg"],"type_name":["秀场主播","电商主播","模特"],"info_url":"http://www.zhibohome.net/HostResume?code=399","is_linkup":2,"is_collect":0,"is_chat":0,"wanted_status_name":"","rob_num":5},{"id":111,"register_id":4907,"anchor_type":"1","keep_pay":0,"job_method":0,"nickname":"tian橙","sex":2,"age":25,"telephone":"15313118225","create_time":"1540801307","date_of_birth":"0","height":163,"weight":52,"live_age":1,"per_style":"活跃气氛","is_open":1,"who_add":2,"browse_amount":0,"sort":8019,"pay_low":3000,"pay_high":7000,"check_mark":"运营","job_plat":"","room_id":0,"type":2,"is_sign":2,"status":1,"wanted_status":0,"check_time":"1550131747","check_description":"","update_time":"0","photo_first":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-10-29/5bd6c31bd49f4.jpg","img_url":["http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-10-29/5bd6c31bd49f4.jpg","http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-10-29/5bd6c31c24175.jpg","http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-10-29/5bd6c31c032ba.jpg"],"type_name":["秀场主播"],"info_url":"http://www.zhibohome.net/HostResume?code=111","is_linkup":2,"is_collect":0,"is_chat":0,"wanted_status_name":"","rob_num":5,"is_rob":0},{"id":201,"register_id":0,"anchor_type":"1","keep_pay":0,"job_method":0,"nickname":"敏敏VF","sex":2,"age":25,"telephone":"18815287900","create_time":"1541491239","date_of_birth":"0","height":0,"weight":0,"live_age":1,"per_style":"","is_open":1,"who_add":2,"browse_amount":0,"sort":8190,"pay_low":3500,"pay_high":6500,"check_mark":"","job_plat":"","room_id":0,"type":1,"is_sign":2,"status":1,"wanted_status":0,"check_time":null,"check_description":"","update_time":"0","photo_first":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-11-06/5be14a276b36b.jpg","img_url":["http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-11-06/5be14a276b36b.jpg","http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-11-06/5be14a2723c5e.jpg","http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-11-06/5be14a274a397.jpg"],"type_name":["秀场主播"],"info_url":"http://www.zhibohome.net/HostResume?code=201","is_linkup":1,"is_collect":0,"is_chat":0,"wanted_status_name":"","rob_num":5,"is_rob":1}]
     * current_page : 1
     * total_page : 1
     */

    private int current_page;
    private int total_page;
    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable {
        /**
         * id : 399
         * register_id : 47
         * anchor_type : 1,3,21
         * keep_pay : 0
         * job_method : 0
         * nickname : 赵思
         * sex : 2
         * age : 21
         * telephone :
         * create_time : 1542771234
         * date_of_birth : 0
         * height : 0
         * weight : 0
         * live_age : 1
         * per_style :
         * is_open : 1
         * who_add : 2
         * browse_amount : 0
         * sort : 1259
         * pay_low : 8000
         * pay_high : 25000
         * check_mark :
         * job_plat :
         * room_id : 0
         * type : 1
         * is_sign : 2
         * status : 1
         * wanted_status : 0
         * check_time : null
         * check_description :
         * update_time : 0
         * photo_first : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-11-21/5bf4d222d1334.jpg
         * img_url : ["http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-11-21/5bf4d222d1334.jpg","http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-11-21/5bf4d222a43d5.jpg","http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-11-21/5bf4d222f1806.jpg"]
         * type_name : ["秀场主播","电商主播","模特"]
         * info_url : http://www.zhibohome.net/HostResume?code=399
         * is_linkup : 2
         * is_collect : 0
         * is_chat : 0
         * wanted_status_name :
         * rob_num : 5
         * is_rob : 0
         */

        private int id;
        private int register_id;
        private String anchor_type;
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
        private int sort;
        private int pay_low;
        private int pay_high;
        private String check_mark;
        private String job_plat;
        private int room_id;
        private int type;
        private int is_sign;
        private int status;
        private int wanted_status;
        private Object check_time;
        private String check_description;
        private String update_time;
        private String photo_first;
        private String info_url;
        private int is_linkup;
        private int is_collect;
        private int is_chat;
        private String wanted_status_name;
        private int rob_num;
        private int is_rob;
        private String chat_id;

        private List<String> img_url;
        private List<String> type_name;


        //            活跃值 ，为空时不显示
        private String active_status;


        public String getActive_status() {
            return active_status;
        }

        public void setActive_status(String active_status) {
            this.active_status = active_status;
        }

        public String getChat_id() {
            return chat_id;
        }

        public void setChat_id(String chat_id) {
            this.chat_id = chat_id;
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

        public String getCheck_mark() {
            return check_mark;
        }

        public void setCheck_mark(String check_mark) {
            this.check_mark = check_mark;
        }

        public String getJob_plat() {
            return job_plat;
        }

        public void setJob_plat(String job_plat) {
            this.job_plat = job_plat;
        }

        public int getRoom_id() {
            return room_id;
        }

        public void setRoom_id(int room_id) {
            this.room_id = room_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getIs_sign() {
            return is_sign;
        }

        public void setIs_sign(int is_sign) {
            this.is_sign = is_sign;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getWanted_status() {
            return wanted_status;
        }

        public void setWanted_status(int wanted_status) {
            this.wanted_status = wanted_status;
        }

        public Object getCheck_time() {
            return check_time;
        }

        public void setCheck_time(Object check_time) {
            this.check_time = check_time;
        }

        public String getCheck_description() {
            return check_description;
        }

        public void setCheck_description(String check_description) {
            this.check_description = check_description;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getPhoto_first() {
            return photo_first;
        }

        public void setPhoto_first(String photo_first) {
            this.photo_first = photo_first;
        }

        public String getInfo_url() {
            return info_url;
        }

        public void setInfo_url(String info_url) {
            this.info_url = info_url;
        }

        public int getIs_linkup() {
            return is_linkup;
        }

        public void setIs_linkup(int is_linkup) {
            this.is_linkup = is_linkup;
        }

        public int getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(int is_collect) {
            this.is_collect = is_collect;
        }

        public int getIs_chat() {
            return is_chat;
        }

        public void setIs_chat(int is_chat) {
            this.is_chat = is_chat;
        }

        public String getWanted_status_name() {
            return wanted_status_name;
        }

        public void setWanted_status_name(String wanted_status_name) {
            this.wanted_status_name = wanted_status_name;
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

        public List<String> getImg_url() {
            return img_url;
        }

        public void setImg_url(List<String> img_url) {
            this.img_url = img_url;
        }

        public List<String> getType_name() {
            return type_name;
        }

        public void setType_name(List<String> type_name) {
            this.type_name = type_name;
        }
    }
}
