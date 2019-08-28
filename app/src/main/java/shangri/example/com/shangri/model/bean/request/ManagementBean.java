package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/7.
 */

public class ManagementBean extends BaseBeen {
    private String end_date;
    private String start_date;
    private String guild_id;
    private String time_slot;
    private String time_span;
    private String chart_type;
    private String page;
    private String quick;
    private String table_flag;

    public String getQuick() {
        return quick;
    }

    public void setQuick(String quick) {
        this.quick = quick;
    }

    public String getTable_flag() {
        return table_flag;
    }

    public void setTable_flag(String table_flag) {
        this.table_flag = table_flag;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getChart_type() {
        return chart_type;
    }

    public void setChart_type(String chart_type) {
        this.chart_type = chart_type;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getGuild_id() {
        return guild_id;
    }

    public void setGuild_id(String guild_id) {
        this.guild_id = guild_id;
    }

    public String getTime_slot() {
        return time_slot;
    }

    public void setTime_slot(String time_slot) {
        this.time_slot = time_slot;
    }

    public String getTime_span() {
        return time_span;
    }

    public void setTime_span(String time_span) {
        this.time_span = time_span;
    }
}
