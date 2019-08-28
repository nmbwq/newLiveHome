package shangri.example.com.shangri.model.bean.response;

import java.util.List;

/**
 * 报表-----主播详情
 */
public class RespAnchoDetailBean {

        /**
         * chart_data : {"sort":[{"date":"2018-05-15","count":1},{"date":"2018-05-16","count":1},{"date":"2018-05-17","count":6},{"date":"2018-05-18","count":6},{"date":"2018-05-19","count":5},{"date":"2018-05-20","count":1},{"date":"2018-05-21","count":1}],"gifts":[{"count":"146733","date":"2018-05-15"},{"count":"61307","date":"2018-05-16"},{"count":"0.00","date":"2018-05-17"},{"count":"0.00","date":"2018-05-18"},{"count":"0.00","date":"2018-05-19"},{"count":"33744","date":"2018-05-20"},{"count":"60399","date":"2018-05-21"}],"live_time":[{"count":"2.53","date":"2018-05-15"},{"count":"1.07","date":"2018-05-16"},{"count":"0.00","date":"2018-05-17"},{"count":"0.00","date":"2018-05-18"},{"count":"0.00","date":"2018-05-19"},{"count":"1.31","date":"2018-05-20"},{"count":"2.00","date":"2018-05-21"}],"added_fans":[{"count":"70","date":"2018-05-15"},{"count":"44","date":"2018-05-16"},{"count":"0.00","date":"2018-05-17"},{"count":"0.00","date":"2018-05-18"},{"count":"0.00","date":"2018-05-19"},{"count":"9","date":"2018-05-20"},{"count":"46","date":"2018-05-21"}]}
         * anchor : {"anchor_name":"A  朱大美_","register_id":"","avatar_url":"","guild_name":"菠萝街（coco）","platfrom_name":"菠萝街直播"}
         * total_all : {"gifts":"7084757","live_time":"331.46","added_fans":"22161"}
         * total_data : {"gifts":{"name":"魅力值增长","data":"60399"},"live_time":{"name":"开播时长","data":"2.00"},"added_fans":{"name":"粉丝增长","data":"46"}}
         * start_date : 2018-05-21
         * end_date : 2018-05-21
         */

        private ChartDataBean chart_data;
        private AnchorBean anchor;
        private TotalAllBean total_all;
        private TotalDataBean total_data;
        private String start_date;
        private String end_date;

        public ChartDataBean getChart_data() {
            return chart_data;
        }

        public void setChart_data(ChartDataBean chart_data) {
            this.chart_data = chart_data;
        }

        public AnchorBean getAnchor() {
            return anchor;
        }

        public void setAnchor(AnchorBean anchor) {
            this.anchor = anchor;
        }

        public TotalAllBean getTotal_all() {
            return total_all;
        }

        public void setTotal_all(TotalAllBean total_all) {
            this.total_all = total_all;
        }

        public TotalDataBean getTotal_data() {
            return total_data;
        }

        public void setTotal_data(TotalDataBean total_data) {
            this.total_data = total_data;
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

        public static class ChartDataBean {
            private List<SortBean> sort;
            private List<GiftsBean> gifts;
            private List<GiftsBean> income_gift;
            private List<LiveTimeBean> live_time;
            private List<AddedFansBean> income_diamond;
            private List<AddedFansBean> added_fans;

            public List<GiftsBean> getIncome_gift() {
                return income_gift;
            }

            public void setIncome_gift(List<GiftsBean> income_gift) {
                this.income_gift = income_gift;
            }

            public List<AddedFansBean> getIncome_diamond() {
                return income_diamond;
            }

            public void setIncome_diamond(List<AddedFansBean> income_diamond) {
                this.income_diamond = income_diamond;
            }

            public List<SortBean> getSort() {
                return sort;
            }

            public void setSort(List<SortBean> sort) {
                this.sort = sort;
            }

            public List<GiftsBean> getGifts() {
                return gifts;
            }

            public void setGifts(List<GiftsBean> gifts) {
                this.gifts = gifts;
            }

            public List<LiveTimeBean> getLive_time() {
                return live_time;
            }

            public void setLive_time(List<LiveTimeBean> live_time) {
                this.live_time = live_time;
            }

            public List<AddedFansBean> getAdded_fans() {
                return added_fans;
            }

            public void setAdded_fans(List<AddedFansBean> added_fans) {
                this.added_fans = added_fans;
            }

            public static class SortBean {
                /**
                 * date : 2018-05-15
                 * count : 1
                 */

                private String date;
                private int count;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }
            }

            public static class GiftsBean {
                /**
                 * count : 146733
                 * date : 2018-05-15
                 */

                private String count;
                private String date;

                public String getCount() {
                    return count;
                }

                public void setCount(String count) {
                    this.count = count;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }
            }

            public static class LiveTimeBean {
                /**
                 * count : 2.53
                 * date : 2018-05-15
                 */

                private String count;
                private String date;

                public String getCount() {
                    return count;
                }

                public void setCount(String count) {
                    this.count = count;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }
            }

            public static class AddedFansBean {
                /**
                 * count : 70
                 * date : 2018-05-15
                 */

                private String count;
                private String date;

                public String getCount() {
                    return count;
                }

                public void setCount(String count) {
                    this.count = count;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }
            }
        }

        public static class AnchorBean {
            /**
             * anchor_name : A  朱大美_
             * register_id :
             * avatar_url :
             * guild_name : 菠萝街（coco）
             * platfrom_name : 菠萝街直播
             */

            private String anchor_name;
            private String register_id;
            private String avatar_url;
            private String guild_name;
            private String platfrom_name;

            public String getAnchor_name() {
                return anchor_name;
            }

            public void setAnchor_name(String anchor_name) {
                this.anchor_name = anchor_name;
            }

            public String getRegister_id() {
                return register_id;
            }

            public void setRegister_id(String register_id) {
                this.register_id = register_id;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
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

        public static class TotalAllBean {
            /**
             * gifts : 7084757
             * live_time : 331.46
             * added_fans : 22161
             */

            private String gifts;
            private String live_time;
            private String added_fans;

            public String getGifts() {
                return gifts;
            }

            public void setGifts(String gifts) {
                this.gifts = gifts;
            }

            public String getLive_time() {
                return live_time;
            }

            public void setLive_time(String live_time) {
                this.live_time = live_time;
            }

            public String getAdded_fans() {
                return added_fans;
            }

            public void setAdded_fans(String added_fans) {
                this.added_fans = added_fans;
            }
        }

        public static class TotalDataBean {
            /**
             * gifts : {"name":"魅力值增长","data":"60399"}
             * live_time : {"name":"开播时长","data":"2.00"}
             * added_fans : {"name":"粉丝增长","data":"46"}
             */

            private GiftsBeanX gifts;
            private LiveTimeBeanX live_time;
            private AddedFansBeanX added_fans;
            private giftsmouthBean giftsmouth;
            private GiftsBeanX income_gift;
            private AddedFansBeanX income_diamond;

            public GiftsBeanX getIncome_gift() {
                return income_gift;
            }

            public void setIncome_gift(GiftsBeanX income_gift) {
                this.income_gift = income_gift;
            }

            public AddedFansBeanX getIncome_diamond() {
                return income_diamond;
            }

            public void setIncome_diamond(AddedFansBeanX income_diamond) {
                this.income_diamond = income_diamond;
            }

            public giftsmouthBean getGiftsmouth() {
                return giftsmouth;
            }

            public void setGiftsmouth(giftsmouthBean giftsmouth) {
                this.giftsmouth = giftsmouth;
            }

            public GiftsBeanX getGifts() {
                return gifts;
            }

            public void setGifts(GiftsBeanX gifts) {
                this.gifts = gifts;
            }

            public LiveTimeBeanX getLive_time() {
                return live_time;
            }

            public void setLive_time(LiveTimeBeanX live_time) {
                this.live_time = live_time;
            }

            public AddedFansBeanX getAdded_fans() {
                return added_fans;
            }

            public void setAdded_fans(AddedFansBeanX added_fans) {
                this.added_fans = added_fans;
            }

            public static class GiftsBeanX {
                /**
                 * name : 魅力值增长
                 * data : 60399
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
                 * name : 本月魅力值
                 * data : 60399
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


            public static class LiveTimeBeanX {
                /**
                 * name : 开播时长
                 * data : 2.00
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

            public static class AddedFansBeanX {
                /**
                 * name : 粉丝增长
                 * data : 46
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

}
