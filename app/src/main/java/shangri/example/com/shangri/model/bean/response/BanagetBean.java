package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class BanagetBean implements Serializable {

//    是否已经分享 1是0否
    int is_share;

    //主播拨打电话次数
    int number;
    //等于1直接拨打电话  等于0 判断次数
    int is_call;

    public int getIs_call() {
        return is_call;
    }

    public void setIs_call(int is_call) {
        this.is_call = is_call;
    }

    public int getNumber() {
        return number;
    }

    public int getIs_share() {
        return is_share;
    }

    public void setIs_share(int is_share) {
        this.is_share = is_share;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private List<BannerBean> banner;

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class BannerBean {
        /**
         * id : 43
         * banner_url : http://cdn1.tooohappy.com/banner/2017-11-23/151140204637051408.jpg
         * recruit_id : 259
         * relation_url : http://zhibozhijia.com/api/read/recruit/detail/280
         * is_collect : 0
         * contact_phone : 15866523347
         * type : 2
         */

        private int id;
        private String banner_url;
        private String recruit_id;
        private String relation_url;
        private int is_collect;
        private String contact_phone;
        private int type;
        private String title;
        private String pay_low;
        private String pay_high;
        private String keep_pay;
        private String work_position;
        private String company;
        private String type_name;

        private String qq;
        private String weixin;
        private String email;

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getKeep_pay() {
            return keep_pay;
        }

        public void setKeep_pay(String keep_pay) {
            this.keep_pay = keep_pay;
        }

        public String getWork_position() {
            return work_position;
        }

        public void setWork_position(String work_position) {
            this.work_position = work_position;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBanner_url() {
            return banner_url;
        }

        public void setBanner_url(String banner_url) {
            this.banner_url = banner_url;
        }

        public String getRecruit_id() {
            return recruit_id;
        }

        public void setRecruit_id(String recruit_id) {
            this.recruit_id = recruit_id;
        }

        public String getRelation_url() {
            return relation_url;
        }

        public void setRelation_url(String relation_url) {
            this.relation_url = relation_url;
        }

        public int getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(int is_collect) {
            this.is_collect = is_collect;
        }

        public String getContact_phone() {
            return contact_phone;
        }

        public void setContact_phone(String contact_phone) {
            this.contact_phone = contact_phone;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}

