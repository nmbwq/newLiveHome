package shangri.example.com.shangri.model.bean.response;

import java.util.List;

public class TrainingArticleBean {


    private List<TrainsBean> trains;

    public List<TrainsBean> getTrains() {
        return trains;
    }

    public void setTrains(List<TrainsBean> trains) {
        this.trains = trains;
    }

    public static class TrainsBean {
        /**
         * id : 5
         * name : 主播化妆技巧系列
         * img_url : http://cdn1.tooohappy.com/article/2017-11-22/15113426265271183.jpg
         * list : [{"train_id":"1","style":"2","title":"新手主播一定要适当的撒娇才会惹人爱","audio_url":"","cover_url":"http://cdn1.tooohappy.com/article/2017-12-21/151384771831358381.jpg","browse_amount":"33500","good_amount":"3278","create_time":"2017年12月21日","publish_time":"1513847718","keywords":"直播|技巧|","collect_amount":"0","article_url":"http://www.zhibohome.net/training/#/","register_good":0,"register_collect":0},{"train_id":"2","style":"2","title":"新手主播秘笈之脸皮厚，心要大,自嗨，遮面出镜","audio_url":"http://zhibohome-audio-bucket.oss-cn-qingdao.aliyuncs.com/article/2017-12-28/15144245722894379.mp3","cover_url":"http://cdn1.tooohappy.com/article/2017-11-23/151140158492159560.jpg","browse_amount":"34584","good_amount":"3365","create_time":"2017年12月28日","publish_time":"1514424573","keywords":"直播|技巧|","collect_amount":"0","article_url":"http://www.zhibohome.net/training/#/","register_good":1,"register_collect":0},{"train_id":"3","style":"1","title":"当主播最忌讳的这三点，千万别犯！","audio_url":"","cover_url":"http://cdn1.tooohappy.com/article/2017-11-23/151140163394196826.jpg","browse_amount":"59","good_amount":"3","create_time":"2017年12月11日","publish_time":"1512972379","keywords":"||","collect_amount":"0","article_url":"http://www.zhibohome.net/training/#/","register_good":1,"register_collect":0}]
         */

        private String id;
        private String name;
        private String img_url;
        private List<ListBean> list;

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

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * train_id : 1
             * style : 2
             * title : 新手主播一定要适当的撒娇才会惹人爱
             * audio_url :
             * cover_url : http://cdn1.tooohappy.com/article/2017-12-21/151384771831358381.jpg
             * browse_amount : 33500
             * good_amount : 3278
             * create_time : 2017年12月21日
             * publish_time : 1513847718
             * keywords : 直播|技巧|
             * collect_amount : 0
             * article_url : http://www.zhibohome.net/training/#/
             * register_good : 0
             * register_collect : 0
             */

            private String train_id;
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

            public String getTrain_id() {
                return train_id;
            }

            public void setTrain_id(String train_id) {
                this.train_id = train_id;
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

            public int getRegister_collect() {
                return register_collect;
            }

            public void setRegister_collect(int register_collect) {
                this.register_collect = register_collect;
            }
        }
    }
}
