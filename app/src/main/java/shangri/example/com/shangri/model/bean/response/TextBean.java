package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class TextBean implements Serializable {


    /**
     * code : 0
     * message : success
     * data : {"platfrom":[],"guild":[],"anchor":[{"id":"4","tag":"类型|台类|型标","platfrom_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-23/5addc4e09536f.jpg","guild_id":"13562456","anchor_name":"昵称","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png","show_location":"7","platfrom_name":"所在平台","anchor_url":"http://test.ilivehome.net/api/read/wiki/web/4/type/3","tags":["类型","台类","型标"],"anchor_collect":0},{"id":"3","tag":"平台|平台|主播","platfrom_url":"","guild_id":"13562456","anchor_name":"昵称","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png","show_location":"6","platfrom_name":"所在平台","anchor_url":"http://test.ilivehome.net/api/read/wiki/web/3/type/3","tags":["平台","平台","主播"],"anchor_collect":0},{"id":"2","tag":"台型|台型|主签","platfrom_url":"","guild_id":"13562456","anchor_name":"昵称","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png","show_location":"5","platfrom_name":"所在平台","anchor_url":"http://test.ilivehome.net/api/read/wiki/web/2/type/3","tags":["台型","台型","主签"],"anchor_collect":0},{"id":"1","tag":"平台|平台|主播","platfrom_url":"","guild_id":"13562456","anchor_name":"昵称","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png","show_location":"4","platfrom_name":"所在平台","anchor_url":"http://test.ilivehome.net/api/read/wiki/web/1/type/3","tags":["平台","平台","主播"],"anchor_collect":0}]}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<?> platfrom;
        private List<?> guild;
        private List<AnchorBean> anchor;

        public List<?> getPlatfrom() {
            return platfrom;
        }

        public void setPlatfrom(List<?> platfrom) {
            this.platfrom = platfrom;
        }

        public List<?> getGuild() {
            return guild;
        }

        public void setGuild(List<?> guild) {
            this.guild = guild;
        }

        public List<AnchorBean> getAnchor() {
            return anchor;
        }

        public void setAnchor(List<AnchorBean> anchor) {
            this.anchor = anchor;
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
}

