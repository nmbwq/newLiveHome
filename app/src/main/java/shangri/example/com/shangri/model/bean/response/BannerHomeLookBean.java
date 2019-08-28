package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public class BannerHomeLookBean implements Serializable {

    /**
     * banners : [{"id":"20","title":"新手主播常见的误区","banner_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/banner/2017-12-28/151445602489857141.jpg","relation_id":"28","relation_url":"http://www.zhibohome.net/api/train/app/28","article_url":"http://www.zhibohome.net/api/train/app/28","audio_url":"http://zhibohome-audio-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-03/5a4c6f5cf40c8.mp3","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-03/5a4cbebf06767.jpg"},{"id":"21","title":"主播开播要注意化妆、吸粉与调设备等各项细节","banner_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/banner/2017-12-29/15145367544251635.jpg","relation_id":"44","relation_url":"http://www.zhibohome.net/api/article/app/44","article_url":"http://www.zhibohome.net/api/article/app/44","audio_url":"","cover_url":""},{"id":"22","title":"新手主播秘笈之脸皮厚，心要大，自嗨，遮面出镜","banner_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/banner/2017-12-29/151453683239666271.jpg","relation_id":"31","relation_url":"http://www.zhibohome.net/api/train/app/31","article_url":"http://www.zhibohome.net/api/train/app/31","audio_url":"http://zhibohome-audio-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-03/5a4c9022e8893.mp3","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-03/5a4c902330461.jpg"}]
     * catagorys : [{"id":"1","name":"新手攻略"},{"id":"2","name":"粉丝维护"},{"id":"3","name":"直播播单"}]
     * trains : [{"id":"37","style":"1","title":"小白引导第九课\u2014\u2014户外直播设备（上）","audio_url":"","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-10/5a55b784272cb.jpg","browse_amount":"3","good_amount":"2","create_time":"2018年01月10日","publish_time":"1515566980","keywords":"小白|引导引导|","collect_amount":"0","article_url":"http://test.zhibohome.net/api/read/train/web/37","register_good":0},{"id":"33","style":"2","title":"小白引导6\u2014\u2014如何选择公会","audio_url":"http://zhibohome-audio-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-04/5a4deafe0849b.mp3","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-04/5a4deafe40317.jpg","browse_amount":"3297","good_amount":"231","create_time":"2018年01月04日","publish_time":"1515056046","keywords":"公会选择|小白引导|","collect_amount":"0","article_url":"http://test.zhibohome.net/api/read/train/web/33","register_good":0},{"id":"32","style":"2","title":"小白引导5--浅谈直播公会","audio_url":"http://zhibohome-audio-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-04/5a4d972f7434e.mp3","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-04/5a4deba452f21.jpg","browse_amount":"4139","good_amount":"311","create_time":"2018年01月04日","publish_time":"1515056036","keywords":"小白引导|公会|","collect_amount":"0","article_url":"http://test.zhibohome.net/api/read/train/web/32","register_good":0},{"id":"29","style":"2","title":"小白引导--新手主播开播前应该了解的基本细节","audio_url":"http://zhibohome-audio-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-03/5a4c75c4daf65.mp3","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-03/5a4cc19bc5061.jpg","browse_amount":"7835","good_amount":"556","create_time":"2018年01月03日","publish_time":"1514979739","keywords":"直播设备|土豪玩家|化妆","collect_amount":"0","article_url":"http://test.zhibohome.net/api/read/train/web/29","register_good":0},{"id":"28","style":"2","title":"小白引导--新手主播常见的三个误区","audio_url":"http://zhibohome-audio-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-03/5a4c6f5cf40c8.mp3","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-03/5a4cbebf06767.jpg","browse_amount":"7927","good_amount":"563","create_time":"2018年01月03日","publish_time":"1514979007","keywords":"误区|新手主播|","collect_amount":"0","article_url":"http://test.zhibohome.net/api/read/train/web/28","register_good":0},{"id":"8","style":"2","title":"新手主播一定要多注意自己在直播间的直播效果","audio_url":"","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-03/5a4cbb7e34536.jpg","browse_amount":"42079","good_amount":"3896","create_time":"2018年01月03日","publish_time":"1514978174","keywords":"直播|技巧|","collect_amount":"0","article_url":"http://test.zhibohome.net/api/read/train/web/8","register_good":0},{"id":"31","style":"2","title":"小白引导--新手主播直播秘笈","audio_url":"http://zhibohome-audio-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-03/5a4c9022e8893.mp3","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-03/5a4c902330461.jpg","browse_amount":"7507","good_amount":"534","create_time":"2018年01月03日","publish_time":"1514967075","keywords":"自黑|心大|拉拢粉丝","collect_amount":"0","article_url":"http://test.zhibohome.net/api/read/train/web/31","register_good":0},{"id":"30","style":"2","title":"小白引导--如何选择直播平台（下）","audio_url":"http://zhibohome-audio-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-03/5a4c8846c0b59.mp3","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-03/5a4c884706a40.jpg","browse_amount":"7597","good_amount":"541","create_time":"2018年01月03日","publish_time":"1514965063","keywords":"直播平台|直播类型|经济公司","collect_amount":"0","article_url":"http://test.zhibohome.net/api/read/train/web/30","register_good":0},{"id":"27","style":"2","title":"小白引导--如何选择直播平台（上）","audio_url":"http://zhibohome-audio-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-03/5a4c35465496a.mp3","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-03/5a4c35468cd81.jpg","browse_amount":"8672","good_amount":"618","create_time":"2018年01月03日","publish_time":"1514943814","keywords":"平台选择|新手主播|","collect_amount":"0","article_url":"http://test.zhibohome.net/api/read/train/web/27","register_good":0},{"id":"26","style":"2","title":"小白引导--告诉你什么是网络直播","audio_url":"http://zhibohome-audio-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-02/5a4b6c2cea9b4.mp3","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-02/5a4b6c2d2a527.jpg","browse_amount":"11242","good_amount":"794","create_time":"2018年01月02日","publish_time":"1514892333","keywords":"小白引导|直播概述|","collect_amount":"0","article_url":"http://test.zhibohome.net/api/read/train/web/26","register_good":0}]
     * current_page : 1
     * total_page : 2
     */
    int num;
    private String current_page;
    private int total_page;
    private List<BannersBean> banners;
    private List<CatagorysBean> catagorys;
    private List<TrainsBean> trains;


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

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

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public List<CatagorysBean> getCatagorys() {
        return catagorys;
    }

    public void setCatagorys(List<CatagorysBean> catagorys) {
        this.catagorys = catagorys;
    }

    public List<TrainsBean> getTrains() {
        return trains;
    }

    public void setTrains(List<TrainsBean> trains) {
        this.trains = trains;
    }

    public static class BannersBean {
        /**
         * id : 20
         * title : 新手主播常见的误区
         * banner_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/banner/2017-12-28/151445602489857141.jpg
         * relation_id : 28
         * relation_url : http://www.zhibohome.net/api/train/app/28
         * article_url : http://www.zhibohome.net/api/train/app/28
         * audio_url : http://zhibohome-audio-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-03/5a4c6f5cf40c8.mp3
         * cover_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-03/5a4cbebf06767.jpg
         */

        private String id;
        private String title;
        private String banner_url;
        private String relation_id;
        private String relation_url;
        private String article_url;
        private String audio_url;
        private String cover_url;

        private String browse_amount;
        private String good_amount;
        private String collect_amount;
        private int register_good;
        private int  register_collect;

        public int getRegister_good() {
            return register_good;
        }

        public void setRegister_good(int register_good) {
            this.register_good = register_good;
        }

        public int getRegister_collect() {
            return register_collect;
        }

        public void setRegister_collect(int register_collect) {
            this.register_collect = register_collect;
        }

        public String getBrowse_amount() {
            return browse_amount;
        }

        public void setBrowse_amount(String browse_amount) {
            this.browse_amount = browse_amount;
        }

        public String getGood_amount() {
            return good_amount;
        }

        public void setGood_amount(String good_amount) {
            this.good_amount = good_amount;
        }

        public String getCollect_amount() {
            return collect_amount;
        }

        public void setCollect_amount(String collect_amount) {
            this.collect_amount = collect_amount;
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

        public String getAudio_url() {
            return audio_url;
        }

        public void setAudio_url(String audio_url) {
            this.audio_url = audio_url;
        }

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
        }
    }

    public static class CatagorysBean {
        /**
         * id : 1
         * name : 新手攻略
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class TrainsBean {
        /**
         * id : 37
         * style : 1
         * title : 小白引导第九课——户外直播设备（上）
         * audio_url :
         * cover_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-10/5a55b784272cb.jpg
         * browse_amount : 3
         * good_amount : 2
         * create_time : 2018年01月10日
         * publish_time : 1515566980
         * keywords : 小白|引导引导|
         * collect_amount : 0
         * article_url : http://test.zhibohome.net/api/read/train/web/37
         * register_good : 0
         */

        private String id;
        private String style;
        private String title;
        private String audio_url;
        private String cover_url;
        private String browse_amount;
        private String good_amount;
        private String create_time;
        private String publish_time;
        private String keywords;
        private String collect_amount;
        private String article_url;
        private int register_good;
        private int register_collect;

        public int getRegister_collect() {
            return register_collect;
        }

        public void setRegister_collect(int register_collect) {
            this.register_collect = register_collect;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAudio_url() {
            return audio_url;
        }

        public void setAudio_url(String audio_url) {
            this.audio_url = audio_url;
        }

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
        }

        public String getBrowse_amount() {
            return browse_amount;
        }

        public void setBrowse_amount(String browse_amount) {
            this.browse_amount = browse_amount;
        }

        public String getGood_amount() {
            return good_amount;
        }

        public void setGood_amount(String good_amount) {
            this.good_amount = good_amount;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getPublish_time() {
            return publish_time;
        }

        public void setPublish_time(String publish_time) {
            this.publish_time = publish_time;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getCollect_amount() {
            return collect_amount;
        }

        public void setCollect_amount(String collect_amount) {
            this.collect_amount = collect_amount;
        }

        public String getArticle_url() {
            return article_url;
        }

        public void setArticle_url(String article_url) {
            this.article_url = article_url;
        }

        public int getRegister_good() {
            return register_good;
        }

        public void setRegister_good(int register_good) {
            this.register_good = register_good;
        }
    }

}

