package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * 登陆返回的用户信息
 * Created by chengaofu on 2017/6/21.
 */

public class anchorDetailBean implements Serializable {


    /**
     * recruit : {"id":"1025","title":"","pay_low":"4000","pay_high":"9000","keep_pay":"5000","company":"小方","company_scale":"","job":"","job_nature":"","contact_person":"","contact_phone":"","phone_address":"","weixin":"","recruit_users":"","education":"","work_years":"","work_position":"深圳","work_address":"","welfare":["五险一金","双薪","加班补助"],"sex":"","job_description":"乌鸦嘴名字 异域舞娘","company_description":"<pre>你马上无意中下沙西 洗洗醒增 女性某自由行4我<\/pre>","user_title":"","email":"","issue_time":"","publish_time":"1539332905","source_web":"","source_url":"","status":"1","job_method":"2","job_plat":"2","anchor_type":"13","salary_type":"3","hot":"0","publish_type":"1","share_click":"1","lightspot":"能行","delete_time":"0","qq":"","create_id":"2","check_time":"1539332956","check_number_real":"126","check_number_run":"3000","plat_name":[{"plat_name":"YY直播"}],"type_name":"电商直播8","info_url":"http://test.ilivehome.net/api/read/recruit/detail/1025","anchor_send_resume":4,"share_number":0}
     * userInfo : [{"resume_message_id":"67","recruit_id":"1025","anchor_id":"47","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-09-27/5bac93a9c4411.jpg","nickname":"活动活动","is_read":"1","telephone":"","resume_telephone":"13185025851","resume_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/resume_phone/2018-10-12/5bc0c13baa06b.jpg"},{"resume_message_id":"54","recruit_id":"1025","anchor_id":"504","avatar_url":"","nickname":"尹军","is_read":"1","telephone":"15639167157","resume_telephone":"15639167157","resume_url":""},{"resume_message_id":"55","recruit_id":"1025","anchor_id":"504","avatar_url":"","nickname":"尹军","is_read":"1","telephone":"","resume_telephone":"15639167157","resume_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/resume_phone/2018-10-12/5bc0bed4348be.jpg"},{"resume_message_id":"63","recruit_id":"1025","anchor_id":"506","avatar_url":"","nickname":"减肥季节成就奖","is_read":"1","telephone":"","resume_telephone":"15700173371","resume_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/resume_phone/2018-10-12/5bc0c017d6214.jpg"}]
     */

    private RecruitBean recruit;
    private List<UserInfoBean> userInfo;

    public RecruitBean getRecruit() {
        return recruit;
    }

    public void setRecruit(RecruitBean recruit) {
        this.recruit = recruit;
    }

    public List<UserInfoBean> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<UserInfoBean> userInfo) {
        this.userInfo = userInfo;
    }

    public static class RecruitBean {
        /**
         * id : 1025
         * title :
         * pay_low : 4000
         * pay_high : 9000
         * keep_pay : 5000
         * company : 小方
         * company_scale :
         * job :
         * job_nature :
         * contact_person :
         * contact_phone :
         * phone_address :
         * weixin :
         * recruit_users :
         * education :
         * work_years :
         * work_position : 深圳
         * work_address :
         * welfare : ["五险一金","双薪","加班补助"]
         * sex :
         * job_description : 乌鸦嘴名字 异域舞娘
         * company_description : <pre>你马上无意中下沙西 洗洗醒增 女性某自由行4我</pre>
         * user_title :
         * email :
         * issue_time :
         * publish_time : 1539332905
         * source_web :
         * source_url :
         * status : 1
         * job_method : 2
         * job_plat : 2
         * anchor_type : 13
         * salary_type : 3
         * hot : 0
         * publish_type : 1
         * share_click : 1
         * lightspot : 能行
         * delete_time : 0
         * qq :
         * create_id : 2
         * check_time : 1539332956
         * check_number_real : 126
         * check_number_run : 3000
         * plat_name : [{"plat_name":"YY直播"}]
         * type_name : 电商直播8
         * info_url : http://test.ilivehome.net/api/read/recruit/detail/1025
         * anchor_send_resume : 4
         * share_number : 0
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
        private String type_name;
        private String info_url;
        private int is_ggwl;


        private int anchor_send_resume;
        private int share_number;
        private  String check_mark;

        public String getCheck_mark() {
            return check_mark;
        }

        public void setCheck_mark(String check_mark) {
            this.check_mark = check_mark;
        }

        private List<String> welfare;
        private List<PositionListBean.ListBean.DataBean.PlatNameBean> plat_name;

        public int getIs_ggwl() {
            return is_ggwl;
        }

        public void setIs_ggwl(int is_ggwl) {
            this.is_ggwl = is_ggwl;
        }

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

        public int getAnchor_send_resume() {
            return anchor_send_resume;
        }

        public void setAnchor_send_resume(int anchor_send_resume) {
            this.anchor_send_resume = anchor_send_resume;
        }

        public int getShare_number() {
            return share_number;
        }

        public void setShare_number(int share_number) {
            this.share_number = share_number;
        }

        public List<String> getWelfare() {
            return welfare;
        }

        public void setWelfare(List<String> welfare) {
            this.welfare = welfare;
        }

        public List<PositionListBean.ListBean.DataBean.PlatNameBean> getPlat_name() {
            return plat_name;
        }

        public void setPlat_name(List<PositionListBean.ListBean.DataBean.PlatNameBean> plat_name) {
            this.plat_name = plat_name;
        }

    }

    public static class UserInfoBean {
        /**
         * resume_message_id : 67
         * recruit_id : 1025
         * anchor_id : 47
         * avatar_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-09-27/5bac93a9c4411.jpg
         * nickname : 活动活动
         * is_read : 1
         * telephone :
         * resume_telephone : 13185025851
         * resume_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/resume_phone/2018-10-12/5bc0c13baa06b.jpg
         */

        private String resume_message_id;
        private String recruit_id;
        private String anchor_id;

        private String resume_id;
        private String avatar_url;
        private String nickname;
        private String is_read;
        private String telephone;
        private String resume_telephone;
        private String resume_url;
        private String info_url;
        private String img_url;


        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getResume_id() {
            return resume_id;
        }

        public void setResume_id(String resume_id) {
            this.resume_id = resume_id;
        }

        public String getInfo_url() {
            return info_url;
        }

        public void setInfo_url(String info_url) {
            this.info_url = info_url;
        }

        public String getResume_message_id() {
            return resume_message_id;
        }

        public void setResume_message_id(String resume_message_id) {
            this.resume_message_id = resume_message_id;
        }

        public String getRecruit_id() {
            return recruit_id;
        }

        public void setRecruit_id(String recruit_id) {
            this.recruit_id = recruit_id;
        }

        public String getAnchor_id() {
            return anchor_id;
        }

        public void setAnchor_id(String anchor_id) {
            this.anchor_id = anchor_id;
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

        public String getIs_read() {
            return is_read;
        }

        public void setIs_read(String is_read) {
            this.is_read = is_read;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getResume_telephone() {
            return resume_telephone;
        }

        public void setResume_telephone(String resume_telephone) {
            this.resume_telephone = resume_telephone;
        }

        public String getResume_url() {
            return resume_url;
        }

        public void setResume_url(String resume_url) {
            this.resume_url = resume_url;
        }
    }
}



