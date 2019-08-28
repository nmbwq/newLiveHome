package shangri.example.com.shangri.model.bean.response;

import java.util.List;

public class GuildListBean {

    private List<GuildsBean> guilds;
    private List<GuildsBean> quick_guilds;

    public List<GuildsBean> getQuick_guilds() {
        return quick_guilds;
    }

    public void setQuick_guilds(List<GuildsBean> quick_guilds) {
        this.quick_guilds = quick_guilds;
    }

    public List<GuildsBean> getGuilds() {
        return guilds;
    }

    public void setGuilds(List<GuildsBean> guilds) {
        this.guilds = guilds;
    }

    public static class GuildsBean {
        /**
         * data : {"current":{"anchor_count":"0","diamond":"0","gift":"0.00"},"name":"礼物收入","name2":"钻石收入","pre_current":{"anchor_count":"12","diamond":"0","gift":"70030.80"}}
         * guild_id : 323ruyuwenhua
         * guild_name : 一直播（猫咪）
         * icon_url : http://cdn1.tooohappy.com/icon/2017-12-13/151314337041127286.jpg
         */

        private DataBean data;
        private String guild_id;
        private String guild_name;
        private String icon_url;
        private int type;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public String getGuild_id() {
            return guild_id;
        }

        public void setGuild_id(String guild_id) {
            this.guild_id = guild_id;
        }

        public String getGuild_name() {
            return guild_name;
        }

        public void setGuild_name(String guild_name) {
            this.guild_name = guild_name;
        }

        public String getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(String icon_url) {
            this.icon_url = icon_url;
        }

        public static class DataBean {
            /**
             * current : {"anchor_count":"0","diamond":"0","gift":"0.00"}
             * name : 礼物收入
             * name2 : 钻石收入
             * pre_current : {"anchor_count":"12","diamond":"0","gift":"70030.80"}
             */
            private CurrentBean current;
            private String name;
            private String name2;
            private PreCurrentBean pre_current;

            public CurrentBean getCurrent() {
                return current;
            }

            public void setCurrent(CurrentBean current) {
                this.current = current;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getName2() {
                return name2;
            }

            public void setName2(String name2) {
                this.name2 = name2;
            }

            public PreCurrentBean getPre_current() {
                return pre_current;
            }

            public void setPre_current(PreCurrentBean pre_current) {
                this.pre_current = pre_current;
            }

            public static class CurrentBean {
                /**
                 * anchor_count : 0
                 * diamond : 0
                 * gift : 0.00
                 */

                private String anchor_count;
                private String diamond;
                private String gift;
                private String live_time = "";


//                private  int  qian_gift=0;
//                private  int  qian_live_time=0;
//                private  int  qian_anchor_count=0;
//                private  int  qian_diamond=0;


                private String qian_gift = "";
                private String qian_live_time = "";
                private String qian_anchor_count = "";
                private String qian_diamond = "";

                public String getQian_gift() {
                    return qian_gift;
                }

                public void setQian_gift(String qian_gift) {
                    this.qian_gift = qian_gift;
                }

                public String getQian_live_time() {
                    return qian_live_time;
                }

                public void setQian_live_time(String qian_live_time) {
                    this.qian_live_time = qian_live_time;
                }

                public String getQian_anchor_count() {
                    return qian_anchor_count;
                }

                public void setQian_anchor_count(String qian_anchor_count) {
                    this.qian_anchor_count = qian_anchor_count;
                }

                public String getQian_diamond() {
                    return qian_diamond;
                }

                public void setQian_diamond(String qian_diamond) {
                    this.qian_diamond = qian_diamond;
                }


                public String getLive_time() {
                    return live_time;
                }

                public void setLive_time(String live_time) {
                    this.live_time = live_time;
                }

                public String getAnchor_count() {
                    return anchor_count;
                }

                public void setAnchor_count(String anchor_count) {
                    this.anchor_count = anchor_count;
                }

                public String getDiamond() {
                    return diamond;
                }

                public void setDiamond(String diamond) {
                    this.diamond = diamond;
                }

                public String getGift() {
                    return gift;
                }

                public void setGift(String gift) {
                    this.gift = gift;
                }
            }

            public static class PreCurrentBean {
                /**
                 * anchor_count : 12
                 * diamond : 0
                 * gift : 70030.80
                 */

                private String anchor_count;
                private String diamond;
                private String gift;
                private String live_time = "";


//                private int qian_gift = 0;
//                private int qian_live_time = 0;
//                private int qian_anchor_count = 0;
//                private int qian_diamond = 0;


                private String qian_gift = "";
                private String qian_live_time = "";
                private String qian_anchor_count = "";
                private String qian_diamond = "";

//
//                public int getQian_gift() {
//                    return qian_gift;
//                }
//
//                public void setQian_gift(int qian_gift) {
//                    this.qian_gift = qian_gift;
//                }
//
//                public int getQian_live_time() {
//                    return qian_live_time;
//                }
//
//                public void setQian_live_time(int qian_live_time) {
//                    this.qian_live_time = qian_live_time;
//                }
//
//                public int getQian_anchor_count() {
//                    return qian_anchor_count;
//                }
//
//                public void setQian_anchor_count(int qian_anchor_count) {
//                    this.qian_anchor_count = qian_anchor_count;
//                }
//
//                public int getQian_diamond() {
//                    return qian_diamond;
//                }


                public String getQian_gift() {
                    return qian_gift;
                }

                public void setQian_gift(String qian_gift) {
                    this.qian_gift = qian_gift;
                }

                public String getQian_live_time() {
                    return qian_live_time;
                }

                public void setQian_live_time(String qian_live_time) {
                    this.qian_live_time = qian_live_time;
                }

                public String getQian_anchor_count() {
                    return qian_anchor_count;
                }

                public void setQian_anchor_count(String qian_anchor_count) {
                    this.qian_anchor_count = qian_anchor_count;
                }

                public String getQian_diamond() {
                    return qian_diamond;
                }

                public void setQian_diamond(String qian_diamond) {
                    this.qian_diamond = qian_diamond;
                }

                public String getLive_time() {
                    return live_time;
                }

                public void setLive_time(String live_time) {
                    this.live_time = live_time;
                }

                public String getAnchor_count() {
                    return anchor_count;
                }

                public void setAnchor_count(String anchor_count) {
                    this.anchor_count = anchor_count;
                }

                public String getDiamond() {
                    return diamond;
                }

                public void setDiamond(String diamond) {
                    this.diamond = diamond;
                }

                public String getGift() {
                    return gift;
                }

                public void setGift(String gift) {
                    this.gift = gift;
                }
            }
        }
    }
}
