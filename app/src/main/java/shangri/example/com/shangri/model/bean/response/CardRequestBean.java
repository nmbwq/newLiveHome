package shangri.example.com.shangri.model.bean.response;

import java.util.List;

import shangri.example.com.shangri.model.bean.request.BaseBeen;

/**
 *
 * Created by chengaofu on 2017/6/21.
 */

public class CardRequestBean extends BaseBeen {


    /**
     * avatar_url :
     * nickname : 13958040540
     * telephone : 18758587574
     * sex : M
     * guilds : [{"guild_id":"1234548255","guild_name":"菠萝街（coco）","uid":"7443730","anchor_name":"1988"}]
     */
    private String avatar_url;
    private String nickname;
    private String telephone;
    private String sex;
    private String register_role;

    public String getRegister_role() {
        return register_role;
    }

    public void setRegister_role(String register_role) {
        this.register_role = register_role;
    }

    private List<GuildsBean> guilds;

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
         * uid : 7443730
         * anchor_name : 1988
         */

        private String guild_id;
        private String guild_name;
        private String uid;
        private String anchor_name;

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

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getAnchor_name() {
            return anchor_name;
        }

        public void setAnchor_name(String anchor_name) {
            this.anchor_name = anchor_name;
        }
    }

}


