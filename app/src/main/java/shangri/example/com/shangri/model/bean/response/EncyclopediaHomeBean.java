package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public class EncyclopediaHomeBean implements Serializable {


    /**
     * code : 0
     * message : success
     * data : {"platfrom":[{"id":"1","platfrom_name":"平台名称","introduction":"一句话描4","location_tag":"最具影响力","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png","show_location":"1","platfrom_url":"http://test.ilivehome.net/api/read/wiki/web/1/type/1"},{"id":"2","platfrom_name":"平台名称","introduction":"一句话描4","location_tag":"最具影响力","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png","show_location":"2","platfrom_url":"http://test.ilivehome.net/api/read/wiki/web/2/type/1"},{"id":"3","platfrom_name":"平台名称","introduction":"一句话描4","location_tag":"最具影响力","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png","show_location":"3","platfrom_url":"http://test.ilivehome.net/api/read/wiki/web/3/type/1"},{"id":"4","platfrom_name":"平台名称","introduction":"一句话描4","location_tag":"最具影响力","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png","show_location":"4","platfrom_url":"http://test.ilivehome.net/api/read/wiki/web/4/type/1"},{"id":"5","platfrom_name":"平台名称","introduction":"一句话描4","location_tag":"最具影响力","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png","show_location":"5","platfrom_url":"http://test.ilivehome.net/api/read/wiki/web/5/type/1"},{"id":"6","platfrom_name":"平台名称","introduction":"一句话描4","location_tag":"最具影响力","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png","show_location":"6","platfrom_url":"http://test.ilivehome.net/api/read/wiki/web/6/type/1"}],"guild":[{"id":"1","guild_name":"公会名称435","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png","show_location":"1","guild_url":"http://test.ilivehome.net/api/read/wiki/web/1/type/2"},{"id":"2","guild_name":"公会名称435","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png","show_location":"2","guild_url":"http://test.ilivehome.net/api/read/wiki/web/2/type/2"},{"id":"3","guild_name":"公会名称435","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png","show_location":"3","guild_url":"http://test.ilivehome.net/api/read/wiki/web/3/type/2"},{"id":"4","guild_name":"公会名称435","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png","show_location":"4","guild_url":"http://test.ilivehome.net/api/read/wiki/web/4/type/2"}],"anchor":[{"id":"1","anchor_name":"昵称","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png","show_location":"4","anchor_fans":"20000","anchor_url":"http://test.ilivehome.net/api/read/wiki/web/1/type/3"},{"id":"2","anchor_name":"昵称","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png","show_location":"5","anchor_fans":"20000","anchor_url":"http://test.ilivehome.net/api/read/wiki/web/2/type/3"},{"id":"3","anchor_name":"昵称","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png","show_location":"6","anchor_fans":"20000","anchor_url":"http://test.ilivehome.net/api/read/wiki/web/3/type/3"}],"banners":[{"id":"35","title":"要留住粉丝，这三点一定要谨记！","banner_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/banner/2018-04-04/5ac489aa6057c.jpg","relation_id":"48","relation_url":"http://jz.zhibohome.net/api/read/train/web/48","article_url":"http://jz.zhibohome.net/api/read/train/web/48","type":"3"},{"id":"36","title":"要留住粉丝，这三点一定要谨记！","banner_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/banner/2018-04-04/5ac489aa6057c.jpg","relation_id":"48","relation_url":"http://jz.zhibohome.net/api/read/train/web/48","article_url":"http://jz.zhibohome.net/api/read/train/web/48","type":"3"},{"id":"37","title":"要留住粉丝，这三点一定要谨记！","banner_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/banner/2018-04-04/5ac489aa6057c.jpg","relation_id":"48","relation_url":"http://jz.zhibohome.net/api/read/train/web/48","article_url":"http://jz.zhibohome.net/api/read/train/web/48","type":"3"}]}
     */
        private List<PlatfromBean> platfrom;
        private List<GuildBean> guild;
        private List<AnchorBean> anchor;
        private List<BannersBean> banners;

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

        public List<BannersBean> getBanners() {
            return banners;
        }

        public void setBanners(List<BannersBean> banners) {
            this.banners = banners;
        }

        public static class PlatfromBean {
            /**
             * id : 1
             * platfrom_name : 平台名称
             * introduction : 一句话描4
             * location_tag : 最具影响力
             * cover_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png
             * show_location : 1
             * platfrom_url : http://test.ilivehome.net/api/read/wiki/web/1/type/1
             */

            private String id;
            private String platfrom_name;
            private String introduction;
            private String location_tag;
            private String cover_url;
            private String show_location;
            private String platfrom_url;
            private int platfrom_collect;

            public int getPlatfrom_collect() {
                return platfrom_collect;
            }

            public void setPlatfrom_collect(int platfrom_collect) {
                this.platfrom_collect = platfrom_collect;
            }

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

            public String getPlatfrom_url() {
                return platfrom_url;
            }

            public void setPlatfrom_url(String platfrom_url) {
                this.platfrom_url = platfrom_url;
            }
        }

        public static class GuildBean {
            /**
             * id : 1
             * guild_name : 公会名称435
             * cover_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png
             * show_location : 1
             * guild_url : http://test.ilivehome.net/api/read/wiki/web/1/type/2
             */

            private String id;
            private String guild_name;
            private String cover_url;
            private String show_location;
            private String guild_url;
            private int guild_collect;

            public int getGuild_collect() {
                return guild_collect;
            }

            public void setGuild_collect(int guild_collect) {
                this.guild_collect = guild_collect;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getGuild_url() {
                return guild_url;
            }

            public void setGuild_url(String guild_url) {
                this.guild_url = guild_url;
            }
        }

        public static class AnchorBean {
            /**
             * id : 1
             * anchor_name : 昵称
             * cover_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png
             * show_location : 4
             * anchor_fans : 20000
             * anchor_url : http://test.ilivehome.net/api/read/wiki/web/1/type/3
             */

            private String id;
            private String anchor_name;
            private String cover_url;
            private String show_location;
            private String anchor_fans;
            private String anchor_url;
            private int number;
            private int anchor_collect;

            public int getAnchor_collect() {
                return anchor_collect;
            }

            public void setAnchor_collect(int anchor_collect) {
                this.anchor_collect = anchor_collect;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getAnchor_fans() {
                return anchor_fans;
            }

            public void setAnchor_fans(String anchor_fans) {
                this.anchor_fans = anchor_fans;
            }

            public String getAnchor_url() {
                return anchor_url;
            }

            public void setAnchor_url(String anchor_url) {
                this.anchor_url = anchor_url;
            }
        }

        public static class BannersBean {
            /**
             * id : 35
             * title : 要留住粉丝，这三点一定要谨记！
             * banner_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/banner/2018-04-04/5ac489aa6057c.jpg
             * relation_id : 48
             * relation_url : http://jz.zhibohome.net/api/read/train/web/48
             * article_url : http://jz.zhibohome.net/api/read/train/web/48
             * type : 3
             */

            private String id;
            private String title;
            private String banner_url;
            private String relation_id;
            private String relation_url;
            private String article_url;
            private String type;
            private int banners_collect;

            public int getBanners_collect() {
                return banners_collect;
            }

            public void setBanners_collect(int banners_collect) {
                this.banners_collect = banners_collect;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getBanner_url() {
                return banner_url;
            }

            public void setBanner_url(String banner_url) {
                this.banner_url = banner_url;
            }

            public String getRelation_id() {
                return relation_id;
            }

            public void setRelation_id(String relation_id) {
                this.relation_id = relation_id;
            }

            public String getRelation_url() {
                return relation_url;
            }

            public void setRelation_url(String relation_url) {
                this.relation_url = relation_url;
            }

            public String getArticle_url() {
                return article_url;
            }

            public void setArticle_url(String article_url) {
                this.article_url = article_url;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }


