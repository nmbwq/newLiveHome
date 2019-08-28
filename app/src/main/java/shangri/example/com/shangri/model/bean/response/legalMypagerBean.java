package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * 登陆返回的用户信息
 * Created by chengaofu on 2017/6/21.
 */

public class legalMypagerBean implements Serializable {


    private List<PayOrderBean> pay_order;

    public List<PayOrderBean> getPay_order() {
        return pay_order;
    }

    public void setPay_order(List<PayOrderBean> pay_order) {
        this.pay_order = pay_order;
    }

    public static class PayOrderBean {
        /**
         * id : 10
         * order_serial : 472018053010452247F1
         * order_pay : 1000000395672444
         * order_no : 47F1
         * uid : 47
         * pay_cate : 1
         * package_type : 10
         * residue_num : 10
         * price : 290.00
         * pay_type : 3
         * create_time : 1527648322
         * status : 2
         */

        private int id;
        private String order_serial;
        private String order_pay;
        private String order_no;
        private int uid;
        private int pay_cate;
        private int package_type;
        private int residue_num;
        private String price;
        private int pay_type;
        private String create_time;
        private int status;

        public PayOrderBean() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrder_serial() {
            return order_serial;
        }

        public void setOrder_serial(String order_serial) {
            this.order_serial = order_serial;
        }

        public String getOrder_pay() {
            return order_pay;
        }

        public void setOrder_pay(String order_pay) {
            this.order_pay = order_pay;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getPay_cate() {
            return pay_cate;
        }

        public void setPay_cate(int pay_cate) {
            this.pay_cate = pay_cate;
        }

        public int getPackage_type() {
            return package_type;
        }

        public void setPackage_type(int package_type) {
            this.package_type = package_type;
        }

        public int getResidue_num() {
            return residue_num;
        }

        public void setResidue_num(int residue_num) {
            this.residue_num = residue_num;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}



