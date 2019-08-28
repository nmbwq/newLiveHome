package shangri.example.com.shangri.model.bean.response;

import java.util.List;

/**
 * Created by admin on 2017/12/25.
 */

public class BossTongGaoBean {
    /**
     * is_resume : 0
     * conscribe : [{"id":1,"title":"网络红人招募","f_title":"600元/天","salary_type":1,"job_method":2,"anchor_type":"二次元","live_for":"每隔2小时推广一次！","popularize":"2018.09.26-2018.10.27","time_quan":"18:00-24:00","content":"在直播间上面添加相关游戏下载图文，由广告主提供教程，会培训您的直播方式，让您吸引到更多的粉丝！"}]
     */
    private int is_resume;
    private List<ConscribeBean> conscribe;

    public int getIs_resume() {
        return is_resume;
    }

    public void setIs_resume(int is_resume) {
        this.is_resume = is_resume;
    }

    public List<ConscribeBean> getConscribe() {
        return conscribe;
    }

    public void setConscribe(List<ConscribeBean> conscribe) {
        this.conscribe = conscribe;
    }

    public static class ConscribeBean {
        /**
         * id : 1
         * title : 网络红人招募
         * f_title : 600元/天
         * salary_type : 1
         * job_method : 2
         * anchor_type : 二次元
         * live_for : 每隔2小时推广一次！
         * popularize : 2018.09.26-2018.10.27
         * time_quan : 18:00-24:00
         * content : 在直播间上面添加相关游戏下载图文，由广告主提供教程，会培训您的直播方式，让您吸引到更多的粉丝！
         */

        private int id;
        private String title;
        private String f_title;
        private int salary_type;
        private int job_method;
        private String anchor_type;
        private String live_for;
        private String popularize;
        private String time_quan;
        private String content;

        private String anchor_from;
        private String is_status;
        private String is_apply;

        private String apply_url;

        public String getApply_url() {
            return apply_url;
        }

        public void setApply_url(String apply_url) {
            this.apply_url = apply_url;
        }

        public String getAnchor_from() {
            return anchor_from;
        }

        public void setAnchor_from(String anchor_from) {
            this.anchor_from = anchor_from;
        }

        public String getIs_status() {
            return is_status;
        }

        public void setIs_status(String is_status) {
            this.is_status = is_status;
        }

        public String getIs_apply() {
            return is_apply;
        }

        public void setIs_apply(String is_apply) {
            this.is_apply = is_apply;
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

        public String getF_title() {
            return f_title;
        }

        public void setF_title(String f_title) {
            this.f_title = f_title;
        }

        public int getSalary_type() {
            return salary_type;
        }

        public void setSalary_type(int salary_type) {
            this.salary_type = salary_type;
        }

        public int getJob_method() {
            return job_method;
        }

        public void setJob_method(int job_method) {
            this.job_method = job_method;
        }

        public String getAnchor_type() {
            return anchor_type;
        }

        public void setAnchor_type(String anchor_type) {
            this.anchor_type = anchor_type;
        }

        public String getLive_for() {
            return live_for;
        }

        public void setLive_for(String live_for) {
            this.live_for = live_for;
        }

        public String getPopularize() {
            return popularize;
        }

        public void setPopularize(String popularize) {
            this.popularize = popularize;
        }

        public String getTime_quan() {
            return time_quan;
        }

        public void setTime_quan(String time_quan) {
            this.time_quan = time_quan;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
