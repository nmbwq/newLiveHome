package shangri.example.com.shangri.model.bean.response;

import java.util.List;

public class LookBannerBean {


    private List<BannersBean> banners;

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public static class BannersBean {
        /**
         * id : 27
         * title : 新手主播如何在直播界站住脚
         * banner_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/banner/2018-09-21/5ba4c0f1e3bff.jpg
         * relation_id : 0
         * relation_url : http://jz.zhibohome.net/api/read/article/web/119
         * article_url : http://jz.zhibohome.net/api/read/article/web/119
         * type : 1
         * browse_amount : 0
         * good_amount : 0
         * collect_amount : 0
         * audio_url :
         * cover_url :
         * register_good : 0
         * register_collect : 0
         */

        private String id;
        private String title;
        private String banner_url;
        private String relation_id;
        private String relation_url;
        private String article_url;
        private String type;
        private int browse_amount;
        private int good_amount;
        private int collect_amount;
        private String audio_url;
        private String cover_url;
        private int register_good;
        private int register_collect;

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

        public int getBrowse_amount() {
            return browse_amount;
        }

        public void setBrowse_amount(int browse_amount) {
            this.browse_amount = browse_amount;
        }

        public int getGood_amount() {
            return good_amount;
        }

        public void setGood_amount(int good_amount) {
            this.good_amount = good_amount;
        }

        public int getCollect_amount() {
            return collect_amount;
        }

        public void setCollect_amount(int collect_amount) {
            this.collect_amount = collect_amount;
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
    }
}
