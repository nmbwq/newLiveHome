package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/6.
 */

public class DaysPriceBean implements Serializable {


    /**
     * priceone : 2
     * pricethree : 8
     * pricesix : 2
     */

    private String priceone;
    private String pricethree;
    private String pricesix;

    public String getPriceone() {
        return priceone;
    }

    public void setPriceone(String priceone) {
        this.priceone = priceone;
    }

    public String getPricethree() {
        return pricethree;
    }

    public void setPricethree(String pricethree) {
        this.pricethree = pricethree;
    }

    public String getPricesix() {
        return pricesix;
    }

    public void setPricesix(String pricesix) {
        this.pricesix = pricesix;
    }
}
