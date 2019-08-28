package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/2/1.
 */

public class EncyclopediaList implements Serializable {


    private List<PlatfromBean> platfrom;
    private List<GuildBean> guild;
    private List<AnchorBean> anchor;

    public List<PlatfromBean> getPlatfrom() {
        return platfrom;
    }

    public void setPlatfrom(List<PlatfromBean> platfrom) {
        this.platfrom = platfrom;
    }

    public List<GuildBean> getGuild() {
        return guild;
    }

    public void setGuild(List<GuildBean> guild) {
        this.guild = guild;
    }

    public List<AnchorBean> getAnchor() {
        return anchor;
    }

    public void setAnchor(List<AnchorBean> anchor) {
        this.anchor = anchor;
    }

    public static class PlatfromBean {
        /**
         * id : 14
         * platfrom_name : 平台名称
         * introduction : 一句话描述1
         * location_tag : 团结一切
         * cover_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-23/5addb18fa02e2.jpg
         * show_location : 10
         * initials : P
         * platfrom_url : http://test.ilivehome.net/api/read/wiki/web/14/type/1
         * platfrom_collect : 1
         */

        private String id;
        private String platfrom_name;
        private String introduction;
        private String location_tag;
        private String cover_url;
        private String show_location;
        private String initials;
        private String platfrom_url;
        private int platfrom_collect;

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

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getLocation_tag() {
            return location_tag;
        }

        public void setLocation_tag(String location_tag) {
            this.location_tag = location_tag;
        }

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
        }

        public String getShow_location() {
            return show_location;
        }

        public void setShow_location(String show_location) {
            this.show_location = show_location;
        }

        public String getInitials() {
            return initials;
        }

        public void setInitials(String initials) {
            this.initials = initials;
        }

        public String getPlatfrom_url() {
            return platfrom_url;
        }

        public void setPlatfrom_url(String platfrom_url) {
            this.platfrom_url = platfrom_url;
        }

        public int getPlatfrom_collect() {
            return platfrom_collect;
        }

        public void setPlatfrom_collect(int platfrom_collect) {
            this.platfrom_collect = platfrom_collect;
        }
    }


    public static class GuildBean {
        /**
         * id : 6
         * introduction : 一句话描述23
         * guild_name : 公会名称435
         * cover_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png
         * show_location : 6
         * location_tag : 标签
         * guild_url : http://test.ilivehome.net/api/read/wiki/web/6/type/2
         * guild_collect : 0
         */

        private String id;
        private String introduction;
        private String guild_name;
        private String cover_url;
        private String show_location;
        private String location_tag;
        private String guild_url;
        private int guild_collect;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getGuild_name() {
            return guild_name;
        }

        public void setGuild_name(String guild_name) {
            this.guild_name = guild_name;
        }

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
        }

        public String getShow_location() {
            return show_location;
        }

        public void setShow_location(String show_location) {
            this.show_location = show_location;
        }

        public String getLocation_tag() {
            return location_tag;
        }

        public void setLocation_tag(String location_tag) {
            this.location_tag = location_tag;
        }

        public String getGuild_url() {
            return guild_url;
        }

        public void setGuild_url(String guild_url) {
            this.guild_url = guild_url;
        }

        public int getGuild_collect() {
            return guild_collect;
        }

        public void setGuild_collect(int guild_collect) {
            this.guild_collect = guild_collect;
        }
    }

    public static class AnchorBean {
        /**
         * id : 4
         * tag : 类型|台类|型标
         * platfrom_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-23/5addc4e09536f.jpg
         * guild_id : 13562456
         * anchor_name : 昵称
         * cover_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png
         * show_location : 7
         * platfrom_name : 所在平台
         * anchor_url : http://test.ilivehome.net/api/read/wiki/web/4/type/3
         * tags : ["类型","台类","型标"]
         * anchor_collect : 0
         */

        private String id;
        private String tag;
        private String platfrom_url;
        private String guild_id;
        private String anchor_name;
        private String cover_url;
        private String show_location;
        private String platfrom_name;
        private String anchor_url;
        private int anchor_collect;
        private List<String> tags;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getPlatfrom_url() {
            return platfrom_url;
        }

        public void setPlatfrom_url(String platfrom_url) {
            this.platfrom_url = platfrom_url;
        }

        public String getGuild_id() {
            return guild_id;
        }

        public void setGuild_id(String guild_id) {
            this.guild_id = guild_id;
        }

        public String getAnchor_name() {
            return anchor_name;
        }

        public void setAnchor_name(String anchor_name) {
            this.anchor_name = anchor_name;
        }

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
        }

        public String getShow_location() {
            return show_location;
        }

        public void setShow_location(String show_location) {
            this.show_location = show_location;
        }

        public String getPlatfrom_name() {
            return platfrom_name;
        }

        public void setPlatfrom_name(String platfrom_name) {
            this.platfrom_name = platfrom_name;
        }

        public String getAnchor_url() {
            return anchor_url;
        }

        public void setAnchor_url(String anchor_url) {
            this.anchor_url = anchor_url;
        }

        public int getAnchor_collect() {
            return anchor_collect;
        }

        public void setAnchor_collect(int anchor_collect) {
            this.anchor_collect = anchor_collect;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }
    }


}

