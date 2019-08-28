package shangri.example.com.shangri.model.bean.response;

import java.util.List;

/**
 * Description:
 * Data：2018/11/4-13:01
 * Author: lin
 */
public class RecruitDetailBean {


    /**
     * is_buy : 0
     * expire_time : 0
     * xcx : [{"id":"21163","title":"","pay_low":"26000","pay_high":"27000","keep_pay":"2000","company":"自由人网络游戏公司","company_scale":"","job":"","job_nature":"","contact_person":"","contact_phone":"13093791795","phone_address":"","weixin":"","recruit_users":"","education":"","work_years":"","work_position":"湖南-长沙","work_address":"","welfare":"周结","sex":"","job_description":"122346","company_description":"我的生活\n最好\n有你","user_title":"","email":"","issue_time":"","publish_time":"1544516470","source_web":"","source_url":"","status":"1","job_method":"3","job_plat":"0","anchor_type":"3","salary_type":"4","hot":"1","publish_type":"3","share_click":"1","lightspot":"测试请拒绝","delete_time":"0","qq":"","create_id":"3311","check_time":"1546068167","check_number_real":"188","check_number_run":"0","check_number_timing":"188","check_mark":null,"preview_num":"0","anchor_tab":"0","update_time":"1546068161","admin_id":"1","create_time":"0","type_name":"电商主播","plat_name":"全平台","is_collect":0,"is_html":2,"companyInfo":{"id":"1101","registrant_id":"3311","company_name":"自由人网络游戏公司","company_short_name":"自由人","company_scale":"50人以下","anchor_scale":"400","location":"未设置","logo":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-10-26/5bd300ddbbf20.jpg","telephone":"13093791795","company_description":"我的生活\n最好\n有你","home_url":"","who_add":"1","create_time":"0","update_time":"0"},"is_vip":0,"is_ggw":0,"token":"s0YI716D7uDDx7d1Bdy7yAsDKIBZekXxXzTU7ddZM7sYdAZmTI"}]
     */

    private int is_buy;
    private int expire_time;
    private List<XcxBean> xcx;

    public int getIs_buy() {
        return is_buy;
    }

    public void setIs_buy(int is_buy) {
        this.is_buy = is_buy;
    }

    public int getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(int expire_time) {
        this.expire_time = expire_time;
    }

    public List<XcxBean> getXcx() {
        return xcx;
    }

    public void setXcx(List<XcxBean> xcx) {
        this.xcx = xcx;
    }

    public static class XcxBean {
        /**
         * id : 21163
         * title :
         * pay_low : 26000
         * pay_high : 27000
         * keep_pay : 2000
         * company : 自由人网络游戏公司
         * company_scale :
         * job :
         * job_nature :
         * contact_person :
         * contact_phone : 13093791795
         * phone_address :
         * weixin :
         * recruit_users :
         * education :
         * work_years :
         * work_position : 湖南-长沙
         * work_address :
         * welfare : 周结
         * sex :
         * job_description : 122346
         * company_description : 我的生活
         * 最好
         * 有你
         * user_title :
         * email :
         * issue_time :
         * publish_time : 1544516470
         * source_web :
         * source_url :
         * status : 1
         * job_method : 3
         * job_plat : 0
         * anchor_type : 3
         * salary_type : 4
         * hot : 1
         * publish_type : 3
         * share_click : 1
         * lightspot : 测试请拒绝
         * delete_time : 0
         * qq :
         * create_id : 3311
         * check_time : 1546068167
         * check_number_real : 188
         * check_number_run : 0
         * check_number_timing : 188
         * check_mark : null
         * preview_num : 0
         * anchor_tab : 0
         * update_time : 1546068161
         * admin_id : 1
         * create_time : 0
         * type_name : 电商主播
         * plat_name : 全平台
         * is_collect : 0
         * is_html : 2
         * companyInfo : {"id":"1101","registrant_id":"3311","company_name":"自由人网络游戏公司","company_short_name":"自由人","company_scale":"50人以下","anchor_scale":"400","location":"未设置","logo":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-10-26/5bd300ddbbf20.jpg","telephone":"13093791795","company_description":"我的生活\n最好\n有你","home_url":"","who_add":"1","create_time":"0","update_time":"0"}
         * is_vip : 0
         * is_ggw : 0
         * token : s0YI716D7uDDx7d1Bdy7yAsDKIBZekXxXzTU7ddZM7sYdAZmTI
         */

        private String id;
        private String title;
        private String pay_low;
        private String pay_high;
        private String keep_pay;
        private String company;
        private String company_scale;
        private String job;
        private String job_nature;
        private String contact_person;
        private String contact_phone;
        private String phone_address;
        private String weixin;
        private String recruit_users;
        private String education;
        private String work_years;
        private String work_position;
        private String work_address;
        private String welfare;
        private String sex;
        private String job_description;
        private String company_description;
        private String user_title;
        private String email;
        private String issue_time;
        private String publish_time;
        private String source_web;
        private String source_url;
        private String status;
        private String job_method;
        private String job_plat;
        private String anchor_type;
        private String salary_type;
        private String hot;
        private String publish_type;
        private String share_click;
        private String lightspot;
        private String delete_time;
        private String qq;
        private String create_id;
        private String check_time;
        private String check_number_real;
        private String check_number_run;
        private String check_number_timing;
        private Object check_mark;
        private String preview_num;
        private String anchor_tab;
        private String update_time;
        private String admin_id;
        private String create_time;
        private String type_name;
        private String plat_name;
        private int is_collect;
        private int is_html;
        private CompanyInfoBean companyInfo;
        private int is_vip;
        private int is_ggw;
        private String token;

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

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getCompany_scale() {
            return company_scale;
        }

        public void setCompany_scale(String company_scale) {
            this.company_scale = company_scale;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getJob_nature() {
            return job_nature;
        }

        public void setJob_nature(String job_nature) {
            this.job_nature = job_nature;
        }

        public String getContact_person() {
            return contact_person;
        }

        public void setContact_person(String contact_person) {
            this.contact_person = contact_person;
        }

        public String getContact_phone() {
            return contact_phone;
        }

        public void setContact_phone(String contact_phone) {
            this.contact_phone = contact_phone;
        }

        public String getPhone_address() {
            return phone_address;
        }

        public void setPhone_address(String phone_address) {
            this.phone_address = phone_address;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getRecruit_users() {
            return recruit_users;
        }

        public void setRecruit_users(String recruit_users) {
            this.recruit_users = recruit_users;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getWork_years() {
            return work_years;
        }

        public void setWork_years(String work_years) {
            this.work_years = work_years;
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

        public String getWelfare() {
            return welfare;
        }

        public void setWelfare(String welfare) {
            this.welfare = welfare;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getJob_description() {
            return job_description;
        }

        public void setJob_description(String job_description) {
            this.job_description = job_description;
        }

        public String getCompany_description() {
            return company_description;
        }

        public void setCompany_description(String company_description) {
            this.company_description = company_description;
        }

        public String getUser_title() {
            return user_title;
        }

        public void setUser_title(String user_title) {
            this.user_title = user_title;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIssue_time() {
            return issue_time;
        }

        public void setIssue_time(String issue_time) {
            this.issue_time = issue_time;
        }

        public String getPublish_time() {
            return publish_time;
        }

        public void setPublish_time(String publish_time) {
            this.publish_time = publish_time;
        }

        public String getSource_web() {
            return source_web;
        }

        public void setSource_web(String source_web) {
            this.source_web = source_web;
        }

        public String getSource_url() {
            return source_url;
        }

        public void setSource_url(String source_url) {
            this.source_url = source_url;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getHot() {
            return hot;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }

        public String getPublish_type() {
            return publish_type;
        }

        public void setPublish_type(String publish_type) {
            this.publish_type = publish_type;
        }

        public String getShare_click() {
            return share_click;
        }

        public void setShare_click(String share_click) {
            this.share_click = share_click;
        }

        public String getLightspot() {
            return lightspot;
        }

        public void setLightspot(String lightspot) {
            this.lightspot = lightspot;
        }

        public String getDelete_time() {
            return delete_time;
        }

        public void setDelete_time(String delete_time) {
            this.delete_time = delete_time;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getCreate_id() {
            return create_id;
        }

        public void setCreate_id(String create_id) {
            this.create_id = create_id;
        }

        public String getCheck_time() {
            return check_time;
        }

        public void setCheck_time(String check_time) {
            this.check_time = check_time;
        }

        public String getCheck_number_real() {
            return check_number_real;
        }

        public void setCheck_number_real(String check_number_real) {
            this.check_number_real = check_number_real;
        }

        public String getCheck_number_run() {
            return check_number_run;
        }

        public void setCheck_number_run(String check_number_run) {
            this.check_number_run = check_number_run;
        }

        public String getCheck_number_timing() {
            return check_number_timing;
        }

        public void setCheck_number_timing(String check_number_timing) {
            this.check_number_timing = check_number_timing;
        }

        public Object getCheck_mark() {
            return check_mark;
        }

        public void setCheck_mark(Object check_mark) {
            this.check_mark = check_mark;
        }

        public String getPreview_num() {
            return preview_num;
        }

        public void setPreview_num(String preview_num) {
            this.preview_num = preview_num;
        }

        public String getAnchor_tab() {
            return anchor_tab;
        }

        public void setAnchor_tab(String anchor_tab) {
            this.anchor_tab = anchor_tab;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(String admin_id) {
            this.admin_id = admin_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getPlat_name() {
            return plat_name;
        }

        public void setPlat_name(String plat_name) {
            this.plat_name = plat_name;
        }

        public int getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(int is_collect) {
            this.is_collect = is_collect;
        }

        public int getIs_html() {
            return is_html;
        }

        public void setIs_html(int is_html) {
            this.is_html = is_html;
        }

        public CompanyInfoBean getCompanyInfo() {
            return companyInfo;
        }

        public void setCompanyInfo(CompanyInfoBean companyInfo) {
            this.companyInfo = companyInfo;
        }

        public int getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(int is_vip) {
            this.is_vip = is_vip;
        }

        public int getIs_ggw() {
            return is_ggw;
        }

        public void setIs_ggw(int is_ggw) {
            this.is_ggw = is_ggw;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class CompanyInfoBean {
            /**
             * id : 1101
             * registrant_id : 3311
             * company_name : 自由人网络游戏公司
             * company_short_name : 自由人
             * company_scale : 50人以下
             * anchor_scale : 400
             * location : 未设置
             * logo : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-10-26/5bd300ddbbf20.jpg
             * telephone : 13093791795
             * company_description : 我的生活
             * 最好
             * 有你
             * home_url :
             * who_add : 1
             * create_time : 0
             * update_time : 0
             */

            private String id;
            private String registrant_id;
            private String company_name;
            private String company_short_name;
            private String company_scale;
            private String anchor_scale;
            private String location;
            private String logo;
            private String telephone;
            private String company_description;
            private String home_url;
            private String who_add;
            private String create_time;
            private String update_time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRegistrant_id() {
                return registrant_id;
            }

            public void setRegistrant_id(String registrant_id) {
                this.registrant_id = registrant_id;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getCompany_short_name() {
                return company_short_name;
            }

            public void setCompany_short_name(String company_short_name) {
                this.company_short_name = company_short_name;
            }

            public String getCompany_scale() {
                return company_scale;
            }

            public void setCompany_scale(String company_scale) {
                this.company_scale = company_scale;
            }

            public String getAnchor_scale() {
                return anchor_scale;
            }

            public void setAnchor_scale(String anchor_scale) {
                this.anchor_scale = anchor_scale;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getCompany_description() {
                return company_description;
            }

            public void setCompany_description(String company_description) {
                this.company_description = company_description;
            }

            public String getHome_url() {
                return home_url;
            }

            public void setHome_url(String home_url) {
                this.home_url = home_url;
            }

            public String getWho_add() {
                return who_add;
            }

            public void setWho_add(String who_add) {
                this.who_add = who_add;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }
        }
    }
}
