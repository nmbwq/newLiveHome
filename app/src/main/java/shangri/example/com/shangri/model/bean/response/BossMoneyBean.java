package shangri.example.com.shangri.model.bean.response;

/**
 * Created by admin on 2017/12/25.
 */

public class BossMoneyBean {
    private int symbol;
    private String name;
    private int low;
    private int high;
    private boolean isfocks;


    public BossMoneyBean() {
    }

    public BossMoneyBean(int symbol, String name, int low, int high, boolean isfocks) {
        this.symbol = symbol;
        this.name = name;
        this.low = low;
        this.high = high;
        this.isfocks = isfocks;
    }

    public int getSymbol() {
        return symbol;
    }

    public void setSymbol(int symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public boolean isIsfocks() {
        return isfocks;
    }

    public void setIsfocks(boolean isfocks) {
        this.isfocks = isfocks;
    }
}
