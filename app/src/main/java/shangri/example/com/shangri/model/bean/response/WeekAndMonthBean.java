package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class WeekAndMonthBean implements Serializable {

    public String month;
    public String week;
    // 首次进去 现在的周或是现在的月   已选择时间再次从上个界面传过来的 月 或是周
    public boolean isNow;
    public String StartTime;
    public String EndTime;
    public  boolean IsClick;

    public boolean isClick() {
        return IsClick;
    }

    public void setClick(boolean click) {
        IsClick = click;
    }

    public WeekAndMonthBean() {
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public boolean isNow() {
        return isNow;
    }

    public void setNow(boolean now) {
        isNow = now;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }
}


