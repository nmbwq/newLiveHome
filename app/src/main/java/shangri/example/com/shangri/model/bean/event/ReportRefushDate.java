package shangri.example.com.shangri.model.bean.event;

/**
 * 浏览数量
 * Created by zuyuli on 2017/7/4.
 */

public class ReportRefushDate {

    public  String type;
    public String guildid;
    public String start_date;
    public String end_date;
    public int time_span;
    public String time_slot;


    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public ReportRefushDate(String type, String guildid, String time_slot, String end_date, int time_span) {
        this.type = type;
        this.guildid = guildid;
        this.time_slot = time_slot;
        this.end_date = end_date;
        this.time_span = time_span;
    }

    public ReportRefushDate(String type, String guildid, String start_date, String end_date) {
        this.type = type;
        this.guildid = guildid;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public int getTime_span() {
        return time_span;

    }

    public void setTime_span(int time_span) {
        this.time_span = time_span;
    }

    public ReportRefushDate() {
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getGuildid() {
        return guildid;
    }

    public void setGuildid(String guildid) {
        this.guildid = guildid;
    }

    public String getTime_slot() {
        return time_slot;
    }

    public void setTime_slot(String time_slot) {
        this.time_slot = time_slot;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }


}
