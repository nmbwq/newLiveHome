package shangri.example.com.shangri.model.bean.response;

public class AddCompanyBean {


    /**
     * company : {"id":"3","registrant_id":"2","company_name":"0哦哦哦婆婆","company_short_name":"空","company_scale":"100-500人","anchor_scale":"326","location":"河北省-石家庄市-桥西区","logo":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-11-09/5be55fcbbcc2d.jpg","telephone":"18758587574","company_description":"万一我让我搜狗莫名还一直哦","home_url":"http://www.baidu.com","who_add":"1","is_perfect":1,"is_linkup":0,"residue_num":"0","is_issue":"1"}
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
         * company_name : 0哦哦哦婆婆
         * company_short_name : 空
         * company_scale : 100-500人
         * anchor_scale : 326
         * location : 河北省-石家庄市-桥西区
         * logo : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-11-09/5be55fcbbcc2d.jpg
         * telephone : 18758587574
         * company_description : 万一我让我搜狗莫名还一直哦
         * home_url : http://www.baidu.com
         * who_add : 1
         * is_perfect : 1
         * is_linkup : 0
         * residue_num : 0
         * is_issue : 1
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
        private int is_perfect;
        private int is_linkup;
        private String residue_num;
        private String is_issue;

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

        public int getIs_perfect() {
            return is_perfect;
        }

        public void setIs_perfect(int is_perfect) {
            this.is_perfect = is_perfect;
        }

        public int getIs_linkup() {
            return is_linkup;
        }

        public void setIs_linkup(int is_linkup) {
            this.is_linkup = is_linkup;
        }

        public String getResidue_num() {
            return residue_num;
        }

        public void setResidue_num(String residue_num) {
            this.residue_num = residue_num;
        }

        public String getIs_issue() {
            return is_issue;
        }

        public void setIs_issue(String is_issue) {
            this.is_issue = is_issue;
        }
    }
}
