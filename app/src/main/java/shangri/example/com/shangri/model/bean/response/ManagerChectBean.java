package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class ManagerChectBean implements Serializable {


    private List<GuildAdminBean> guild_admin;

    public List<GuildAdminBean> getGuild_admin() {
        return guild_admin;
    }

    public void setGuild_admin(List<GuildAdminBean> guild_admin) {
        this.guild_admin = guild_admin;
    }

    public static class GuildAdminBean {
        /**
         * admin_reg_id : 47
         * nickname : 13185025851
         * guild_id : 650418055593
         * guild_name : UP（猫咪）
         * status : 1
         * mask :
         */

        private int admin_reg_id;
        private String nickname;
        private String guild_id;
        private String guild_name;
        private int status;
        private String mask;
        private String avatar_url;

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public int getAdmin_reg_id() {
            return admin_reg_id;
        }

        public void setAdmin_reg_id(int admin_reg_id) {
            this.admin_reg_id = admin_reg_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMask() {
            return mask;
        }

        public void setMask(String mask) {
            this.mask = mask;
        }
    }

}



