package shangri.example.com.shangri.model.bean.response;

import java.util.List;

/**
 * Description:VIP套餐列表
 * Data：2018/11/22-11:12
 * Author: lin
 */
public class VIPCardBean {

    List<Packages> packages;

    public List<Packages> getPackages() {
        return packages;
    }

    public void setPackages(List<Packages> packages) {
        this.packages = packages;
    }

    public class Packages{
        String id;
        String name;
        String valid_month;
        String price;
        String is_sales;
        String price_sales;
        String expire_time;

        public String getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(String expire_time) {
            this.expire_time = expire_time;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValid_month() {
            return valid_month;
        }

        public void setValid_month(String valid_month) {
            this.valid_month = valid_month;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getIs_sales() {
            return is_sales;
        }

        public void setIs_sales(String is_sales) {
            this.is_sales = is_sales;
        }

        public String getPrice_sales() {
            return price_sales;
        }

        public void setPrice_sales(String price_sales) {
            this.price_sales = price_sales;
        }
    }
}
