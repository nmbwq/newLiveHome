package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/4.
 */

public class ChoiceGuildBean implements Serializable{
        private List<GuildsBean> guilds;

        public List<GuildsBean> getGuilds() {
            return guilds;
        }

        public void setGuilds(List<GuildsBean> guilds) {
            this.guilds = guilds;
        }

        public static class GuildsBean {
            public GuildsBean(String guild_name) {
                this.guild_name = guild_name;
            }

            public GuildsBean(String guild_id, String guild_name) {
                this.guild_id = guild_id;
                this.guild_name = guild_name;
            }

            /**
             * guild_id : 1234548255
             * guild_name : 菠萝街（coco）
             * platfrom_name : 菠萝街直播
             */


            private String guild_id;
            private String guild_name;
            private String platfrom_name;
            private Boolean flag;


            public Boolean getFlag() {
                return flag;
            }

            public void setFlag(Boolean flag) {
                this.flag = flag;
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

            public String getPlatfrom_name() {
                return platfrom_name;
            }

            public void setPlatfrom_name(String platfrom_name) {
                this.platfrom_name = platfrom_name;
            }
        }
}
