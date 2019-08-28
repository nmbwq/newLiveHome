package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/3.
 */

public class PatrolDataBean implements Serializable {

    /**
     * current_page : 1
     * total_page : 1
     * inspects : [{"id":"4","creater_id":"2","guild_name":"菠萝街（coco）","platfrom_name":"菠萝街直播","member_time":"","memner_status":"1","anchor_name":"🛡盾上加鹿🦌","comment":"Qwqwqwqwq","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497090394139368.jpg","good_tags_ids":"19,17","bad_tags_ids":"30,28","photo_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-05-08/5af11d7edacb8.jpg,http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-05-08/5af11d7f8122e.jpg,http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-05-08/5af11d7fa31dd.jpg","audio_url":"http://zhibohome-audio-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-05-08/5af11d7fc5dbd.mp3","is_alert":1,"inspect_date":"2018-05-08","create_date":"2018-05-08 11:46","good_tags":["主动出击、展现自已","思维敏捷、语言幽默"],"bad_tags":["内容过于单一","好高骛远  傲慢不逊"],"read":[],"no_read":[{"id":"47","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497090394139368.jpg","is_read":"0"}],"type":1,"creater_avatar":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2017-12-19/151367073957785799.jpg"},{"id":"2","creater_id":"2","guild_name":"菠萝街（coco）","platfrom_name":"菠萝街直播","member_time":"","memner_status":"1","anchor_name":"1988","comment":"少装逼","avatar_url":"","good_tags_ids":"17,19","bad_tags_ids":"5","photo_url":"","audio_url":"","is_alert":"0","inspect_date":"2018-01-03","create_date":"2018-01-03 18:57","good_tags":["主动出击、展现自已","思维敏捷、语言幽默"],"bad_tags":["有点冷场"],"read":[],"no_read":[],"type":1,"creater_avatar":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2017-12-19/151367073957785799.jpg"},{"id":"1","creater_id":"2","guild_name":"菠萝街（coco）","platfrom_name":"菠萝街直播","member_time":"","memner_status":"1","anchor_name":"大大-","comment":"人挺帅","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497506759846958.jpg","good_tags_ids":"4,6","bad_tags_ids":"28","photo_url":"","audio_url":"","is_alert":"0","inspect_date":"2018-01-03","create_date":"2018-01-03 18:56","good_tags":["直播气氛好","话题不错"],"bad_tags":["内容过于单一"],"read":[],"no_read":[],"type":1,"creater_avatar":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2017-12-19/151367073957785799.jpg"}]
     */
    private int exist;

    public int getExist() {
        return exist;
    }

    public void setExist(int exist) {
        this.exist = exist;
    }

    private String current_page;
    private int total_page;
    private List<InspectsBean> inspects;

    public String getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(String current_page) {
        this.current_page = current_page;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public List<InspectsBean> getInspects() {
        return inspects;
    }

    public void setInspects(List<InspectsBean> inspects) {
        this.inspects = inspects;
    }

    public static class InspectsBean implements Serializable {
        /**
         * id : 4
         * creater_id : 2
         * guild_name : 菠萝街（coco）
         * platfrom_name : 菠萝街直播
         * member_time :
         * memner_status : 1
         * anchor_name : 🛡盾上加鹿🦌
         * comment : Qwqwqwqwq
         * avatar_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497090394139368.jpg
         * good_tags_ids : 19,17
         * bad_tags_ids : 30,28
         * photo_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-05-08/5af11d7edacb8.jpg,http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-05-08/5af11d7f8122e.jpg,http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-05-08/5af11d7fa31dd.jpg
         * audio_url : http://zhibohome-audio-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-05-08/5af11d7fc5dbd.mp3
         * is_alert : 1
         * inspect_date : 2018-05-08
         * create_date : 2018-05-08 11:46
         * good_tags : ["主动出击、展现自已","思维敏捷、语言幽默"]
         * bad_tags : ["内容过于单一","好高骛远  傲慢不逊"]
         * read : []
         * no_read : [{"id":"47","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497090394139368.jpg","is_read":"0"}]
         * type : 1
         * creater_avatar : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2017-12-19/151367073957785799.jpg
         */

        private String id;
        private String creater_id;
        private String guild_name;
        private String platfrom_name;
        private String member_time;
        private String memner_status;
        private String anchor_name;
        private String comment;
        private String avatar_url;
        private String good_tags_ids;
        private String bad_tags_ids;
        private String photo_url;
        private String audio_url;
        private int is_alert;
        private String inspect_date;
        private String create_date;
        private int type;
        private String creater_avatar;
        private String creater_name;

        private List<String> good_tags;
        private List<String> bad_tags;
        private List<NoReadBean> read;
        private List<NoReadBean> no_read;
        private int is_read = 0;
        private String audio_time;


        public String getCreater_name() {
            return creater_name;
        }

        public void setCreater_name(String creater_name) {
            this.creater_name = creater_name;
        }

        public String getAudio_time() {
            return audio_time;
        }

        public void setAudio_time(String audio_time) {
            this.audio_time = audio_time;
        }

        public int getIs_read() {
            return is_read;
        }

        public void setIs_read(int is_read) {
            this.is_read = is_read;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreater_id() {
            return creater_id;
        }

        public void setCreater_id(String creater_id) {
            this.creater_id = creater_id;
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

        public String getMember_time() {
            return member_time;
        }

        public void setMember_time(String member_time) {
            this.member_time = member_time;
        }

        public String getMemner_status() {
            return memner_status;
        }

        public void setMemner_status(String memner_status) {
            this.memner_status = memner_status;
        }

        public String getAnchor_name() {
            return anchor_name;
        }

        public void setAnchor_name(String anchor_name) {
            this.anchor_name = anchor_name;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getGood_tags_ids() {
            return good_tags_ids;
        }

        public void setGood_tags_ids(String good_tags_ids) {
            this.good_tags_ids = good_tags_ids;
        }

        public String getBad_tags_ids() {
            return bad_tags_ids;
        }

        public void setBad_tags_ids(String bad_tags_ids) {
            this.bad_tags_ids = bad_tags_ids;
        }

        public String getPhoto_url() {
            return photo_url;
        }

        public void setPhoto_url(String photo_url) {
            this.photo_url = photo_url;
        }

        public String getAudio_url() {
            return audio_url;
        }

        public void setAudio_url(String audio_url) {
            this.audio_url = audio_url;
        }

        public int getIs_alert() {
            return is_alert;
        }

        public void setIs_alert(int is_alert) {
            this.is_alert = is_alert;
        }

        public String getInspect_date() {
            return inspect_date;
        }

        public void setInspect_date(String inspect_date) {
            this.inspect_date = inspect_date;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCreater_avatar() {
            return creater_avatar;
        }

        public void setCreater_avatar(String creater_avatar) {
            this.creater_avatar = creater_avatar;
        }

        public List<String> getGood_tags() {
            return good_tags;
        }

        public void setGood_tags(List<String> good_tags) {
            this.good_tags = good_tags;
        }

        public List<String> getBad_tags() {
            return bad_tags;
        }

        public void setBad_tags(List<String> bad_tags) {
            this.bad_tags = bad_tags;
        }

        public List<NoReadBean> getRead() {
            return read;
        }

        public void setRead(List<NoReadBean> read) {
            this.read = read;
        }

        public List<NoReadBean> getNo_read() {
            return no_read;
        }

        public void setNo_read(List<NoReadBean> no_read) {
            this.no_read = no_read;
        }

        public static class NoReadBean implements Serializable {
            /**
             * id : 47
             * avatar_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497090394139368.jpg
             * is_read : 0
             */

            private String id;
            private String avatar_url;
            private String is_read;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public String getIs_read() {
                return is_read;
            }

            public void setIs_read(String is_read) {
                this.is_read = is_read;
            }
        }
    }
}

