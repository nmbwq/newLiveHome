package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class SearchBean implements Serializable {


    private List<AdminBean> admin;

    public List<AdminBean> getAdmin() {
        return admin;
    }

    public void setAdmin(List<AdminBean> admin) {
        this.admin = admin;
    }

    public static class AdminBean {
        /**
         * guild_id : 1233005924
         * guild_name : 菠萝街直播（Mu）
         * icon_url : http://cdn1.tooohappy.com/icon/2017-12-11/151298472448674197.jpg
         * sort : 1
         */

        private String guild_id;
        private String guild_name;
        private String icon_url;
        private int sort;
        int registrants_id;

        public int getRegistrants_id() {
            return registrants_id;
        }

        public void setRegistrants_id(int registrants_id) {
            this.registrants_id = registrants_id;
        }

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

        public String getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(String icon_url) {
            this.icon_url = icon_url;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }

}



