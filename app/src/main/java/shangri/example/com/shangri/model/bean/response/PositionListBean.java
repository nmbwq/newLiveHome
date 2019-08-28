package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * 登陆返回的用户信息
 * Created by chengaofu on 2017/6/21.
 */

public class PositionListBean implements Serializable {

    /**
     * list : {"current_page":1,"data":[{"id":1003,"title":"","pay_low":1000,"pay_high":5000,"keep_pay":0,"job_method":0,"job_plat":"","anchor_type":"2","salary_type":0,"welfare":["包吃","包住","加班补助"],"work_position":"","work_address":"","company":"未设置1","hot":0,"contact_phone":"","publish_type":1,"lightspot":"","qq":"","weixin":"","email":"","status":4,"plat_name":[],"type_name":"户外直播","info_url":"http://zhibozhijia.com/api/read/recruit/detail/1003"}],"first_page_url":"http://zhibozhijia.com/api/guild/position/list?page=1","from":1,"last_page":1,"last_page_url":"http://zhibozhijia.com/api/guild/position/list?page=1","next_page_url":null,"path":"http://zhibozhijia.com/api/guild/position/list","per_page":12,"prev_page_url":null,"to":1,"total":1}
     */
//    发布招聘总数
    private  int send_count;
    //    免费发布职位数
    private  int free_send_recruit;
    //    发布职位上限数
    private  int send_recruit_upper;
    //    发布职位消耗波豆数
    private  int send_recruit_bd;
    //    可用波豆数
    private  int usable_bd;


    public int getSend_count() {
        return send_count;
    }

    public void setSend_count(int send_count) {
        this.send_count = send_count;
    }

    public int getFree_send_recruit() {
        return free_send_recruit;
    }

    public void setFree_send_recruit(int free_send_recruit) {
        this.free_send_recruit = free_send_recruit;
    }

    public int getSend_recruit_upper() {
        return send_recruit_upper;
    }

    public void setSend_recruit_upper(int send_recruit_upper) {
        this.send_recruit_upper = send_recruit_upper;
    }

    public int getSend_recruit_bd() {
        return send_recruit_bd;
    }

    public void setSend_recruit_bd(int send_recruit_bd) {
        this.send_recruit_bd = send_recruit_bd;
    }

    public int getUsable_bd() {
        return usable_bd;
    }

    public void setUsable_bd(int usable_bd) {
        this.usable_bd = usable_bd;
    }

    private ListBean list;

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public static class ListBean implements Serializable {
        /**
         * current_page : 1
         * data : [{"id":1003,"title":"","pay_low":1000,"pay_high":5000,"keep_pay":0,"job_method":0,"job_plat":"","anchor_type":"2","salary_type":0,"welfare":["包吃","包住","加班补助"],"work_position":"","work_address":"","company":"未设置1","hot":0,"contact_phone":"","publish_type":1,"lightspot":"","qq":"","weixin":"","email":"","status":4,"plat_name":[],"type_name":"户外直播","info_url":"http://zhibozhijia.com/api/read/recruit/detail/1003"}]
         * first_page_url : http://zhibozhijia.com/api/guild/position/list?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://zhibozhijia.com/api/guild/position/list?page=1
         * next_page_url : null
         * path : http://zhibozhijia.com/api/guild/position/list
         * per_page : 12
         * prev_page_url : null
         * to : 1
         * total : 1
         */

        private int current_page;
        private String first_page_url;
        private int from;
        private int last_page;
        private String last_page_url;
        private Object next_page_url;
        private String path;
        private int per_page;
        private Object prev_page_url;
        private int to;
        private int total;
        private List<DataBean> data;



        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public String getFirst_page_url() {
            return first_page_url;
        }

        public void setFirst_page_url(String first_page_url) {
            this.first_page_url = first_page_url;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public String getLast_page_url() {
            return last_page_url;
        }

        public void setLast_page_url(String last_page_url) {
            this.last_page_url = last_page_url;
        }

        public Object getNext_page_url() {
            return next_page_url;
        }

        public void setNext_page_url(Object next_page_url) {
            this.next_page_url = next_page_url;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public Object getPrev_page_url() {
            return prev_page_url;
        }

        public void setPrev_page_url(Object prev_page_url) {
            this.prev_page_url = prev_page_url;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Serializable {
            /**
             * id : 1003
             * title :
             * pay_low : 1000
             * pay_high : 5000
             * keep_pay : 0
             * job_method : 0
             * job_plat :
             * anchor_type : 2
             * salary_type : 0
             * welfare : ["包吃","包住","加班补助"]
             * work_position :
             * work_address :
             * company : 未设置1
             * hot : 0
             * contact_phone :
             * publish_type : 1
             * lightspot :
             * qq :
             * weixin :
             * email :
             * status : 4
             * plat_name : []
             * type_name : 户外直播
             * info_url : http://zhibozhijia.com/api/read/recruit/detail/1003
             */

            private int id;
            private String title;
            private int pay_low;
            private int pay_high;
            private int keep_pay;
            private int job_method;
            private String job_plat;
            private String anchor_type;
            private int salary_type;
            private String work_position;
            private String work_address;
            private String company;
            private int hot;
            private String contact_phone;
            private int publish_type;
            private String lightspot;
            private String qq;
            private String weixin;
            private String email;
            private int status;

            private String job_description;
            private String type_name;
            private String info_url;
            private List<String> welfare;
            private List<PlatNameBean> plat_name;


            private int check_number_real;
            private int share_number;
            private int anchor_send_resume;

            private String check_mark;


            private int is_buy;
            private String expire_time;
            //倒计时时间   当前时间戳-expire_time得来的
            private long time=0;

            private int  is_ggw;

            private String down_des;


            public String getDown_des() {
                return down_des;
            }

            public void setDown_des(String down_des) {
                this.down_des = down_des;
            }

            public int getIs_ggw() {
                return is_ggw;
            }

            public void setIs_ggw(int is_ggw) {
                this.is_ggw = is_ggw;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public String getCheck_mark() {
                return check_mark;
            }

            public int getIs_buy() {
                return is_buy;
            }

            public void setIs_buy(int is_buy) {
                this.is_buy = is_buy;
            }

            public String getExpire_time() {
                return expire_time;
            }

            public void setExpire_time(String expire_time) {
                this.expire_time = expire_time;
            }

            public void setCheck_mark(String check_mark) {
                this.check_mark = check_mark;
            }

            public int getCheck_number_real() {
                return check_number_real;
            }

            public void setCheck_number_real(int check_number_real) {
                this.check_number_real = check_number_real;
            }

            public int getShare_number() {
                return share_number;
            }

            public void setShare_number(int share_number) {
                this.share_number = share_number;
            }

            public int getAnchor_send_resume() {
                return anchor_send_resume;
            }

            public void setAnchor_send_resume(int anchor_send_resume) {
                this.anchor_send_resume = anchor_send_resume;
            }

            public String getJob_description() {
                return job_description;
            }

            public void setJob_description(String job_description) {
                this.job_description = job_description;
            }

            public static class PlatNameBean implements Serializable {
                /**
                 * plat_name : 虎牙直播
                 */

                private String plat_name;

                public String getPlat_name() {
                    return plat_name;
                }

                public void setPlat_name(String plat_name) {
                    this.plat_name = plat_name;
                }
            }


            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
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

            public String getJob_plat() {
                return job_plat;
            }

            public void setJob_plat(String job_plat) {
                this.job_plat = job_plat;
            }

            public String getAnchor_type() {
                return anchor_type;
            }

            public void setAnchor_type(String anchor_type) {
                this.anchor_type = anchor_type;
            }

            public int getSalary_type() {
                return salary_type;
            }

            public void setSalary_type(int salary_type) {
                this.salary_type = salary_type;
            }

            public String getWork_position() {
                return work_position;
            }

            public void setWork_position(String work_position) {
                this.work_position = work_position;
            }

            public String getWork_address() {
                return work_address;
            }

            public void setWork_address(String work_address) {
                this.work_address = work_address;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public int getHot() {
                return hot;
            }

            public void setHot(int hot) {
                this.hot = hot;
            }

            public String getContact_phone() {
                return contact_phone;
            }

            public void setContact_phone(String contact_phone) {
                this.contact_phone = contact_phone;
            }

            public int getPublish_type() {
                return publish_type;
            }

            public void setPublish_type(int publish_type) {
                this.publish_type = publish_type;
            }

            public String getLightspot() {
                return lightspot;
            }

            public void setLightspot(String lightspot) {
                this.lightspot = lightspot;
            }

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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public String getInfo_url() {
                return info_url;
            }

            public void setInfo_url(String info_url) {
                this.info_url = info_url;
            }

            public List<String> getWelfare() {
                return welfare;
            }

            public void setWelfare(List<String> welfare) {
                this.welfare = welfare;
            }

            public List<PlatNameBean> getPlat_name() {
                return plat_name;
            }

            public void setPlat_name(List<PlatNameBean> plat_name) {
                this.plat_name = plat_name;
            }
        }
    }

}



