package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class LookMeCompanyBean implements Serializable {

    /**
     * current_page : 1
     * total_page : 1
     * company_list : [{"id":"3","registrant_id":"2","company_name":"0哦哦哦婆婆","company_short_name":"空","company_scale":"100-500人","anchor_scale":"326","location":"河北省-石家庄市-桥西区","logo":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-11-09/5be55fcbbcc2d.jpg","telephone":"18758587574","company_description":"万一我让我搜狗莫名还一直哦","home_url":"http://www.baidu.com","who_add":"2","create_time":"0","update_time":"0","type":"1","operate_num":"20","view_time":"1543645098","token":"URJoaEJiIJr4W4QvCwcP"},{"id":"177","registrant_id":"769","company_name":"觉得的","company_short_name":"哦哦么得","company_scale":"500人以上","anchor_scale":"63333","location":"未设置","logo":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-12-01/5c022f4ce356a.jpg","telephone":"15929242645","company_description":"我摸","home_url":"","who_add":"1","create_time":"0","update_time":"0","type":"1","operate_num":"20","view_time":"1543647263","token":"V5Q501ZC1B50ZB4odDY0eByKdO5D0yDL1OBEDZlbOlk1BOYdzy"}]
     */

    private String current_page;
    private int total_page;
    private List<CompanyListBean> company_list;

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

    public List<CompanyListBean> getCompany_list() {
        return company_list;
    }

    public void setCompany_list(List<CompanyListBean> company_list) {
        this.company_list = company_list;
    }

    public static class CompanyListBean {
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
         * who_add : 2
         * create_time : 0
         * update_time : 0
         * type : 1
         * operate_num : 20
         * view_time : 1543645098
         * token : URJoaEJiIJr4W4QvCwcP
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
        private String type;
        private String operate_num;
        private String view_time;
        private String token;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOperate_num() {
            return operate_num;
        }

        public void setOperate_num(String operate_num) {
            this.operate_num = operate_num;
        }

        public String getView_time() {
            return view_time;
        }

        public void setView_time(String view_time) {
            this.view_time = view_time;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}



