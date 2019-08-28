package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/9.
 */

public class NewCompanyBean implements Serializable {


    /**
     * company : {"id":3,"registrant_id":2,"company_name":"杭州汇都科技有限公司","company_scale":"","location":"","logo":"","telephone":"15722569965","company_description":"胜多负少d"}
     */

    private CompanyBean company;

    public CompanyBean getCompany() {
        return company;
    }

    public void setCompany(CompanyBean company) {
        this.company = company;
    }

    public static class CompanyBean {
        /**
         * id : 3
         * registrant_id : 2
         * company_name : 杭州汇都科技有限公司
         * company_scale :
         * location :
         * logo :
         * telephone : 15722569965
         * company_description : 胜多负少d
         */


        private int is_perfect;
        private int residue_num;
        private int is_issue;
        private int is_linkup;


        private int id;
        private int registrant_id;
        private String company_name;
        private String company_scale;
        private String location;
        private String logo;
        private String telephone;
        private String company_description;
        private String anchor_scale;
        //        剩余波豆数
        private int bd;
        //        查看一次简历消耗的波豆数
        private int hf_bd;

        //        查看一次简历消耗的波豆数
        private int hf_bd2;
        //        发布职位赠送次数
        private int recruit_num;

        public int getHf_bd2() {
            return hf_bd2;
        }

        public void setHf_bd2(int hf_bd2) {
            this.hf_bd2 = hf_bd2;
        }

        public int getBd() {
            return bd;
        }

        public void setBd(int bd) {
            this.bd = bd;
        }

        public int getHf_bd() {
            return hf_bd;
        }

        public void setHf_bd(int hf_bd) {
            this.hf_bd = hf_bd;
        }

        public int getRecruit_num() {
            return recruit_num;
        }

        public void setRecruit_num(int recruit_num) {
            this.recruit_num = recruit_num;
        }

        public String getAnchor_scale() {
            return anchor_scale;
        }

        public void setAnchor_scale(String anchor_scale) {
            this.anchor_scale = anchor_scale;
        }

        public int getIs_perfect() {
            return is_perfect;
        }

        public void setIs_perfect(int is_perfect) {
            this.is_perfect = is_perfect;
        }

        public int getResidue_num() {
            return residue_num;
        }

        public void setResidue_num(int residue_num) {
            this.residue_num = residue_num;
        }

        public int getIs_issue() {
            return is_issue;
        }

        public void setIs_issue(int is_issue) {
            this.is_issue = is_issue;
        }

        public int getIs_linkup() {
            return is_linkup;
        }

        public void setIs_linkup(int is_linkup) {
            this.is_linkup = is_linkup;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRegistrant_id() {
            return registrant_id;
        }

        public void setRegistrant_id(int registrant_id) {
            this.registrant_id = registrant_id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getCompany_scale() {
            return company_scale;
        }

        public void setCompany_scale(String company_scale) {
            this.company_scale = company_scale;
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
    }
}

