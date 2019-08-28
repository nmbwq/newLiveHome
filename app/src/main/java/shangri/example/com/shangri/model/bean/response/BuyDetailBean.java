package shangri.example.com.shangri.model.bean.response;

import java.util.List;

/**
 * Description: 购买明细
 * Data：2018/11/7-10:52
 * Author: lin
 */
public class BuyDetailBean {
    List<Detail> detail;

    public List<Detail> getDetail() {
        return detail;
    }

    public void setDetail(List<Detail> detail) {
        this.detail = detail;
    }

    public class Detail{
        String price;
        String pay_type;
        String package_num;
        String create_time;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getPackage_num() {
            return package_num;
        }

        public void setPackage_num(String package_num) {
            this.package_num = package_num;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
