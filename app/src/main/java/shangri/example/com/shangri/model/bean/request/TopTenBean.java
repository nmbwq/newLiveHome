package shangri.example.com.shangri.model.bean.request;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/13.
 */

public class TopTenBean implements Serializable {

    private List<TopDataBean> top_data;
    private List<QuickDataBean> quick_data;

    private List<AnchorTopBean> anchor_top;

    private List<AnchorTopBean> anchor_data;

    public List<AnchorTopBean> getAnchor_data() {
        return anchor_data;
    }

    public void setAnchor_data(List<AnchorTopBean> anchor_data) {
        this.anchor_data = anchor_data;
    }

    public List<AnchorTopBean> getAnchor_top() {
        return anchor_top;
    }

    public void setAnchor_top(List<AnchorTopBean> anchor_top) {
        this.anchor_top = anchor_top;
    }

    public List<TopDataBean> getTop_data() {
        return top_data;
    }

    public void setTop_data(List<TopDataBean> top_data) {
        this.top_data = top_data;
    }

    public List<QuickDataBean> getQuick_data() {
        return quick_data;
    }

    public void setQuick_data(List<QuickDataBean> quick_data) {
        this.quick_data = quick_data;
    }

    public static class TopDataBean {
        /**
         * name : 礼物收益增长
         * start_date : 2018-08-16
         * end_date : 2018-08-16
         * data : {"current_page":1,"data":[],"first_page_url":"http://test.zhibohome.net/api/report/index/topnew?page=1","from":null,"last_page":1,"last_page_url":"http://test.zhibohome.net/api/report/index/topnew?page=1","next_page_url":null,"path":"http://test.zhibohome.net/api/report/index/topnew","per_page":10,"prev_page_url":null,"to":null,"total":0}
         */

        private String name;
        private String start_date;
        private String end_date;
        private DataBean data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * current_page : 1
             * data : []
             * first_page_url : http://test.zhibohome.net/api/report/index/topnew?page=1
             * from : null
             * last_page : 1
             * last_page_url : http://test.zhibohome.net/api/report/index/topnew?page=1
             * next_page_url : null
             * path : http://test.zhibohome.net/api/report/index/topnew
             * per_page : 10
             * prev_page_url : null
             * to : null
             * total : 0
             */
            private int current_page;
            private String first_page_url;
            private Object from;
            private int last_page;
            private String last_page_url;
            private Object next_page_url;
            private String path;
            private int per_page;
            private Object prev_page_url;
            private Object to;
            private int total;
            private List<QuickDataBean.DataBeanXX.DataBeanX> data;

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

            public Object getFrom() {
                return from;
            }

            public void setFrom(Object from) {
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

            public Object getTo() {
                return to;
            }

            public void setTo(Object to) {
                this.to = to;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public List<QuickDataBean.DataBeanXX.DataBeanX> getData() {
                return data;
            }

            public void setData(List<QuickDataBean.DataBeanXX.DataBeanX> data) {
                this.data = data;
            }
        }
    }

    public static class QuickDataBean {
        /**
         * name : 礼物收益增长
         * start_date : 2018-08-16
         * end_date : 2018-08-16
         * data : {"current_page":1,"data":[{"count":"3613140","belong_platfrom_uid":"194021843","anchor_name":"哥華道知天果如","sort":1},{"count":"2419145","belong_platfrom_uid":"1266615866","anchor_name":"火柴妹妹弹古筝","sort":2},{"count":"654749","belong_platfrom_uid":"983387467","anchor_name":"熙儿川妹随便挖","sort":3},{"count":"587798","belong_platfrom_uid":"1048720943","anchor_name":"雪碧陪过情人节","sort":4},{"count":"532666","belong_platfrom_uid":"329591198","anchor_name":"主角8月23日年庆","sort":5},{"count":"460807","belong_platfrom_uid":"328371926","anchor_name":"幽幽感恩陪伴","sort":6},{"count":"447759","belong_platfrom_uid":"1320702097","anchor_name":"俊星江苏漫妮R","sort":7},{"count":"400823","belong_platfrom_uid":"189211580","anchor_name":"萌萌休息调整","sort":8},{"count":"351670","belong_platfrom_uid":"1310040931","anchor_name":"俊星晨美","sort":9},{"count":"348920","belong_platfrom_uid":"228763812","anchor_name":"俊星笑笑","sort":10},{"count":"306291","belong_platfrom_uid":"1290505300","anchor_name":"福建厉新18百天","sort":11},{"count":"289833","belong_platfrom_uid":"78361916","anchor_name":"女将红姐","sort":12},{"count":"246727","belong_platfrom_uid":"205348205","anchor_name":"筱钰七夕出租","sort":13},{"count":"226118","belong_platfrom_uid":"1133567480","anchor_name":"一易公子一","sort":14},{"count":"221233","belong_platfrom_uid":"217709597","anchor_name":"曼巴免X费挖X宝","sort":15},{"count":"198523","belong_platfrom_uid":"145644922","anchor_name":"De杨天成","sort":16},{"count":"191082","belong_platfrom_uid":"387392071","anchor_name":"沐瑶求助攻冠五","sort":17},{"count":"187518","belong_platfrom_uid":"323759712","anchor_name":"只做伱的心儿","sort":18},{"count":"163486","belong_platfrom_uid":"178215397","anchor_name":"俊星雪宝新开始","sort":19},{"count":"158175","belong_platfrom_uid":"41394937","anchor_name":"王云","sort":20}],"first_page_url":"http://test.zhibohome.net/api/report/index/topnew?page=1","from":1,"last_page":7,"last_page_url":"http://test.zhibohome.net/api/report/index/topnew?page=7","next_page_url":"http://test.zhibohome.net/api/report/index/topnew?page=2","path":"http://test.zhibohome.net/api/report/index/topnew","per_page":20,"prev_page_url":null,"to":20,"total":130}
         */

        private String name;
        private String start_date;
        private String end_date;
        private DataBeanXX data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public DataBeanXX getData() {
            return data;
        }

        public void setData(DataBeanXX data) {
            this.data = data;
        }

        public static class DataBeanXX {
            /**
             * current_page : 1
             * data : [{"count":"3613140","belong_platfrom_uid":"194021843","anchor_name":"哥華道知天果如","sort":1},{"count":"2419145","belong_platfrom_uid":"1266615866","anchor_name":"火柴妹妹弹古筝","sort":2},{"count":"654749","belong_platfrom_uid":"983387467","anchor_name":"熙儿川妹随便挖","sort":3},{"count":"587798","belong_platfrom_uid":"1048720943","anchor_name":"雪碧陪过情人节","sort":4},{"count":"532666","belong_platfrom_uid":"329591198","anchor_name":"主角8月23日年庆","sort":5},{"count":"460807","belong_platfrom_uid":"328371926","anchor_name":"幽幽感恩陪伴","sort":6},{"count":"447759","belong_platfrom_uid":"1320702097","anchor_name":"俊星江苏漫妮R","sort":7},{"count":"400823","belong_platfrom_uid":"189211580","anchor_name":"萌萌休息调整","sort":8},{"count":"351670","belong_platfrom_uid":"1310040931","anchor_name":"俊星晨美","sort":9},{"count":"348920","belong_platfrom_uid":"228763812","anchor_name":"俊星笑笑","sort":10},{"count":"306291","belong_platfrom_uid":"1290505300","anchor_name":"福建厉新18百天","sort":11},{"count":"289833","belong_platfrom_uid":"78361916","anchor_name":"女将红姐","sort":12},{"count":"246727","belong_platfrom_uid":"205348205","anchor_name":"筱钰七夕出租","sort":13},{"count":"226118","belong_platfrom_uid":"1133567480","anchor_name":"一易公子一","sort":14},{"count":"221233","belong_platfrom_uid":"217709597","anchor_name":"曼巴免X费挖X宝","sort":15},{"count":"198523","belong_platfrom_uid":"145644922","anchor_name":"De杨天成","sort":16},{"count":"191082","belong_platfrom_uid":"387392071","anchor_name":"沐瑶求助攻冠五","sort":17},{"count":"187518","belong_platfrom_uid":"323759712","anchor_name":"只做伱的心儿","sort":18},{"count":"163486","belong_platfrom_uid":"178215397","anchor_name":"俊星雪宝新开始","sort":19},{"count":"158175","belong_platfrom_uid":"41394937","anchor_name":"王云","sort":20}]
             * first_page_url : http://test.zhibohome.net/api/report/index/topnew?page=1
             * from : 1
             * last_page : 7
             * last_page_url : http://test.zhibohome.net/api/report/index/topnew?page=7
             * next_page_url : http://test.zhibohome.net/api/report/index/topnew?page=2
             * path : http://test.zhibohome.net/api/report/index/topnew
             * per_page : 20
             * prev_page_url : null
             * to : 20
             * total : 130
             */

            private int current_page;
            private String first_page_url;
            private int from;
            private int last_page;
            private String last_page_url;
            private String next_page_url;
            private String path;
            private int per_page;
            private Object prev_page_url;
            private int to;
            private int total;
            private List<DataBeanX> data;

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

            public String getNext_page_url() {
                return next_page_url;
            }

            public void setNext_page_url(String next_page_url) {
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

            public List<DataBeanX> getData() {
                return data;
            }

            public void setData(List<DataBeanX> data) {
                this.data = data;
            }

            public static class DataBeanX {
                /**
                 * count : 3613140
                 * belong_platfrom_uid : 194021843
                 * anchor_name : 哥華道知天果如
                 * sort : 1
                 */

                private String count;
                private String belong_platfrom_uid;
                private String anchor_name;
                private String guild_id;
                private int sort;
//                private String nickname;
//                private String credits;
//
//                public String getNickname() {
//                    return nickname;
//                }
//
//                public void setNickname(String nickname) {
//                    this.nickname = nickname;
//                }
//
//                public String getCredits() {
//                    return credits;
//                }
//
//                public void setCredits(String credits) {
//                    this.credits = credits;
//                }

                public String getCount() {
                    return count;
                }

                public String getGuild_id() {
                    return guild_id;
                }

                public void setGuild_id(String guild_id) {
                    this.guild_id = guild_id;
                }

                public void setCount(String count) {
                    this.count = count;
                }

                public String getBelong_platfrom_uid() {
                    return belong_platfrom_uid;
                }

                public void setBelong_platfrom_uid(String belong_platfrom_uid) {
                    this.belong_platfrom_uid = belong_platfrom_uid;
                }

                public String getAnchor_name() {
                    return anchor_name;
                }

                public void setAnchor_name(String anchor_name) {
                    this.anchor_name = anchor_name;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }
            }
        }
    }


    public static class AnchorTopBean {
        /**
         * name : 土豪
         * data : {"data":[{"rank_uid":"137145152","nickname":"唯你是青山","credits":"4644312","avatar":"http://ares.kktv8.com/kktv/portrait/20180804/12/137145152_5036918.jpg!256"},{"rank_uid":"85793968","nickname":" 离 人 ","credits":"2114476","avatar":"http://ares.kktv8.com/kktv/portrait/20180809/23/85793968_3323612.jpg!256"},{"rank_uid":"76614166","nickname":"就想听会歌","credits":"1029000","avatar":"http://ares.kktv8.com/kktv/portrait/20180809/9/76614166_1159437.jpg!256"},{"rank_uid":"46443416","nickname":" 从零开始 ","credits":"724361","avatar":"http://ares.kktv8.com/kktv/portrait/20180426/19/46443416_4745581.jpg!256"},{"rank_uid":"133423253","nickname":"绝情葬爱荒度人生","credits":"371506","avatar":"http://ares.kktv8.com/kktv/portrait/20180810/17/133423253_4259542.jpg!256"},{"rank_uid":"137195198","nickname":"白方璟","credits":"343050","avatar":"http://ares.kktv8.com/kktv/portrait/20180803/19/137195198_293500.jpg!256"},{"rank_uid":"89529086","nickname":" 落 叶 ","credits":"264099","avatar":"http://ares.kktv8.com/kktv/portrait/20180806/21/89529086_2748478.jpg!256"},{"rank_uid":"65147667","nickname":"退 再也不见","credits":"252200","avatar":"http://ares.kktv8.com/kktv/portrait/20180808/12/65147667_1935572.jpg!256"},{"rank_uid":"137571395","nickname":"   单纯的男孩儿 ","credits":"244876","avatar":"http://ares.kktv8.com/kktv/portrait/20180805/17/137571395_5843133.jpg!256"},{"rank_uid":"132720482","nickname":"我 爱 潇 ","credits":"244291","avatar":"http://ares.kktv8.com/kktv/portrait/20180731/12/132720482_101093.jpg!256"}]}
         */

        private String name;
        private DataBeanX data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public DataBeanX getData() {
            return data;
        }

        public void setData(DataBeanX data) {
            this.data = data;
        }

        public static class DataBeanX {
            private List<DataBean> data;

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * rank_uid : 137145152
                 * nickname : 唯你是青山
                 * credits : 4644312
                 * avatar : http://ares.kktv8.com/kktv/portrait/20180804/12/137145152_5036918.jpg!256
                 */

                private String rank_uid;
                private String nickname;
                private String credits;
                private String avatar;

                //主播快速公会 后面top20 需要的参数 和 nickname credits一样
                private String count;
                private String anchor_name;


                public String getCount() {
                    return count;
                }

                public void setCount(String count) {
                    this.count = count;
                }

                public String getAnchor_name() {
                    return anchor_name;
                }

                public void setAnchor_name(String anchor_name) {
                    this.anchor_name = anchor_name;
                }

                public String getRank_uid() {
                    return rank_uid;
                }

                public void setRank_uid(String rank_uid) {
                    this.rank_uid = rank_uid;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getCredits() {
                    return credits;
                }

                public void setCredits(String credits) {
                    this.credits = credits;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }
            }
        }
    }


}
