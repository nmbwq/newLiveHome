package shangri.example.com.shangri.model.bean.response;

import java.util.List;

public class CommunicationBean {


    /**
     * is_resume : 1
     * resume_telephone : 13185025851
     * list : {"current_page":1,"data":[{"id":"939","title":"秀场直播","pay_low":"6000","pay_high":"8000","keep_pay":"0","job_method":"2","job_plat":"1,2,6","anchor_type":"4","salary_type":"1","welfare":["包住","房补"],"work_position":"广东-深圳","work_address":"","company":"偶遇也许我","hot":"1","contact_phone":"18667165996","publish_type":"1","lightspot":"OK嘻嘻我","qq":"405698952","weixin":"656565","email":"123@162.com","plat_name":[{"plat_name":"花椒直播"},{"plat_name":"假直播"}],"type_name":"游戏直播","info_url":"http://test.ilivehome.net/api/read/recruit/detail/939","is_collect":0,"is_linkup":1},{"id":"993","title":"秀场直播","pay_low":"6000","pay_high":"80000","keep_pay":"5000","job_method":"1","job_plat":"1,2","anchor_type":"1","salary_type":"0","welfare":["五险一金","年底双薪","绩效奖金","全勤奖"],"work_position":"成都","work_address":"","company":"四川世纪惊鸿科技有限公司","hot":"1","contact_phone":"15822564415","publish_type":"1","lightspot":"","qq":"","weixin":"","email":"","plat_name":[{"plat_name":"花椒直播"}],"type_name":"秀场直播","info_url":"http://test.ilivehome.net/api/read/recruit/detail/993","is_collect":0,"is_linkup":1}],"first_page_url":"http://test.ilivehome.net/api/recruit/link/up/list?page=1","from":1,"last_page":1,"last_page_url":"http://test.ilivehome.net/api/recruit/link/up/list?page=1","next_page_url":null,"path":"http://test.ilivehome.net/api/recruit/link/up/list","per_page":12,"prev_page_url":null,"to":2,"total":2}
     */

    private int is_resume;
    private String resume_telephone;
    private ListBean list;

    public int getIs_resume() {
        return is_resume;
    }

    public void setIs_resume(int is_resume) {
        this.is_resume = is_resume;
    }

    public String getResume_telephone() {
        return resume_telephone;
    }

    public void setResume_telephone(String resume_telephone) {
        this.resume_telephone = resume_telephone;
    }

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * current_page : 1
         * data : [{"id":"939","title":"秀场直播","pay_low":"6000","pay_high":"8000","keep_pay":"0","job_method":"2","job_plat":"1,2,6","anchor_type":"4","salary_type":"1","welfare":["包住","房补"],"work_position":"广东-深圳","work_address":"","company":"偶遇也许我","hot":"1","contact_phone":"18667165996","publish_type":"1","lightspot":"OK嘻嘻我","qq":"405698952","weixin":"656565","email":"123@162.com","plat_name":[{"plat_name":"花椒直播"},{"plat_name":"假直播"}],"type_name":"游戏直播","info_url":"http://test.ilivehome.net/api/read/recruit/detail/939","is_collect":0,"is_linkup":1},{"id":"993","title":"秀场直播","pay_low":"6000","pay_high":"80000","keep_pay":"5000","job_method":"1","job_plat":"1,2","anchor_type":"1","salary_type":"0","welfare":["五险一金","年底双薪","绩效奖金","全勤奖"],"work_position":"成都","work_address":"","company":"四川世纪惊鸿科技有限公司","hot":"1","contact_phone":"15822564415","publish_type":"1","lightspot":"","qq":"","weixin":"","email":"","plat_name":[{"plat_name":"花椒直播"}],"type_name":"秀场直播","info_url":"http://test.ilivehome.net/api/read/recruit/detail/993","is_collect":0,"is_linkup":1}]
         * first_page_url : http://test.ilivehome.net/api/recruit/link/up/list?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://test.ilivehome.net/api/recruit/link/up/list?page=1
         * next_page_url : null
         * path : http://test.ilivehome.net/api/recruit/link/up/list
         * per_page : 12
         * prev_page_url : null
         * to : 2
         * total : 2
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

        public static class DataBean {
            /**
             * id : 939
             * title : 秀场直播
             * pay_low : 6000
             * pay_high : 8000
             * keep_pay : 0
             * job_method : 2
             * job_plat : 1,2,6
             * anchor_type : 4
             * salary_type : 1
             * welfare : ["包住","房补"]
             * work_position : 广东-深圳
             * work_address :
             * company : 偶遇也许我
             * hot : 1
             * contact_phone : 18667165996
             * publish_type : 1
             * lightspot : OK嘻嘻我
             * qq : 405698952
             * weixin : 656565
             * email : 123@162.com
             * plat_name : [{"plat_name":"花椒直播"},{"plat_name":"假直播"}]
             * type_name : 游戏直播
             * info_url : http://test.ilivehome.net/api/read/recruit/detail/939
             * is_collect : 0
             * is_linkup : 1
             */

            private String id;
            private String title;
            private String pay_low;
            private String pay_high;
            private String keep_pay;
            private String job_method;
            private String job_plat;
            private String anchor_type;
            private String salary_type;
            private String work_position;
            private String work_address;
            private String company;
            private String hot;
            private String contact_phone;
            private String publish_type;
            private String lightspot;
            private String qq;
            private String weixin;
            private String email;
            private String type_name;
            private String info_url;
            private int is_collect;
            private int is_linkup;
            private List<String> welfare;
            private List<PlatNameBean> plat_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getJob_method() {
                return job_method;
            }

            public void setJob_method(String job_method) {
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

            public String getSalary_type() {
                return salary_type;
            }

            public void setSalary_type(String salary_type) {
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

            public String getHot() {
                return hot;
            }

            public void setHot(String hot) {
                this.hot = hot;
            }

            public String getContact_phone() {
                return contact_phone;
            }

            public void setContact_phone(String contact_phone) {
                this.contact_phone = contact_phone;
            }

            public String getPublish_type() {
                return publish_type;
            }

            public void setPublish_type(String publish_type) {
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

            public int getIs_collect() {
                return is_collect;
            }

            public void setIs_collect(int is_collect) {
                this.is_collect = is_collect;
            }

            public int getIs_linkup() {
                return is_linkup;
            }

            public void setIs_linkup(int is_linkup) {
                this.is_linkup = is_linkup;
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

            public static class PlatNameBean {
                /**
                 * plat_name : 花椒直播
                 */

                private String plat_name;

                public String getPlat_name() {
                    return plat_name;
                }

                public void setPlat_name(String plat_name) {
                    this.plat_name = plat_name;
                }
            }
        }
    }
}
