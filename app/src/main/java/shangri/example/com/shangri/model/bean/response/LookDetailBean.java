package shangri.example.com.shangri.model.bean.response;

import java.util.List;

/**
 * Created by Administrator on 2018/1/18.
 */

public class LookDetailBean {

        /**
         * anchors : [{"belong_platfrom_uid":"6960113","guild_id":"1234548255","income_total":"57758","live_time":"3.33","added_fans":"67","income_gift":"0.00","income_diamond":"0.00","anchor_nickname":"lucky求守护"},{"belong_platfrom_uid":"9557411","guild_id":"1234548255","income_total":"41053","live_time":"3.30","added_fans":"5","income_gift":"0.00","income_diamond":"0.00","anchor_nickname":"假漂亮"},{"belong_platfrom_uid":"9796550","guild_id":"1234548255","income_total":"39860","live_time":"6.52","added_fans":"214","income_gift":"0.00","income_diamond":"0.00","anchor_nickname":"❣️点宝宝?"},{"belong_platfrom_uid":"11018290","guild_id":"1234548255","income_total":"34759","live_time":"3.20","added_fans":"119","income_gift":"0.00","income_diamond":"0.00","anchor_nickname":"Co?佛系女污"},{"belong_platfrom_uid":"8719544","guild_id":"1234548255","income_total":"32630","live_time":"2.25","added_fans":"8","income_gift":"0.00","income_diamond":"0.00","anchor_nickname":"?壁咚阿星"},{"belong_platfrom_uid":"9903535","guild_id":"1234548255","income_total":"30507","live_time":"1.10","added_fans":"3","income_gift":"0.00","income_diamond":"0.00","anchor_nickname":"空姐右右"},{"belong_platfrom_uid":"10209803","guild_id":"1234548255","income_total":"29706","live_time":"2.72","added_fans":"85","income_gift":"0.00","income_diamond":"0.00","anchor_nickname":"Coco?赵唯"},{"belong_platfrom_uid":"9988574","guild_id":"1234548255","income_total":"28118","live_time":"0.93","added_fans":"61","income_gift":"0.00","income_diamond":"0.00","anchor_nickname":"你的鹿兮??"},{"belong_platfrom_uid":"11308314","guild_id":"1234548255","income_total":"12853","live_time":"0.17","added_fans":"0","income_gift":"0.00","income_diamond":"0.00","anchor_nickname":"?冰激凌"},{"belong_platfrom_uid":"9997011","guild_id":"1234548255","income_total":"12268","live_time":"4.13","added_fans":"120","income_gift":"0.00","income_diamond":"0.00","anchor_nickname":"李木子?"}]
         * current_page : 1
         * total_page : 11
         */

        private String current_page;
        private int total_page;
        private List<AnchorsBean> anchors;

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

        public List<AnchorsBean> getAnchors() {
            return anchors;
        }

        public void setAnchors(List<AnchorsBean> anchors) {
            this.anchors = anchors;
        }

        public static class AnchorsBean {
            /**
             * belong_platfrom_uid : 6960113
             * guild_id : 1234548255
             * income_total : 57758
             * live_time : 3.33
             * added_fans : 67
             * income_gift : 0.00
             * income_diamond : 0.00
             * anchor_nickname : lucky求守护
             */

            private String belong_platfrom_uid;
            private String guild_id;
            private String income_total;
            private String live_time;
            private String added_fans;
            private String income_gift;
            private String income_diamond;
            private String anchor_nickname;

            public String getBelong_platfrom_uid() {
                return belong_platfrom_uid;
            }

            public void setBelong_platfrom_uid(String belong_platfrom_uid) {
                this.belong_platfrom_uid = belong_platfrom_uid;
            }

            public String getGuild_id() {
                return guild_id;
            }

            public void setGuild_id(String guild_id) {
                this.guild_id = guild_id;
            }

            public String getIncome_total() {
                return income_total;
            }

            public void setIncome_total(String income_total) {
                this.income_total = income_total;
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

            public String getIncome_gift() {
                return income_gift;
            }

            public void setIncome_gift(String income_gift) {
                this.income_gift = income_gift;
            }

            public String getIncome_diamond() {
                return income_diamond;
            }

            public void setIncome_diamond(String income_diamond) {
                this.income_diamond = income_diamond;
            }

            public String getAnchor_nickname() {
                return anchor_nickname;
            }

            public void setAnchor_nickname(String anchor_nickname) {
                this.anchor_nickname = anchor_nickname;
            }
        }

}
