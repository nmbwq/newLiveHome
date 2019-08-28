package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

import retrofit2.http.PUT;

/**
 * Created by Administrator on 2018/3/6.
 */

public class MyAnchorListBeanResponse implements Serializable {


    /**
     * code : 0
     * message : success
     * data : {"guilds":[{"guild_name":"菠萝街（coco）","guild_id":"1234548255","anchors_count":14,"anchors":[{"anchor_uid":"4565950","anchor_name":"66666666","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497688191041153.jpg"},{"anchor_uid":"7300783","anchor_name":"🛡盾上加鹿🦌","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497090394139368.jpg"},{"anchor_uid":"7443730","anchor_name":"1988","avatar_url":""},{"anchor_uid":"5202247","anchor_name":"大大-","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497506759846958.jpg"},{"anchor_uid":"8933227","anchor_name":"习惯了一个人","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-04/151507391341954725.jpg"},{"anchor_uid":"8925119","anchor_name":"张杨轩儿","avatar_url":""},{"anchor_uid":"11123496","anchor_name":"飘爷","avatar_url":""},{"anchor_uid":"4704350","anchor_name":"我叫小雨吖","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-03-05/5a9ce496668ef.jpg"},{"anchor_uid":"6886478","anchor_name":"栀子求破286万","avatar_url":""},{"anchor_uid":"10243769","anchor_name":"城市虞明","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-03-05/152023859865917359.jpg"},{"anchor_uid":"6203296","anchor_name":"小狐狸","avatar_url":""},{"anchor_uid":"9365032","anchor_name":"梅倪倪倪倪倪倪～","avatar_url":""},{"anchor_uid":"9325396","anchor_name":"你的蓝朋友&","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-03-14/5aa8cf57221e0.jpg"},{"anchor_uid":"11852438","anchor_name":"静静","avatar_url":""}]},{"guild_name":"UP（猫咪）","guild_id":"650418055593","anchors_count":0,"anchors":[]},{"guild_name":"一直播（猫咪）","guild_id":"323ruyuwenhua","anchors_count":0,"anchors":[]},{"guild_name":"小米（猫咪）","guild_id":"53425479107","anchors_count":0,"anchors":[]},{"guild_name":"NOW（猫咪）","guild_id":"432418055593","anchors_count":5,"anchors":[{"anchor_uid":"170183042","anchor_name":"张轩轩","avatar_url":""},{"anchor_uid":"168649890","anchor_name":"葫芦_换号","avatar_url":""},{"anchor_uid":"203787319","anchor_name":"大眼萌娃","avatar_url":""},{"anchor_uid":"374804239","anchor_name":"小影","avatar_url":""},{"anchor_uid":"386837141","anchor_name":"小希希","avatar_url":""}]},{"guild_name":"火山（猫咪）","guild_id":"665418055593","anchors_count":0,"anchors":[]}]}
     */
    private List<GuildsBean> guilds;

    public List<GuildsBean> getGuilds() {
        return guilds;
    }

    public void setGuilds(List<GuildsBean> guilds) {
        this.guilds = guilds;
    }

    public static class GuildsBean {
        /**
         * guild_name : 菠萝街（coco）
         * guild_id : 1234548255
         * anchors_count : 14
         * anchors : [{"anchor_uid":"4565950","anchor_name":"66666666","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497688191041153.jpg"},{"anchor_uid":"7300783","anchor_name":"🛡盾上加鹿🦌","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497090394139368.jpg"},{"anchor_uid":"7443730","anchor_name":"1988","avatar_url":""},{"anchor_uid":"5202247","anchor_name":"大大-","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497506759846958.jpg"},{"anchor_uid":"8933227","anchor_name":"习惯了一个人","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-04/151507391341954725.jpg"},{"anchor_uid":"8925119","anchor_name":"张杨轩儿","avatar_url":""},{"anchor_uid":"11123496","anchor_name":"飘爷","avatar_url":""},{"anchor_uid":"4704350","anchor_name":"我叫小雨吖","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-03-05/5a9ce496668ef.jpg"},{"anchor_uid":"6886478","anchor_name":"栀子求破286万","avatar_url":""},{"anchor_uid":"10243769","anchor_name":"城市虞明","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-03-05/152023859865917359.jpg"},{"anchor_uid":"6203296","anchor_name":"小狐狸","avatar_url":""},{"anchor_uid":"9365032","anchor_name":"梅倪倪倪倪倪倪～","avatar_url":""},{"anchor_uid":"9325396","anchor_name":"你的蓝朋友&","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-03-14/5aa8cf57221e0.jpg"},{"anchor_uid":"11852438","anchor_name":"静静","avatar_url":""}]
         */

        private String guild_name;
        private String guild_id;
        private int anchors_count;
        private List<AnchorsBean> anchors;
        public  Boolean isShow=false;

        public Boolean getShow() {
            return isShow;
        }

        public void setShow(Boolean show) {
            isShow = show;
        }

        public String getGuild_name() {
            return guild_name;
        }

        public void setGuild_name(String guild_name) {
            this.guild_name = guild_name;
        }

        public String getGuild_id() {
            return guild_id;
        }

        public void setGuild_id(String guild_id) {
            this.guild_id = guild_id;
        }

        public int getAnchors_count() {
            return anchors_count;
        }

        public void setAnchors_count(int anchors_count) {
            this.anchors_count = anchors_count;
        }

        public List<AnchorsBean> getAnchors() {
            return anchors;
        }

        public void setAnchors(List<AnchorsBean> anchors) {
            this.anchors = anchors;
        }

        public static class AnchorsBean {
            /**
             * anchor_uid : 4565950
             * anchor_name : 66666666
             * avatar_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497688191041153.jpg
             */

            private String anchor_uid;
            private String anchor_name;
            private String avatar_url;


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
        }
    }
}

