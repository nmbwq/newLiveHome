package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class companyDetailBean implements Serializable {


    /**
     * code : 0
     * message : 获取成功
     * data : {"id":4,"registrant_id":2,"company_name":"YY 测试 公司阿斯达斯的","company_scale":"100-500","location":"浙江 杭州","logo":"","guilds":[{"guild_id":"1234548255","guild_name":"菠萝街（coco）"},{"guild_id":"650418055593","guild_name":"UP（猫咪）"},{"guild_id":"323ruyuwenhua","guild_name":"一直播（猫咪）"},{"guild_id":"53425479107","guild_name":"小米（猫咪）"},{"guild_id":"432418055593","guild_name":"NOW（猫咪）"},{"guild_id":"665418055593","guild_name":"火山（猫咪）"},{"guild_id":"8861563916768","guild_name":"测试电文"}]}
     */




        private int id;
        private int registrant_id;
        private String company_name;
        private String company_scale;
        private String location;
        private String logo;
        private List<GuildsBean> guilds;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRegistrant_id() {
            return registrant_id;
        }

        public void setRegistrant_id(int registrant_id) {
            this.registrant_id = registrant_id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getCompany_scale() {
            return company_scale;
        }

        public void setCompany_scale(String company_scale) {
            this.company_scale = company_scale;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public List<GuildsBean> getGuilds() {
            return guilds;
        }

        public void setGuilds(List<GuildsBean> guilds) {
            this.guilds = guilds;
        }

        public static class GuildsBean {
            /**
             * guild_id : 1234548255
             * guild_name : 菠萝街（coco）
             */

            private String guild_id;
            private String guild_name;
            private  int  status;

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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
        }

}



