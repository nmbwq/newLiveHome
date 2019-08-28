package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/2/1.
 */

public class MyPlatfromList implements Serializable {

    private List<PlatfromsBean> platfroms;

    public List<PlatfromsBean> getPlatfroms() {
        return platfroms;
    }

    public void setPlatfroms(List<PlatfromsBean> platfroms) {
        this.platfroms = platfroms;
    }

    public static class PlatfromsBean {
        /**
         * id : 1
         * platfrom_name : 哈哈
         * guild_name : 呵呵
         * anchor_content : 不要太多
         * cover_url :
         */

        private String id;
        private String platfrom_name;
        private String guild_name;
        private String anchor_content;
        private String cover_url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPlatfrom_name() {
            return platfrom_name;
        }

        public void setPlatfrom_name(String platfrom_name) {
            this.platfrom_name = platfrom_name;
        }

        public String getGuild_name() {
            return guild_name;
        }

        public void setGuild_name(String guild_name) {
            this.guild_name = guild_name;
        }

        public String getAnchor_content() {
            return anchor_content;
        }

        public void setAnchor_content(String anchor_content) {
            this.anchor_content = anchor_content;
        }

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
        }

    }
}
