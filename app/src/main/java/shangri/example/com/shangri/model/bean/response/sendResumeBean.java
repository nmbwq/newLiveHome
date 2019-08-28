package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * 登陆返回的用户信息
 * Created by chengaofu on 2017/6/21.
 */

public class sendResumeBean implements Serializable {


    private List<ResumeBean> resume;

    public List<ResumeBean> getResume() {
        return resume;
    }

    public void setResume(List<ResumeBean> resume) {
        this.resume = resume;
    }

    public static class ResumeBean implements Serializable {
        /**
         * id : 590
         * job_method : 2
         * job_plat : 1,6,5
         * anchor_type : 2
         * salary_type : 3
         * welfare : ["主持","公司氛围好","移动互联网","领导nice","年度旅游","美女如云","地铁周边","带薪年假","免费零食"]
         * plat_name : [{"plat_name":"虎牙直播"},{"plat_name":"红人直播"},{"plat_name":"UP直播"}]
         * type_name : 户外直播
         * send_number : 3
         * isNotRead : 3
         */

        private int id;
        private int hot;
        private int job_method;
        private String job_plat;
        private String anchor_type;
        private int salary_type;
        private String type_name;
        private int send_number;
        private int isNotRead;
        public String title;

        private List<String> welfare;
        private List<PlatNameBean> plat_name;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getHot() {
            return hot;
        }

        public void setHot(int hot) {
            this.hot = hot;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public int getSend_number() {
            return send_number;
        }

        public void setSend_number(int send_number) {
            this.send_number = send_number;
        }

        public int getIsNotRead() {
            return isNotRead;
        }

        public void setIsNotRead(int isNotRead) {
            this.isNotRead = isNotRead;
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
    }

}



