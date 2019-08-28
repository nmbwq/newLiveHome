package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class CollectBean implements Serializable {

    /**
     * collects : [{"id":"8","question":"【精品】下播以后怎么和粉丝守护去沟通维护？","answer":"直播是让粉丝能与主播更好的沟通线下维护至关重要，线下维护要与粉丝做朋友平时多关心，多聊天也可以再其生日或者节日送上自己的一份小礼物让其知道你们是真正得朋友。","browse_amount":"46890","good_amount":"3238","bad_amount":"0","collect_amount":"772","isgood":1,"isbad":0,"register_collect":1},{"id":"17","question":"刚开播前没有粉丝怎么办？","answer":"对于刚开播可分享直播给粉丝朋友，也可以自娱自乐，讲一些搞笑段子以及唱歌来缓解没人的尴尬。","browse_amount":"103207","good_amount":"7125","bad_amount":"3","collect_amount":"1734","isgood":1,"isbad":1,"register_collect":1},{"id":"15","question":"直播中遇到粉丝与粉丝争吵如何及时处理？","answer":"遇到这种情况要第一时间劝阻双方以避免更多的口水战，为了不影响直播间气氛私下要和双方多沟通多劝阻以安抚双方为目的。","browse_amount":"77628","good_amount":"5362","bad_amount":"1","collect_amount":"1297","isgood":1,"isbad":0,"register_collect":1}]
     * current_page : 1
     * total_page : 1
     */

    private String current_page;
    private int total_page;
    private List<CollectsBean> collects;

    public String getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(String current_page) {
        this.current_page = current_page;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public List<CollectsBean> getCollects() {
        return collects;
    }

    public void setCollects(List<CollectsBean> collects) {
        this.collects = collects;
    }

    public static class CollectsBean {
        /**
         * id : 8
         * question : 【精品】下播以后怎么和粉丝守护去沟通维护？
         * answer : 直播是让粉丝能与主播更好的沟通线下维护至关重要，线下维护要与粉丝做朋友平时多关心，多聊天也可以再其生日或者节日送上自己的一份小礼物让其知道你们是真正得朋友。
         * browse_amount : 46890
         * good_amount : 3238
         * bad_amount : 0
         * collect_amount : 772
         * isgood : 1
         * isbad : 0
         * register_collect : 1
         */
//            问答数据
        private String id;
        private String question;
        private String answer;
        private String browse_amount;
        private String good_amount;
        private String bad_amount;
        private String collect_amount;
        private int isgood;
        private int isbad;
        private int register_collect;

        //头条数据
        private String style;
        private String title;
        private String audio_url;
        private String cover_url;
        private String keywords;
        private int type;
        private String article_url;
        private int register_good;

        //
        private String pay_low;
        private String pay_high;
        private String job_method;
        private String job_plat;
        private String anchor_type;
        private String salary_type;
        private String work_position;
        private String work_address;
        private String company;
        private String hot;
        private String info_url;
        private List<String> welfare;
        private List<BossDataBean.ListBean.DataBean.PlatNameBean> plat_name;
        private String type_name;
        private String is_collect;
        private String keep_pay;
        private String contact_phone;
        //1是已沟通  0是没有沟通
        private int is_linkup;
        //        发布类型 1系统（爬取的）2 官方（手动发布）
        private int publish_type;
        //        是否VIP 1是 0否
        private int is_vip;
        //1 正常 2审核中 3审核失败 4已关闭
        private int status;

        private String qq;
        private String weixin;
        private String email;

        private int is_ggw;



        public int getIs_ggw() {
            return is_ggw;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setIs_ggw(int is_ggw) {
            this.is_ggw = is_ggw;
        }

        public int getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(int is_vip) {
            this.is_vip = is_vip;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

        public int getPublish_type() {
            return publish_type;
        }

        public void setPublish_type(int publish_type) {
            this.publish_type = publish_type;
        }

        public int getIs_linkup() {
            return is_linkup;
        }

        public void setIs_linkup(int is_linkup) {
            this.is_linkup = is_linkup;
        }

        public String getContact_phone() {
            return contact_phone;
        }

        public void setContact_phone(String contact_phone) {
            this.contact_phone = contact_phone;
        }

        public String getKeep_pay() {
            return keep_pay;
        }

        public void setKeep_pay(String keep_pay) {
            this.keep_pay = keep_pay;
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

        public List<BossDataBean.ListBean.DataBean.PlatNameBean> getPlat_name() {
            return plat_name;
        }

        public void setPlat_name(List<BossDataBean.ListBean.DataBean.PlatNameBean> plat_name) {
            this.plat_name = plat_name;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(String is_collect) {
            this.is_collect = is_collect;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAudio_url() {
            return audio_url;
        }

        public void setAudio_url(String audio_url) {
            this.audio_url = audio_url;
        }

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getArticle_url() {
            return article_url;
        }

        public void setArticle_url(String article_url) {
            this.article_url = article_url;
        }

        public int getRegister_good() {
            return register_good;
        }

        public void setRegister_good(int register_good) {
            this.register_good = register_good;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getBrowse_amount() {
            return browse_amount;
        }

        public void setBrowse_amount(String browse_amount) {
            this.browse_amount = browse_amount;
        }

        public String getGood_amount() {
            return good_amount;
        }

        public void setGood_amount(String good_amount) {
            this.good_amount = good_amount;
        }

        public String getBad_amount() {
            return bad_amount;
        }

        public void setBad_amount(String bad_amount) {
            this.bad_amount = bad_amount;
        }

        public String getCollect_amount() {
            return collect_amount;
        }

        public void setCollect_amount(String collect_amount) {
            this.collect_amount = collect_amount;
        }

        public int getIsgood() {
            return isgood;
        }

        public void setIsgood(int isgood) {
            this.isgood = isgood;
        }

        public int getIsbad() {
            return isbad;
        }

        public void setIsbad(int isbad) {
            this.isbad = isbad;
        }

        public int getRegister_collect() {
            return register_collect;
        }

        public void setRegister_collect(int register_collect) {
            this.register_collect = register_collect;
        }
    }
}

