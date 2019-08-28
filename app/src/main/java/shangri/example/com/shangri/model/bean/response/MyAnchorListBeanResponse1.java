package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/6.
 */

public class MyAnchorListBeanResponse1 implements Serializable {


    private List<GuildsBean> guilds;
    private List<GuildsBean> quick_guilds;

    public List<GuildsBean> getGuilds() {
        return guilds;
    }

    public void setGuilds(List<GuildsBean> guilds) {
        this.guilds = guilds;
    }

    public List<GuildsBean> getQuick_guilds() {
        return quick_guilds;
    }

    public void setQuick_guilds(List<GuildsBean> quick_guilds) {
        this.quick_guilds = quick_guilds;
    }

    public static class GuildsBean {
        /**
         * guild_name : 菠萝街直播（coco）
         * guild_id : 1234548255
         * check_status : 1
         * table_flag :
         * palt_type : 1
         * icon_url : http://cdn1.tooohappy.com/icon/2017-12-11/151298472448674197.jpg
         * anchors_count : 8
         */

        private String guild_name;
        private String guild_id;
        private String check_status;
        private String table_flag;
        private String palt_type;
        private String icon_url;
        private int anchors_count;

        public String getGuild_name() {
            return guild_name;
        }

        public void setGuild_name(String guild_name) {
            this.guild_name = guild_name;
        }

        public String getGuild_id() {
            return guild_id;
        }

        public void setGuild_id(String guild_id) {
            this.guild_id = guild_id;
        }

        public String getCheck_status() {
            return check_status;
        }

        public void setCheck_status(String check_status) {
            this.check_status = check_status;
        }

        public String getTable_flag() {
            return table_flag;
        }

        public void setTable_flag(String table_flag) {
            this.table_flag = table_flag;
        }

        public String getPalt_type() {
            return palt_type;
        }

        public void setPalt_type(String palt_type) {
            this.palt_type = palt_type;
        }

        public String getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(String icon_url) {
            this.icon_url = icon_url;
        }

        public int getAnchors_count() {
            return anchors_count;
        }

        public void setAnchors_count(int anchors_count) {
            this.anchors_count = anchors_count;
        }
    }

//    public static class QuickGuildsBean {
//        /**
//         * guild_name : 测试迅雷
//         * guild_id : 123111111
//         * check_status : 3
//         * table_flag : xunlei
//         * icon_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2017-12-13/151314337041127286.jpg
//         * anchors_count : 1
//         */
//
//        private String guild_name;
//        private String guild_id;
//        private String check_status;
//        private String table_flag;
//        private String icon_url;
//        private int anchors_count;
//
//        public String getGuild_name() {
//            return guild_name;
//        }
//
//        public void setGuild_name(String guild_name) {
//            this.guild_name = guild_name;
//        }
//
//        public String getGuild_id() {
//            return guild_id;
//        }
//
//        public void setGuild_id(String guild_id) {
//            this.guild_id = guild_id;
//        }
//
//        public String getCheck_status() {
//            return check_status;
//        }
//
//        public void setCheck_status(String check_status) {
//            this.check_status = check_status;
//        }
//
//        public String getTable_flag() {
//            return table_flag;
//        }
//
//        public void setTable_flag(String table_flag) {
//            this.table_flag = table_flag;
//        }
//
//        public String getIcon_url() {
//            return icon_url;
//        }
//
//        public void setIcon_url(String icon_url) {
//            this.icon_url = icon_url;
//        }
//
//        public int getAnchors_count() {
//            return anchors_count;
//        }
//
//        public void setAnchors_count(int anchors_count) {
//            this.anchors_count = anchors_count;
//        }
//    }
}

