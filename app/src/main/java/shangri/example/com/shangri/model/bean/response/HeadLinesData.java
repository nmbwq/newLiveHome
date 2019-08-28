package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public class HeadLinesData implements Serializable {
    /**
     * catagorys : [{"id":"1","name":"新手攻略"},{"id":"2","name":"粉丝维护"},{"id":"3","name":"直播"}]
     * banners : [{"id":"26","title":"虎牙星盛典圆满落幕，主播培养仍是未来重点， 承诺永不欠薪","banner_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/banner/2018-02-01/5a72c7158f246.jpg","relation_id":"100","relation_url":"https://www.zhibohome.net/api/article/app/100","article_url":"https://www.zhibohome.net/api/article/app/100","type":"2","audio_url":"","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-02-01/5a72c14bc4e5e.jpg"}]
     * articles : [{"id":"117","style":"2","title":"4.8王菲登封面杂志素净空灵气场足  马天宇使大招求王菲高清写真","audio_url":"http://zhibohome-audio-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-04-08/5ac9bf8232c0d.mp3","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-04-08/5ac9bf823d48e.jpg","browse_amount":"3","good_amount":"0","create_time":"2018年04月08日","publish_time":"1523171284","keywords":"||","collect_amount":"0","type":2,"article_url":"http://test.zhibohome.net/api/article/app/117","register_good":0,"register_collect":0},{"id":"113","style":"2","title":"王菲登封面杂志素净空灵气场足  马天宇使大招求王菲高清写真","audio_url":"","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-03-06/5a9e55c0cc893.jpg","browse_amount":"33506","good_amount":"2313","create_time":"2018年03月06日","publish_time":"1520326446","keywords":"明星|大片|娱乐","collect_amount":"556","type":2,"article_url":"http://test.zhibohome.net/api/article/app/113","register_good":0,"register_collect":0},{"id":"112","style":"2","title":"李宇春2018\u2014\u2014流行（liú xíng）巡演主海报揭晓  首轮开票今晚开抢！","audio_url":"","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-03-01/5a979e6716244.jpg","browse_amount":"55671","good_amount":"3823","create_time":"2018年03月01日","publish_time":"1519891592","keywords":"明星|流行|","collect_amount":"924","type":2,"article_url":"http://test.zhibohome.net/api/article/app/112","register_good":0,"register_collect":0},{"id":"111","style":"2","title":"抖音年度人气明星揭晓","audio_url":"","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-02-23/5a8fbfca237ff.jpg","browse_amount":"81415","good_amount":"5673","create_time":"2018年02月23日","publish_time":"1519370186","keywords":"抖音|打榜|","collect_amount":"1350","type":2,"article_url":"http://test.zhibohome.net/api/article/app/111","register_good":0,"register_collect":0},{"id":"110","style":"2","title":"一直播平台公布 \u2014 2017年度直播影响力媒体榜","audio_url":"","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-02-11/5a7fb488471c1.jpg","browse_amount":"121512","good_amount":"8418","create_time":"2018年02月11日","publish_time":"1518319555","keywords":"一直播|榜单|","collect_amount":"2032","type":2,"article_url":"http://test.zhibohome.net/api/article/app/110","register_good":0,"register_collect":0},{"id":"109","style":"2","title":"超新星之夜背后，now直播强势出击，内容突围战已打响","audio_url":"","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-02-10/5a7e9161dd09d.jpg","browse_amount":"122066","good_amount":"8494","create_time":"2018年02月10日","publish_time":"1518256846","keywords":"now直播|超新星|","collect_amount":"2042","type":2,"article_url":"http://test.zhibohome.net/api/article/app/109","register_good":0,"register_collect":0},{"id":"108","style":"2","title":"一直播平台公布\u20142017年度直播影响力政务榜单，有图有真相！","audio_url":"","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-02-05/5a7849b499700.jpg","browse_amount":"121418","good_amount":"8467","create_time":"2018年02月05日","publish_time":"1517833924","keywords":"榜单|直播|一直播","collect_amount":"2006","type":2,"article_url":"http://test.zhibohome.net/api/article/app/108","register_good":0,"register_collect":1},{"id":"107","style":"2","title":"众星亮相\u201c放肆一下\u201d盛典红毯 星光熠熠，一直播平台一众头部主播出席","audio_url":"","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-02-05/5a7839b549ced.jpg","browse_amount":"121629","good_amount":"8479","create_time":"2018年02月05日","publish_time":"1517833916","keywords":"一直播|直播+社交|明星","collect_amount":"2009","type":2,"article_url":"http://test.zhibohome.net/api/article/app/107","register_good":0,"register_collect":1},{"id":"106","style":"2","title":"黄轩登杂志封面演绎俊朗贵公子 多感型男雅痞不羁","audio_url":"","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-02-05/5a781cc2e878a.jpg","browse_amount":"121946","good_amount":"8521","create_time":"2018年02月05日","publish_time":"1517833074","keywords":"明星|娱乐|","collect_amount":"2017","type":2,"article_url":"http://test.zhibohome.net/api/article/app/106","register_good":0,"register_collect":1},{"id":"105","style":"2","title":"kilakila2017豆粉二次元宠幸榜","audio_url":"","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-02-03/5a756af974fd3.jpg","browse_amount":"121260","good_amount":"8449","create_time":"2018年02月03日","publish_time":"1517644586","keywords":"二次元|榜单|","collect_amount":"2037","type":2,"article_url":"http://test.zhibohome.net/api/article/app/105","register_good":0,"register_collect":0},{"id":"104","style":"2","title":"手握二次元入场券 \u201c红豆Live \u201d正式更名\u201cKilaKila\u201d  如何突围语音直播边界","audio_url":"","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-02-03/5a755cc42a3ff.jpg","browse_amount":"121451","good_amount":"8463","create_time":"2018年02月03日","publish_time":"1517644579","keywords":"语音直播|红豆live|二次元","collect_amount":"2041","type":2,"article_url":"http://test.zhibohome.net/api/article/app/104","register_good":0,"register_collect":1},{"id":"103","style":"2","title":"陌陌发布《直播答题用户报告》大赢家是学生党","audio_url":"","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-02-02/5a7431ea8effa.jpg","browse_amount":"121384","good_amount":"8440","create_time":"2018年02月02日","publish_time":"1517565414","keywords":"陌陌|直播答题|","collect_amount":"2031","type":2,"article_url":"http://test.zhibohome.net/api/article/app/103","register_good":0,"register_collect":1},{"id":"102","style":"2","title":"《星速客SHOOT》张继科大片曝光 血性又温情才是真少年","audio_url":"","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-02-01/5a72e9d3b8686.jpg","browse_amount":"121319","good_amount":"8442","create_time":"2018年02月01日","publish_time":"1517481700","keywords":"娱乐|时尚|明星","collect_amount":"2030","type":2,"article_url":"http://test.zhibohome.net/api/article/app/102","register_good":0,"register_collect":1},{"id":"100","style":"2","title":"虎牙星盛典圆满落幕，主播培养仍是未来重点  承诺永不欠薪","audio_url":"","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-02-01/5a72c14bc4e5e.jpg","browse_amount":"121843","good_amount":"8480","create_time":"2018年02月01日","publish_time":"1517481619","keywords":"虎牙直播|主播培养|造星","collect_amount":"2035","type":2,"article_url":"http://test.zhibohome.net/api/article/app/100","register_good":0,"register_collect":0},{"id":"101","style":"2","title":"Angelababy登大刊封面文艺复古风 红唇娇艳似芭比","audio_url":"","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-02-01/5a72d59f54bec.jpg","browse_amount":"121240","good_amount":"8440","create_time":"2018年02月01日","publish_time":"1517475231","keywords":"明星|娱乐|","collect_amount":"2027","type":2,"article_url":"http://test.zhibohome.net/api/article/app/101","register_good":0,"register_collect":0},{"id":"99","style":"2","title":"第60届格莱美奖揭晓！美国歌手马尔斯狂揽七项大奖","audio_url":"","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-01-30/5a7049298b6ff.jpg","browse_amount":"126475","good_amount":"8786","create_time":"2018年01月30日","publish_time":"1517308432","keywords":"格莱美|音乐|娱乐","collect_amount":"2129","type":2,"article_url":"http://test.zhibohome.net/api/article/app/99","register_good":0,"register_collect":0},{"id":"98","style":"2","title":"荔枝\u201c了不起的声音\u201d直播盛典收官，荔枝年度男女主播前三强诞生","audio_url":"","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-01-29/5a6f0465d071a.jpg","browse_amount":"130678","good_amount":"9089","create_time":"2018年01月29日","publish_time":"1517225867","keywords":"荔枝直播|语音直播|年度盛典","collect_amount":"2198","type":2,"article_url":"http://test.zhibohome.net/api/article/app/98","register_good":0,"register_collect":0},{"id":"97","style":"2","title":"《三生三世》白浅、夜华再携手！杨幂赵又廷合体拍情人节大片","audio_url":"","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-01-25/5a69a9d433ec8.jpg","browse_amount":"134280","good_amount":"9298","create_time":"2018年01月25日","publish_time":"1516874669","keywords":"娱乐|明星|资讯","collect_amount":"2265","type":2,"article_url":"http://test.zhibohome.net/api/article/app/97","register_good":0,"register_collect":0},{"id":"96","style":"2","title":"第90届奥斯卡提名名单揭晓  《水形物语》13项提名强势领跑","audio_url":"","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-01-24/5a680286350f8.jpg","browse_amount":"134238","good_amount":"9281","create_time":"2018年01月24日","publish_time":"1516766159","keywords":"奥斯卡|娱乐|","collect_amount":"2241","type":2,"article_url":"http://test.zhibohome.net/api/article/app/96","register_good":0,"register_collect":0},{"id":"95","style":"2","title":"49岁许晴直播中卸妆迎生日  素颜对镜头少女感十足","audio_url":"","cover_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-01-23/5a670e3354659.jpg","browse_amount":"134169","good_amount":"9275","create_time":"2018年01月23日","publish_time":"1516703283","keywords":"素颜直播|明星直播|娱乐","collect_amount":"2239","type":2,"article_url":"http://test.zhibohome.net/api/article/app/95","register_good":0,"register_collect":0}]
     * current_page : 1
     * total_page : 3
     */

    private String current_page;
    private int total_page;
    private List<CatagorysBean> catagorys;
    private List<BannersBean> banners;
    private List<ArticlesBean> articles;

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

    public List<CatagorysBean> getCatagorys() {
        return catagorys;
    }

    public void setCatagorys(List<CatagorysBean> catagorys) {
        this.catagorys = catagorys;
    }

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public List<ArticlesBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesBean> articles) {
        this.articles = articles;
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

    public static class BannersBean {
        /**
         * id : 26
         * title : 虎牙星盛典圆满落幕，主播培养仍是未来重点， 承诺永不欠薪
         * banner_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/banner/2018-02-01/5a72c7158f246.jpg
         * relation_id : 100
         * relation_url : https://www.zhibohome.net/api/article/app/100
         * article_url : https://www.zhibohome.net/api/article/app/100
         * type : 2
         * audio_url :
         * cover_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-02-01/5a72c14bc4e5e.jpg
         */

        private String id;
        private String title;
        private String banner_url;
        private String relation_id;
        private String relation_url;
        private String article_url;
        private String type;
        private String audio_url;
        private String cover_url;
        private String browse_amount;
        private String good_amount;
        private String collect_amount;
        private int register_good;
        private int register_collect;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

    public static class ArticlesBean {
        /**
         * id : 117
         * style : 2
         * title : 4.8王菲登封面杂志素净空灵气场足  马天宇使大招求王菲高清写真
         * audio_url : http://zhibohome-audio-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-04-08/5ac9bf8232c0d.mp3
         * cover_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-04-08/5ac9bf823d48e.jpg
         * browse_amount : 3
         * good_amount : 0
         * create_time : 2018年04月08日
         * publish_time : 1523171284
         * keywords : ||
         * collect_amount : 0
         * type : 2
         * article_url : http://test.zhibohome.net/api/article/app/117
         * register_good : 0
         * register_collect : 0
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
        private int type;
        private String article_url;
        private int register_good;
        private int register_collect;


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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

