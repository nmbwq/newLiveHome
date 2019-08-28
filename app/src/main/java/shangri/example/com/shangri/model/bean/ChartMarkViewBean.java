package shangri.example.com.shangri.model.bean;

/**
 * Created by Administrator on 2018/8/3.
 */

public class ChartMarkViewBean {
    String date="";
    String Markdate="";
    String symbol="";
    //纵坐标值  解决大数据Inter以后乱码  出现e问题
    String y="";

    public ChartMarkViewBean(String date, String markdate, String symbol) {
        this.date = date;
        Markdate = markdate;
        this.symbol = symbol;
    }

    public ChartMarkViewBean(String date, String markdate, String symbol, String y) {
        this.date = date;
        Markdate = markdate;
        this.symbol = symbol;
        this.y = y;
    }

    public ChartMarkViewBean(String y) {
        this.y = y;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public ChartMarkViewBean() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMarkdate() {
        return Markdate;
    }

    public void setMarkdate(String markdate) {
        Markdate = markdate;
    }
}
