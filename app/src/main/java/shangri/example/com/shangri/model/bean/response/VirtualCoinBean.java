package shangri.example.com.shangri.model.bean.response;

import java.util.List;

/**
 * Description:
 * Data：2018/11/29-15:01
 * Author: lin
 */
public class VirtualCoinBean {
    List<Packages> packages;
    String virtual_coin;

    public String getVirtual_coin() {
        return virtual_coin;
    }

    public void setVirtual_coin(String virtual_coin) {
        this.virtual_coin = virtual_coin;
    }

    public List<Packages> getPackages() {
        return packages;
    }

    public void setPackages(List<Packages> packages) {
        this.packages = packages;
    }

    public static class Packages {

        String id;
        String coin_num;
        String is_sales;
        String name;
        String price;
        String price_sales;
        String price_sales_vip;
        String price_vip;
        String expire_time;

        public String getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(String expire_time) {
            this.expire_time = expire_time;
        }

        boolean isChecked = false;

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCoin_num() {
            return coin_num;
        }

        public void setCoin_num(String coin_num) {
            this.coin_num = coin_num;
        }

        public String getIs_sales() {
            return is_sales;
        }

        public void setIs_sales(String is_sales) {
            this.is_sales = is_sales;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPrice_sales() {
            return price_sales;
        }

        public void setPrice_sales(String price_sales) {
            this.price_sales = price_sales;
        }

        public String getPrice_sales_vip() {
            return price_sales_vip;
        }

        public void setPrice_sales_vip(String price_sales_vip) {
            this.price_sales_vip = price_sales_vip;
        }

        public String getPrice_vip() {
            return price_vip;
        }

        public void setPrice_vip(String price_vip) {
            this.price_vip = price_vip;
        }
    }
}
