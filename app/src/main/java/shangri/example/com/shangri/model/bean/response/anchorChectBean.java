package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class anchorChectBean implements Serializable {


    private List<ApplysBean> applys;

    public List<ApplysBean> getApplys() {
        return applys;
    }

    public void setApplys(List<ApplysBean> applys) {
        this.applys = applys;
    }

    public static class ApplysBean {
        /**
         * guild_name : NOW（猫咪）
         * platfrom_name : NOW直播
         * anchor_uid : 208297519
         * anchor_name : 宋妍瑾
         * avatar_url :
         * check_status : 0
         * register_guild_id : 30
         * check_mark :
         * anchor_nickname : 哦哦
         */

        private String guild_name;
        private String platfrom_name;
        private String anchor_uid;
        private String anchor_name;
        private String avatar_url;
        private String check_status;
        private String register_guild_id;
        private String check_mark;
        private String anchor_nickname;

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

        public String getAnchor_uid() {
            return anchor_uid;
        }

        public void setAnchor_uid(String anchor_uid) {
            this.anchor_uid = anchor_uid;
        }

        public String getAnchor_name() {
            return anchor_name;
        }

        public void setAnchor_name(String anchor_name) {
            this.anchor_name = anchor_name;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getCheck_status() {
            return check_status;
        }

        public void setCheck_status(String check_status) {
            this.check_status = check_status;
        }

        public String getRegister_guild_id() {
            return register_guild_id;
        }

        public void setRegister_guild_id(String register_guild_id) {
            this.register_guild_id = register_guild_id;
        }

        public String getCheck_mark() {
            return check_mark;
        }

        public void setCheck_mark(String check_mark) {
            this.check_mark = check_mark;
        }

        public String getAnchor_nickname() {
            return anchor_nickname;
        }

        public void setAnchor_nickname(String anchor_nickname) {
            this.anchor_nickname = anchor_nickname;
        }
    }

}



