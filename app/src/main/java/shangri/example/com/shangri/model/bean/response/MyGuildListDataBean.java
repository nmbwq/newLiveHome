package shangri.example.com.shangri.model.bean.response;

import java.util.List;

import shangri.example.com.shangri.model.bean.request.BaseBeen;

/**
 * Created by Administrator on 2017/12/30.
 */

public class MyGuildListDataBean extends BaseBeen {

    private List<GuildsBean> guilds;
    private List<GuildsBean> quick_gulid;

    public List<GuildsBean> getQuick_gulid() {
        return quick_gulid;
    }

    public void setQuick_gulid(List<GuildsBean> quick_gulid) {
        this.quick_gulid = quick_gulid;
    }

    public List<GuildsBean> getGuilds() {
        return guilds;
    }

    public void setGuilds(List<GuildsBean> guilds) {
        this.guilds = guilds;
    }

    public static class GuildsBean {
        /**
         * id : 35
         * guild_name : 小米（猫咪）
         * platfrom_name : 小米直播
         * check_status : 1
         * check_mark :
         * icon_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2017-12-11/151298468491184578.jpg
         */

        private String id;
        private String guild_name;
        private String guild_id;

        private String platfrom_name;
        private String check_status;
        private String check_mark;
        private String icon_url;
        private String platfrom_id;
        private String member_time;
        private String guild_ratio;


        public String getPlatfrom_id() {
            return platfrom_id;
        }

        public void setPlatfrom_id(String platfrom_id) {
            this.platfrom_id = platfrom_id;
        }

        public String getGuild_id() {
            return guild_id;
        }

        public void setGuild_id(String guild_id) {
            this.guild_id = guild_id;
        }

        public String getGuild_ratio() {
            return guild_ratio;
        }

        public void setGuild_ratio(String guild_ratio) {
            this.guild_ratio = guild_ratio;
        }

        public String getMember_time() {
            return member_time;
        }

        public void setMember_time(String member_time) {
            this.member_time = member_time;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGuild_name() {
            return guild_name;
        }

        public void setGuild_name(String guild_name) {
            this.guild_name = guild_name;
        }

        public String getPlatfrom_name() {
            return platfrom_name;
        }

        public void setPlatfrom_name(String platfrom_name) {
            this.platfrom_name = platfrom_name;
        }

        public String getCheck_status() {
            return check_status;
        }

        public void setCheck_status(String check_status) {
            this.check_status = check_status;
        }

        public String getCheck_mark() {
            return check_mark;
        }

        public void setCheck_mark(String check_mark) {
            this.check_mark = check_mark;
        }

        public String getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(String icon_url) {
            this.icon_url = icon_url;
        }
    }

}
