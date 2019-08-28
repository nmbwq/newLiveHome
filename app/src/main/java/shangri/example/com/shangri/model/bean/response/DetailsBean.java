package shangri.example.com.shangri.model.bean.response;

import java.util.List;

public class DetailsBean {


    /**
     * current_page : 1
     * total_page : 1
     * bills : [{"id":"28","register_id":"388","title":"提现","type":"2","operate_num":"12","apply_id":"17","nickname":null,"create_time":"1541660440","status":"1","status_des":""},{"id":"27","register_id":"388","title":"提现","type":"2","operate_num":"12","apply_id":"16","nickname":null,"create_time":"1541660440","status":"1","status_des":""},{"id":"26","register_id":"388","title":"提现","type":"2","operate_num":"12","apply_id":"15","nickname":null,"create_time":"1541660440","status":"1","status_des":""},{"id":"25","register_id":"388","title":"提现","type":"2","operate_num":"12","apply_id":"14","nickname":null,"create_time":"1541660439","status":"1","status_des":""},{"id":"23","register_id":"388","title":"提现","type":"2","operate_num":"12","apply_id":"12","nickname":null,"create_time":"1541660419","status":"1","status_des":""},{"id":"22","register_id":"388","title":"提现","type":"2","operate_num":"12","apply_id":"11","nickname":null,"create_time":"1541660415","status":"1","status_des":""},{"id":"12","register_id":"388","title":"邀请好友","type":"1","operate_num":"2","apply_id":"0","nickname":"未编辑","create_time":"1541649343","status":0,"status_des":""},{"id":"8","register_id":"388","title":"提现","type":"2","operate_num":"10","apply_id":"6","nickname":null,"create_time":"1541644328","status":"2","status_des":""},{"id":"7","register_id":"388","title":"提现","type":"2","operate_num":"20","apply_id":"5","nickname":null,"create_time":"1541643690","status":"2","status_des":""},{"id":"6","register_id":"388","title":"提现","type":"2","operate_num":"30","apply_id":"4","nickname":null,"create_time":"1541580433","status":"3","status_des":"帐号错误"},{"id":"3","register_id":"388","title":"上传简历","type":"1","operate_num":"5","apply_id":"0","nickname":null,"create_time":"1541577663","status":0,"status_des":""}]
     */

    private int current_page;
    private int total_page;
    private List<BillsBean> bills;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public List<BillsBean> getBills() {
        return bills;
    }

    public void setBills(List<BillsBean> bills) {
        this.bills = bills;
    }

    public static class BillsBean {
        /**
         * id : 28
         * register_id : 388
         * title : 提现
         * type : 2
         * operate_num : 12
         * apply_id : 17
         * nickname : null
         * create_time : 1541660440
         * status : 1
         * status_des :
         */

        private String id;
        private String register_id;
        private String title;
        private String type;
        private String operate_num;
        private String apply_id;
        private String nickname;
        private String create_time;
        private String status;
        private String status_des;

        private String company;
        //        是否过期 1是 0否
        private int is_expire;

        public int getIs_expire() {
            return is_expire;
        }

        public void setIs_expire(int is_expire) {
            this.is_expire = is_expire;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRegister_id() {
            return register_id;
        }

        public void setRegister_id(String register_id) {
            this.register_id = register_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getApply_id() {
            return apply_id;
        }

        public void setApply_id(String apply_id) {
            this.apply_id = apply_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatus_des() {
            return status_des;
        }

        public void setStatus_des(String status_des) {
            this.status_des = status_des;
        }
    }
}
