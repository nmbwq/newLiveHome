package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/6.
 */

public class MyTaskBean implements Serializable {


//        private AnchorBean anchor;
        private int current_page;
        private int total_page;
        private List<TasksBean> tasks;

//        public AnchorBean getAnchor() {
//            return anchor;
//        }

//        public void setAnchor(AnchorBean anchor) {
//            this.anchor = anchor;
//        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getTotal_page() {
            return total_page;
        }

        public void setTotal_page(int total_page) {
            this.total_page = total_page;
        }

        public List<TasksBean> getTasks() {
            return tasks;
        }

        public void setTasks(List<TasksBean> tasks) {
            this.tasks = tasks;
        }

//        public static class AnchorBean {
//            /**
//             * anchor_name : ?盾上加鹿?
//             * guild_name : 菠萝街（coco）
//             */
//
//            private String anchor_name;
//            private String guild_name;
//
//            public String getAnchor_name() {
//                return anchor_name;
//            }
//
//            public void setAnchor_name(String anchor_name) {
//                this.anchor_name = anchor_name;
//            }
//
//            public String getGuild_name() {
//                return guild_name;
//            }
//
//            public void setGuild_name(String guild_name) {
//                this.guild_name = guild_name;
//            }
//        }

        public static class TasksBean {
            /**
             * theme : 月底冲刺
             * self_aims : 300.00
             * start_time : 1516959960
             * end_time : 1517046300
             * uid : 7300783
             * guild_id : 1234548255
             * platfrom_name : 菠萝街直播
             * guild_name : 菠萝街（coco）
             * aims_name : 魅力值增长
             * total_aims : 0
             */

            private String theme;
            private String self_aims;
            private String start_time;
            private String end_time;
            private String uid;
            private String guild_id;
            private String platfrom_name;
            private String guild_name;
            private String aims_name;
            private int total_aims;

            public String getTheme() {
                return theme;
            }

            public void setTheme(String theme) {
                this.theme = theme;
            }

            public String getSelf_aims() {
                return self_aims;
            }

            public void setSelf_aims(String self_aims) {
                this.self_aims = self_aims;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getGuild_id() {
                return guild_id;
            }

            public void setGuild_id(String guild_id) {
                this.guild_id = guild_id;
            }

            public String getPlatfrom_name() {
                return platfrom_name;
            }

            public void setPlatfrom_name(String platfrom_name) {
                this.platfrom_name = platfrom_name;
            }

            public String getGuild_name() {
                return guild_name;
            }

            public void setGuild_name(String guild_name) {
                this.guild_name = guild_name;
            }

            public String getAims_name() {
                return aims_name;
            }

            public void setAims_name(String aims_name) {
                this.aims_name = aims_name;
            }

            public int getTotal_aims() {
                return total_aims;
            }

            public void setTotal_aims(int total_aims) {
                this.total_aims = total_aims;
            }
        }

}
