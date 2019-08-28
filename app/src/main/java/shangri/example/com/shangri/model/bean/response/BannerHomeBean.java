package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public class BannerHomeBean implements Serializable{

    /**
     * code : 0
     * message : success
     * data : {"message_count":0,"article_catagorys":[{"id":"1","name":"新手攻略"},{"id":"2","name":"粉丝维护"},{"id":"3","name":"直播研究院"}],"banners":[{"id":"25","title":"哈哈哈哈哈哈哈哈哈","banner_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/banner/2018-01-03/5a4c8ad63f95a.jpg","relation_url":"http://www.zhibohome.net/api/article/app/52"}],"articles":{"current_page":1,"data":[{"id":"62","style":"2","title":"直播行业地区差异：南方有电竞 北方有喊麦","audio_url":null,"cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2017-12-29/5a45e4d334eb0.jpg","browse_amount":"22151","good_amount":"1546","create_time":"3 天前","publish_time":"1514960741","keywords":"直播|新闻|","article_url":"http://www.zhibohome.net/api/article/app/62","register_good":0},{"id":"69","style":"2","title":"2017年中国直播行业规模分析","audio_url":null,"cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-01-03/5a4c706940387.jpg","browse_amount":"544","good_amount":"51","create_time":"3 天前","publish_time":"1514958953","keywords":"2017年报告|直播行业|数据","article_url":"http://www.zhibohome.net/api/article/app/69","register_good":0},{"id":"61","style":"2","title":"娱乐主播需要有才艺，游戏主播需要爱玩游戏","audio_url":null,"cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2017-12-29/5a45e0452ef1e.jpg","browse_amount":"26001","good_amount":"1804","create_time":"2017年12月29日","publish_time":"1514528837","keywords":"直播|技巧|分类","article_url":"http://www.zhibohome.net/api/article/app/61","register_good":0},{"id":"54","style":"2","title":"向大主播学习优雅又机智的收礼物","audio_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2017-12-13/151315444959589483.mp3","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2017-12-29/5a45e00038bfa.jpg","browse_amount":"34905","good_amount":"3358","create_time":"2017年12月29日","publish_time":"1514528768","keywords":"直播|技巧|","article_url":"http://www.zhibohome.net/api/article/app/54","register_good":0},{"id":"50","style":"2","title":"新手主播注意事项-提前打听和了解不同的平台","audio_url":null,"cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2017-12-29/5a45dd6f7f68b.jpg","browse_amount":"34834","good_amount":"3360","create_time":"2017年12月29日","publish_time":"1514528111","keywords":"直播|技巧|","article_url":"http://www.zhibohome.net/api/article/app/50","register_good":0}],"first_page_url":"http://test.zhibohome.net/api/article/index?page=1","from":1,"last_page":1,"last_page_url":"http://test.zhibohome.net/api/article/index?page=1","next_page_url":null,"path":"http://test.zhibohome.net/api/article/index","per_page":10,"prev_page_url":null,"to":5,"total":5}}
     */
        /**
         * message_count : 0
         * article_catagorys : [{"id":"1","name":"新手攻略"},{"id":"2","name":"粉丝维护"},{"id":"3","name":"直播研究院"}]
         * banners : [{"id":"25","title":"哈哈哈哈哈哈哈哈哈","banner_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/banner/2018-01-03/5a4c8ad63f95a.jpg","relation_url":"http://www.zhibohome.net/api/article/app/52"}]
         * articles : {"current_page":1,"data":[{"id":"62","style":"2","title":"直播行业地区差异：南方有电竞 北方有喊麦","audio_url":null,"cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2017-12-29/5a45e4d334eb0.jpg","browse_amount":"22151","good_amount":"1546","create_time":"3 天前","publish_time":"1514960741","keywords":"直播|新闻|","article_url":"http://www.zhibohome.net/api/article/app/62","register_good":0},{"id":"69","style":"2","title":"2017年中国直播行业规模分析","audio_url":null,"cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-01-03/5a4c706940387.jpg","browse_amount":"544","good_amount":"51","create_time":"3 天前","publish_time":"1514958953","keywords":"2017年报告|直播行业|数据","article_url":"http://www.zhibohome.net/api/article/app/69","register_good":0},{"id":"61","style":"2","title":"娱乐主播需要有才艺，游戏主播需要爱玩游戏","audio_url":null,"cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2017-12-29/5a45e0452ef1e.jpg","browse_amount":"26001","good_amount":"1804","create_time":"2017年12月29日","publish_time":"1514528837","keywords":"直播|技巧|分类","article_url":"http://www.zhibohome.net/api/article/app/61","register_good":0},{"id":"54","style":"2","title":"向大主播学习优雅又机智的收礼物","audio_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2017-12-13/151315444959589483.mp3","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2017-12-29/5a45e00038bfa.jpg","browse_amount":"34905","good_amount":"3358","create_time":"2017年12月29日","publish_time":"1514528768","keywords":"直播|技巧|","article_url":"http://www.zhibohome.net/api/article/app/54","register_good":0},{"id":"50","style":"2","title":"新手主播注意事项-提前打听和了解不同的平台","audio_url":null,"cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2017-12-29/5a45dd6f7f68b.jpg","browse_amount":"34834","good_amount":"3360","create_time":"2017年12月29日","publish_time":"1514528111","keywords":"直播|技巧|","article_url":"http://www.zhibohome.net/api/article/app/50","register_good":0}],"first_page_url":"http://test.zhibohome.net/api/article/index?page=1","from":1,"last_page":1,"last_page_url":"http://test.zhibohome.net/api/article/index?page=1","next_page_url":null,"path":"http://test.zhibohome.net/api/article/index","per_page":10,"prev_page_url":null,"to":5,"total":5}
         */

        private int message_count;
        private ArticlesBean articles;
        private List<ArticleCatagorysBean> article_catagorys;
        private List<BannersBean> banners;

        public int getMessage_count() {
            return message_count;
        }

        public void setMessage_count(int message_count) {
            this.message_count = message_count;
        }

        public ArticlesBean getArticles() {
            return articles;
        }

        public void setArticles(ArticlesBean articles) {
            this.articles = articles;
        }

        public List<ArticleCatagorysBean> getArticle_catagorys() {
            return article_catagorys;
        }

        public void setArticle_catagorys(List<ArticleCatagorysBean> article_catagorys) {
            this.article_catagorys = article_catagorys;
        }

        public List<BannersBean> getBanners() {
            return banners;
        }

        public void setBanners(List<BannersBean> banners) {
            this.banners = banners;
        }

        public static class ArticlesBean implements Serializable {
            /**
             * current_page : 1
             * data : [{"id":"62","style":"2","title":"直播行业地区差异：南方有电竞 北方有喊麦","audio_url":null,"cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2017-12-29/5a45e4d334eb0.jpg","browse_amount":"22151","good_amount":"1546","create_time":"3 天前","publish_time":"1514960741","keywords":"直播|新闻|","article_url":"http://www.zhibohome.net/api/article/app/62","register_good":0},{"id":"69","style":"2","title":"2017年中国直播行业规模分析","audio_url":null,"cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-01-03/5a4c706940387.jpg","browse_amount":"544","good_amount":"51","create_time":"3 天前","publish_time":"1514958953","keywords":"2017年报告|直播行业|数据","article_url":"http://www.zhibohome.net/api/article/app/69","register_good":0},{"id":"61","style":"2","title":"娱乐主播需要有才艺，游戏主播需要爱玩游戏","audio_url":null,"cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2017-12-29/5a45e0452ef1e.jpg","browse_amount":"26001","good_amount":"1804","create_time":"2017年12月29日","publish_time":"1514528837","keywords":"直播|技巧|分类","article_url":"http://www.zhibohome.net/api/article/app/61","register_good":0},{"id":"54","style":"2","title":"向大主播学习优雅又机智的收礼物","audio_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2017-12-13/151315444959589483.mp3","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2017-12-29/5a45e00038bfa.jpg","browse_amount":"34905","good_amount":"3358","create_time":"2017年12月29日","publish_time":"1514528768","keywords":"直播|技巧|","article_url":"http://www.zhibohome.net/api/article/app/54","register_good":0},{"id":"50","style":"2","title":"新手主播注意事项-提前打听和了解不同的平台","audio_url":null,"cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2017-12-29/5a45dd6f7f68b.jpg","browse_amount":"34834","good_amount":"3360","create_time":"2017年12月29日","publish_time":"1514528111","keywords":"直播|技巧|","article_url":"http://www.zhibohome.net/api/article/app/50","register_good":0}]
             * first_page_url : http://test.zhibohome.net/api/article/index?page=1
             * from : 1
             * last_page : 1
             * last_page_url : http://test.zhibohome.net/api/article/index?page=1
             * next_page_url : null
             * path : http://test.zhibohome.net/api/article/index
             * per_page : 10
             * prev_page_url : null
             * to : 5
             * total : 5
             */

            private int current_page;
            private String first_page_url;
            private int from;
            private int last_page;
            private String last_page_url;
            private Object next_page_url;
            private String path;
            private int per_page;
            private Object prev_page_url;
            private int to;
            private int total;
            private List<DataBean> data;

            public int getCurrent_page() {
                return current_page;
            }

            public void setCurrent_page(int current_page) {
                this.current_page = current_page;
            }

            public String getFirst_page_url() {
                return first_page_url;
            }

            public void setFirst_page_url(String first_page_url) {
                this.first_page_url = first_page_url;
            }

            public int getFrom() {
                return from;
            }

            public void setFrom(int from) {
                this.from = from;
            }

            public int getLast_page() {
                return last_page;
            }

            public void setLast_page(int last_page) {
                this.last_page = last_page;
            }

            public String getLast_page_url() {
                return last_page_url;
            }

            public void setLast_page_url(String last_page_url) {
                this.last_page_url = last_page_url;
            }

            public Object getNext_page_url() {
                return next_page_url;
            }

            public void setNext_page_url(Object next_page_url) {
                this.next_page_url = next_page_url;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public int getPer_page() {
                return per_page;
            }

            public void setPer_page(int per_page) {
                this.per_page = per_page;
            }

            public Object getPrev_page_url() {
                return prev_page_url;
            }

            public void setPrev_page_url(Object prev_page_url) {
                this.prev_page_url = prev_page_url;
            }

            public int getTo() {
                return to;
            }

            public void setTo(int to) {
                this.to = to;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean implements Serializable{
                /**
                 * id : 62
                 * style : 2
                 * title : 直播行业地区差异：南方有电竞 北方有喊麦
                 * audio_url : null
                 * cover_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2017-12-29/5a45e4d334eb0.jpg
                 * browse_amount : 22151
                 * good_amount : 1546
                 * create_time : 3 天前
                 * publish_time : 1514960741
                 * keywords : 直播|新闻|
                 * article_url : http://www.zhibohome.net/api/article/app/62
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
                private String article_url;
                private int register_good;

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

        public static class ArticleCatagorysBean implements Serializable  {
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

        public static class BannersBean implements Serializable  {
            /**
             * id : 25
             * title : 哈哈哈哈哈哈哈哈哈
             * banner_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/banner/2018-01-03/5a4c8ad63f95a.jpg
             * relation_url : http://www.zhibohome.net/api/article/app/52
             */

            private String id;
            private String title;
            private String banner_url;
            private String relation_url;

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

            public String getRelation_url() {
                return relation_url;
            }

            public void setRelation_url(String relation_url) {
                this.relation_url = relation_url;
            }
        }
    }

