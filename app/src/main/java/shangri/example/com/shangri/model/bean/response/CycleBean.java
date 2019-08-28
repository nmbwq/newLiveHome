package shangri.example.com.shangri.model.bean.response;

import java.util.List;

/**
 * Created by admin on 2017/12/25.
 */

public class CycleBean {
    private String symbol;
    private String name;
    private boolean isfocks;


    public CycleBean() {
    }

    public CycleBean(String symbol, String name, boolean isfocks) {
        this.symbol = symbol;
        this.name = name;
        this.isfocks = isfocks;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsfocks() {
        return isfocks;
    }

    public void setIsfocks(boolean isfocks) {
        this.isfocks = isfocks;
    }
}
