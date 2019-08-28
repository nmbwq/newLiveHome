package shangri.example.com.shangri.model.bean.response;

public class ResShareBean {

    /**
     * guild_id : 323ruyuwenhua
     * guild_name : 一直播（猫咪）
     * platfrom_name : 一直播
     * icon_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2017-12-13/151314337041127286.jpg
     * month : {"month":"2018-04","anchor_count":"12","inspect_count":"5","inspect_anchor":"3","live_time":"0.00"}
     * pre_month : {"month":"2018-03","anchor_count":"20","inspect_count":"1","inspect_anchor":"1","live_time":"0.00"}
     * mask : 确认过眼神，是公会中的法拉利，继续加油，让其他公会闻你的尾气吧！
     */
    private String guild_id;
    private String guild_name;
    private String platfrom_name;
    private String icon_url;
    private MonthBean month;
    private PreMonthBean pre_month;
    private String mask;

    public String getGuild_id() {
        return guild_id;
    }

    public void setGuild_id(String guild_id) {
        this.guild_id = guild_id;
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

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public MonthBean getMonth() {
        return month;
    }

    public void setMonth(MonthBean month) {
        this.month = month;
    }

    public PreMonthBean getPre_month() {
        return pre_month;
    }

    public void setPre_month(PreMonthBean pre_month) {
        this.pre_month = pre_month;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public static class MonthBean {
        /**
         * month : 2018-04
         * anchor_count : 12
         * inspect_count : 5
         * inspect_anchor : 3
         * live_time : 0.00
         */

        private String month;
        private String anchor_count;
        private String inspect_count;
        private String inspect_anchor;
        private String live_time;

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getAnchor_count() {
            return anchor_count;
        }

        public void setAnchor_count(String anchor_count) {
            this.anchor_count = anchor_count;
        }

        public String getInspect_count() {
            return inspect_count;
        }

        public void setInspect_count(String inspect_count) {
            this.inspect_count = inspect_count;
        }

        public String getInspect_anchor() {
            return inspect_anchor;
        }

        public void setInspect_anchor(String inspect_anchor) {
            this.inspect_anchor = inspect_anchor;
        }

        public String getLive_time() {
            return live_time;
        }

        public void setLive_time(String live_time) {
            this.live_time = live_time;
        }
    }

    public static class PreMonthBean {
        /**
         * month : 2018-03
         * anchor_count : 20
         * inspect_count : 1
         * inspect_anchor : 1
         * live_time : 0.00
         */

        private String month;
        private String anchor_count;
        private String inspect_count;
        private String inspect_anchor;
        private String live_time;

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getAnchor_count() {
            return anchor_count;
        }

        public void setAnchor_count(String anchor_count) {
            this.anchor_count = anchor_count;
        }

        public String getInspect_count() {
            return inspect_count;
        }

        public void setInspect_count(String inspect_count) {
            this.inspect_count = inspect_count;
        }

        public String getInspect_anchor() {
            return inspect_anchor;
        }

        public void setInspect_anchor(String inspect_anchor) {
            this.inspect_anchor = inspect_anchor;
        }

        public String getLive_time() {
            return live_time;
        }

        public void setLive_time(String live_time) {
            this.live_time = live_time;
        }
    }

}
