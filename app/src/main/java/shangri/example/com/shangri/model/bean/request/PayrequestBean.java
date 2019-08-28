package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/3/6.
 */

public class PayrequestBean extends BaseBeen {

    private String package_type;
    private String price;
    private String pay_type;
    private String from;
    private String  package_num;

    public String getPackage_num() {
        return package_num;
    }

    public void setPackage_num(String package_num) {
        this.package_num = package_num;
    }

    //下面是法务支付需要的参数
    private String  pay_cate;

    public String getPay_cate() {
        return pay_cate;
    }

    public void setPay_cate(String pay_cate) {
        this.pay_cate = pay_cate;
    }

    public String getPackage_type() {
        return package_type;
    }

    public void setPackage_type(String package_type) {
        this.package_type = package_type;
    }

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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
