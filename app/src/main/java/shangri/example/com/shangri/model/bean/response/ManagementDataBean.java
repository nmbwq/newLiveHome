package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/7.
 */

public class ManagementDataBean implements Serializable {
    /**
     * guilds : [{"guild_id":"1234548255","guild_name":"菠萝街（coco）","platfrom_name":"菠萝街直播"},{"guild_id":"650418055593","guild_name":"UP（猫咪）","platfrom_name":"UP直播"},{"guild_id":"323ruyuwenhua","guild_name":"一直播（猫咪）","platfrom_name":"一直播"},{"guild_id":"53425479107","guild_name":"小米（猫咪）","platfrom_name":"小米直播"},{"guild_id":"432418055593","guild_name":"NOW（猫咪）","platfrom_name":"NOW直播"},{"guild_id":"665418055593","guild_name":"火山（猫咪）","platfrom_name":"火山直播"}]
     * start_date : 2018-01-01
     * end_date : 2018-01-07
     * total_data : {"anchors":{"name":"开播主播","data":120},"gifts":{"name":"魅力值增长","data":"6594964"},"live_time":{"name":"开播时长","data":"888.35"},"added_fans":{"name":"粉丝增长","data":"17753"}}
     */

    private String start_date;
    private String end_date;
    private TotalDataBean total_data;
    private List<GuildsBean> guilds;
    private List<GuildsBean> quick_guild;

    public List<GuildsBean> getQuick_guild() {
        return quick_guild;
    }

    public void setQuick_guild(List<GuildsBean> quick_guild) {
        this.quick_guild = quick_guild;
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

    public static class TotalDataBean implements Serializable {
        /**
         * anchors : {"name":"开播主播","data":120}
         * gifts : {"name":"魅力值增长","data":"6594964"}
         * live_time : {"name":"开播时长","data":"888.35"}
         * added_fans : {"name":"粉丝增长","data":"17753"}
         */

        private AnchorsBean anchors;
        private GiftsBean gifts;
        private IncomeGiftBean income_gift;
        private LiveTimeBean live_time;
        private AddedFansBean added_fans;
        private IncomeDiamondBean income_diamond;
        private PeriodGiftsBean period_gifts;
        private AnchorNameBean anchor_name;
        private AnchorUidBean anchor_uid;

        private giftsmouthBean giftsmouth;


        public giftsmouthBean getGiftsmouth() {
            return giftsmouth;
        }

        public void setGiftsmouth(giftsmouthBean giftsmouth) {
            this.giftsmouth = giftsmouth;
        }

        public AnchorNameBean getAnchor_name() {
            return anchor_name;
        }

        public void setAnchor_name(AnchorNameBean anchor_name) {
            this.anchor_name = anchor_name;
        }

        public AnchorUidBean getAnchor_uid() {
            return anchor_uid;
        }

        public void setAnchor_uid(AnchorUidBean anchor_uid) {
            this.anchor_uid = anchor_uid;
        }

        public PeriodGiftsBean getPeriod_gifts() {
            return period_gifts;
        }

        public void setPeriod_gifts(PeriodGiftsBean period_gifts) {
            this.period_gifts = period_gifts;
        }

        public AnchorsBean getAnchors() {
            return anchors;
        }

        public void setAnchors(AnchorsBean anchors) {
            this.anchors = anchors;
        }

        public GiftsBean getGifts() {
            return gifts;
        }

        public void setGifts(GiftsBean gifts) {
            this.gifts = gifts;
        }

        public IncomeGiftBean getIncome_gift() {
            return income_gift;
        }

        public void setIncome_gift(IncomeGiftBean income_gift) {
            this.income_gift = income_gift;
        }

        public IncomeDiamondBean getIncome_diamond() {
            return income_diamond;
        }

        public void setIncome_diamond(IncomeDiamondBean income_diamond) {
            this.income_diamond = income_diamond;
        }

        public LiveTimeBean getLive_time() {
            return live_time;
        }

        public void setLive_time(LiveTimeBean live_time) {
            this.live_time = live_time;
        }

        public AddedFansBean getAdded_fans() {
            return added_fans;
        }

        public void setAdded_fans(AddedFansBean added_fans) {
            this.added_fans = added_fans;
        }

        public static class AnchorsBean implements Serializable {
            /**
             * name : 开播主播
             * data : 120
             */

            private String name;
            private String data;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }
        }

        public static class GiftsBean implements Serializable {
            /**
             * name : 魅力值增长
             * data : 6594964
             */

            private String name;
            private String data;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }
        }

        public static class LiveTimeBean implements Serializable {
            /**
             * name : 开播时长
             * data : 888.35
             */
            private String name;
            private String data;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }
        }

        public static class IncomeGiftBean {
            /**
             * name : 礼物收入增长
             * data : 0
             */

            private String name;
            private String data;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }
        }

        public static class AddedFansBean implements Serializable {
            /**
             * name : 粉丝增长
             * data : 17753
             */

            private String name;
            private String data;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }
        }
    }

    public static class AnchorNameBean {
        /**
         * name : 主播昵称
         * data : 逗比婉要加油
         */

        private String name;
        private String data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

    public static class AnchorUidBean {
        /**
         * name : 主播ID
         * data : 8720266
         */

        private String name;
        private String data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }


    public static class giftsmouthBean {
        /**
         * name : 主播ID
         * data : 8720266
         */

        private String name;
        private String data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

    public static class PeriodGiftsBean implements Serializable {
        /**
         * name : 钻石收入增长
         * data : 0
         */

        private String name;
        private String data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

    public static class IncomeDiamondBean implements Serializable {
        /**
         * name : 钻石收入增长
         * data : 0
         */

        private String name;
        private String data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

    public static class GuildsBean implements Serializable {
        /**
         * guild_id : 1234548255
         * guild_name : 菠萝街（coco）
         * platfrom_name : 菠萝街直播
         */

        private String guild_id;
        private String guild_name;
        private String platfrom_name;
        private String status;
        private String table_flag;
        //0正常  1快速公会
        private String type;


        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTable_flag() {
            return table_flag;
        }

        public void setTable_flag(String table_flag) {
            this.table_flag = table_flag;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public String getPlatfrom_name() {
            return platfrom_name;
        }

        public void setPlatfrom_name(String platfrom_name) {
            this.platfrom_name = platfrom_name;
        }
    }
}
