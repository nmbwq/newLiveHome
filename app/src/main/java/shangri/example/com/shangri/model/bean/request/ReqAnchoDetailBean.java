package shangri.example.com.shangri.model.bean.request;

public class ReqAnchoDetailBean {
    public  String token;
    public  String guild_id;
    public  String anchor_uid;
    public  String end_date;
    public  String start_date;
    public  String time_slot;

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getGuild_id() {
        return guild_id;
    }

    public void setGuild_id(String guild_id) {
        this.guild_id = guild_id;
    }

    public String getAnchor_uid() {
        return anchor_uid;
    }

    public void setAnchor_uid(String anchor_uid) {
        this.anchor_uid = anchor_uid;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getTime_slot() {
        return time_slot;
    }

    public void setTime_slot(String time_slot) {
        this.time_slot = time_slot;
    }

}
