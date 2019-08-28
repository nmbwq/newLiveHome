package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/13.
 */

public class DetailBean implements Serializable{
        /**
         * detail_data : [{"gifts":"376516","live_time":"22.28","added_fans":"342","date":"2018-01-12","anchors":15},{"gifts":"2296824","live_time":"129.03","added_fans":"1828","date":"2018-01-11","anchors":55},{"gifts":"1277980","live_time":"132.54","added_fans":"1363","date":"2018-01-10","anchors":55},{"gifts":"1441951","live_time":"153.07","added_fans":"2796","date":"2018-01-09","anchors":55},{"gifts":"705911","live_time":"131.16","added_fans":"2764","date":"2018-01-08","anchors":60},{"gifts":"1261925","live_time":"135.20","added_fans":"2779","date":"2018-01-07","anchors":59},{"gifts":"1107930","live_time":"158.87","added_fans":"3067","date":"2018-01-06","anchors":61}]
         * start_date : 2018-01-12
         * end_date : 2018-01-12
         */

        private String start_date;
        private String end_date;
        private List<DetailDataBean> detail_data;

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

        public List<DetailDataBean> getDetail_data() {
            return detail_data;
        }

        public void setDetail_data(List<DetailDataBean> detail_data) {
            this.detail_data = detail_data;
        }

        public static class DetailDataBean {
            /**
             * gifts : 376516
             * live_time : 22.28
             * added_fans : 342
             * date : 2018-01-12
             * anchors : 15
             */

            private String gifts;
            private String live_time;
            private String added_fans;
            private String date;
            private String anchors;

            private String income_gift;
            private String income_diamond;


            public String getIncome_diamond() {
                return income_diamond;
            }

            public void setIncome_diamond(String income_diamond) {
                this.income_diamond = income_diamond;
            }
            public String getIncome_gift() {
                return income_gift;
            }

            public void setIncome_gift(String income_gift) {
                this.income_gift = income_gift;
            }
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

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getAnchors() {
                return anchors;
            }

            public void setAnchors(String anchors) {
                this.anchors = anchors;
            }
        }
}
