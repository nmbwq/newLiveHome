package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class ManagementChartBean implements Serializable{


        private List<ChartDataBean> chart_data;

        public List<ChartDataBean> getChart_data() {
            return chart_data;
        }

        public void setChart_data(List<ChartDataBean> chart_data) {
            this.chart_data = chart_data;
        }

        public static class ChartDataBean {
            /**
             * count : 527364
             * date : 01-01
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
