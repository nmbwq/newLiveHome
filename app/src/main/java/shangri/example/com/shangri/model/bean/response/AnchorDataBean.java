package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */

public class AnchorDataBean implements Serializable {

    /**
     * code : 0
     * message : success
     * data : {"guilds":[{"guild_id":"1234548255","guild_name":"菠萝街（coco）","platfrom_name":"菠萝街直播"},{"guild_id":"650418055593","guild_name":"UP（猫咪）","platfrom_name":"UP直播"},{"guild_id":"323ruyuwenhua","guild_name":"一直播（猫咪）","platfrom_name":"一直播"},{"guild_id":"53425479107","guild_name":"小米（猫咪）","platfrom_name":"小米直播"},{"guild_id":"432418055593","guild_name":"NOW（猫咪）","platfrom_name":"NOW直播"},{"guild_id":"665418055593","guild_name":"火山（猫咪）","platfrom_name":"火山直播"}],"start_date":"2018-01-11","end_date":"2018-01-11","total_data":{"anchors":{"name":"开播主播","data":0},"income_gift":{"name":"礼物收入增长","data":0},"live_time":{"name":"开播时长","data":0},"income_diamond":{"name":"钻石收入增长","data":0}}}
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
        /**
         * guilds : [{"guild_id":"1234548255","guild_name":"菠萝街（coco）","platfrom_name":"菠萝街直播"},{"guild_id":"650418055593","guild_name":"UP（猫咪）","platfrom_name":"UP直播"},{"guild_id":"323ruyuwenhua","guild_name":"一直播（猫咪）","platfrom_name":"一直播"},{"guild_id":"53425479107","guild_name":"小米（猫咪）","platfrom_name":"小米直播"},{"guild_id":"432418055593","guild_name":"NOW（猫咪）","platfrom_name":"NOW直播"},{"guild_id":"665418055593","guild_name":"火山（猫咪）","platfrom_name":"火山直播"}]
         * start_date : 2018-01-11
         * end_date : 2018-01-11
         * total_data : {"anchors":{"name":"开播主播","data":0},"income_gift":{"name":"礼物收入增长","data":0},"live_time":{"name":"开播时长","data":0},"income_diamond":{"name":"钻石收入增长","data":0}}
         */

        private String start_date;
        private String end_date;
        private TotalDataBean total_data;
        private List<GuildsBean> guilds;

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

        public TotalDataBean getTotal_data() {
            return total_data;
        }

        public void setTotal_data(TotalDataBean total_data) {
            this.total_data = total_data;
        }

        public List<GuildsBean> getGuilds() {
            return guilds;
        }

        public void setGuilds(List<GuildsBean> guilds) {
            this.guilds = guilds;
        }

        public static class TotalDataBean {
            /**
             * anchors : {"name":"开播主播","data":0}
             * income_gift : {"name":"礼物收入增长","data":0}
             * live_time : {"name":"开播时长","data":0}
             * income_diamond : {"name":"钻石收入增长","data":0}
             */

            private AnchorsBean anchors;
            private IncomeGiftBean income_gift;
            private LiveTimeBean live_time;
            private IncomeDiamondBean income_diamond;

            public AnchorsBean getAnchors() {
                return anchors;
            }

            public void setAnchors(AnchorsBean anchors) {
                this.anchors = anchors;
            }

            public IncomeGiftBean getIncome_gift() {
                return income_gift;
            }

            public void setIncome_gift(IncomeGiftBean income_gift) {
                this.income_gift = income_gift;
            }

            public LiveTimeBean getLive_time() {
                return live_time;
            }

            public void setLive_time(LiveTimeBean live_time) {
                this.live_time = live_time;
            }

            public IncomeDiamondBean getIncome_diamond() {
                return income_diamond;
            }

            public void setIncome_diamond(IncomeDiamondBean income_diamond) {
                this.income_diamond = income_diamond;
            }

            public static class AnchorsBean {
                /**
                 * name : 开播主播
                 * data : 0
                 */

                private String name;
                private int data;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getData() {
                    return data;
                }

                public void setData(int data) {
                    this.data = data;
                }
            }

            public static class IncomeGiftBean {
                /**
                 * name : 礼物收入增长
                 * data : 0
                 */

                private String name;
                private int data;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getData() {
                    return data;
                }

                public void setData(int data) {
                    this.data = data;
                }
            }

            public static class LiveTimeBean {
                /**
                 * name : 开播时长
                 * data : 0
                 */

                private String name;
                private int data;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getData() {
                    return data;
                }

                public void setData(int data) {
                    this.data = data;
                }
            }

            public static class IncomeDiamondBean {
                /**
                 * name : 钻石收入增长
                 * data : 0
                 */

                private String name;
                private int data;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getData() {
                    return data;
                }

                public void setData(int data) {
                    this.data = data;
                }
            }
        }

        public static class GuildsBean {
            /**
             * guild_id : 1234548255
             * guild_name : 菠萝街（coco）
             * platfrom_name : 菠萝街直播
             */

            private String guild_id;
            private String guild_name;
            private String platfrom_name;

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

            public String getPlatfrom_name() {
                return platfrom_name;
            }

            public void setPlatfrom_name(String platfrom_name) {
                this.platfrom_name = platfrom_name;
            }
        }
    }
}

