package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/9.  约聊功能   会长抢主播功能
 */

public class GrabAnchorBean implements Serializable {


    /**
     * xf_bd : 20
     * xh_number : 4
     * usable_bd : 55492
     * is_activity : 1
     * activity : {"id":1,"start_time":1550160000,"end_time":1551369599,"g_price":20,"number_days":10,"status":1,"create_time":1550223276}
     */

    private int xf_bd;
    private int xh_number;
    private int usable_bd;
    private int is_activity;
    private ActivityBean activity;

    public int getXf_bd() {
        return xf_bd;
    }

    public void setXf_bd(int xf_bd) {
        this.xf_bd = xf_bd;
    }

    public int getXh_number() {
        return xh_number;
    }

    public void setXh_number(int xh_number) {
        this.xh_number = xh_number;
    }

    public int getUsable_bd() {
        return usable_bd;
    }

    public void setUsable_bd(int usable_bd) {
        this.usable_bd = usable_bd;
    }

    public int getIs_activity() {
        return is_activity;
    }

    public void setIs_activity(int is_activity) {
        this.is_activity = is_activity;
    }

    public ActivityBean getActivity() {
        return activity;
    }

    public void setActivity(ActivityBean activity) {
        this.activity = activity;
    }

    public static class ActivityBean {
        /**
         * id : 1
         * start_time : 1550160000
         * end_time : 1551369599
         * g_price : 20
         * number_days : 10
         * status : 1
         * create_time : 1550223276
         */

        private int id;
        private int start_time;
        private int end_time;
        private int g_price;
        private int number_days;
        private int status;
        private int create_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStart_time() {
            return start_time;
        }

        public void setStart_time(int start_time) {
            this.start_time = start_time;
        }

        public int getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }

        public int getG_price() {
            return g_price;
        }

        public void setG_price(int g_price) {
            this.g_price = g_price;
        }

        public int getNumber_days() {
            return number_days;
        }

        public void setNumber_days(int number_days) {
            this.number_days = number_days;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }
    }
}
