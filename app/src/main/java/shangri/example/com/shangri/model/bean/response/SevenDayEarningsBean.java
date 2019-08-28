package shangri.example.com.shangri.model.bean.response;

import java.util.List;

import shangri.example.com.shangri.model.bean.request.BaseBeen;

/**
 * Created by Administrator on 2017/12/30.
 */

public class SevenDayEarningsBean extends BaseBeen {



        private List<EarningsBean> earnings;

        public List<EarningsBean> getEarnings() {
            return earnings;
        }

        public void setEarnings(List<EarningsBean> earnings) {
            this.earnings = earnings;
        }

        public static class EarningsBean {
            /**
             * show_date : 2018-12-05
             * operate_num : 0
             */

            private String show_date;
            private String operate_num;

            public String getShow_date() {
                return show_date;
            }

            public void setShow_date(String show_date) {
                this.show_date = show_date;
            }

            public String getOperate_num() {
                return operate_num;
            }

            public void setOperate_num(String operate_num) {
                this.operate_num = operate_num;
            }
        }
}
