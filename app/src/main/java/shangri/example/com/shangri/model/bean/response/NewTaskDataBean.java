package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/3.
 */

public class NewTaskDataBean implements Serializable {


    private int current_page;
    private int total_page;
    private List<TasksBean> tasks;

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

    public static class TasksBean implements Serializable {
        /**
         * id : 3
         * creater_id : 267
         * guild_name : 菠萝街（coco）
         * platfrom_name : 菠萝街直播
         * member_time : 1528601566
         * memner_status : 2
         * theme : 菠萝姐的
         * content : 第三方的手
         * expect_aims : 800000.00
         * hide_expect : 0
         * is_alert : 0
         * start_time : 2018-05-01 00:00
         * end_time : 2018-05-19 00:00
         * task_status : 1
         * join : []
         * no_join : [{"id":47,"nickname":"13185025851","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497090394139368.jpg","target_value":60000,"reality_value":0}]
         * over_list : []
         * ing_list : []
         * type : 2
         * creater_avatar :
         */

        private int id;
        private int creater_id;
        private String guild_name;
        private String platfrom_name;
        private String member_time;
        private int memner_status;
        private String theme;
        private String content;
        private String expect_aims;
        private int hide_expect;
        private int is_alert;
        private String start_time;
        private String end_time;
        private int task_status;
        private int type;
        private String creater_avatar;
        private List<NoJoinBean> join;
        private List<NoJoinBean> no_join;
        private List<NoJoinBean> over_list;
        private List<NoJoinBean> ing_list;
        private int status;
        private int task_type;
        private String icon_url;
        private String time_mask;
        private int percent;
        private String creater_name;
        //        目标量
        private String target_value;
        //        已完成量
        private String reality_value;

        public int getTask_type() {
            return task_type;
        }

        public void setTask_type(int task_type) {
            this.task_type = task_type;
        }

        public TasksBean() {
        }

        public String getTarget_value() {
            return target_value;
        }

        public void setTarget_value(String target_value) {
            this.target_value = target_value;
        }

        public String getReality_value() {
            return reality_value;
        }

        public void setReality_value(String reality_value) {
            this.reality_value = reality_value;
        }

        public String getCreater_name() {
            return creater_name;
        }

        public void setCreater_name(String creater_name) {
            this.creater_name = creater_name;
        }

        public String getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(String icon_url) {
            this.icon_url = icon_url;
        }

        public String getTime_mask() {
            return time_mask;
        }

        public void setTime_mask(String time_mask) {
            this.time_mask = time_mask;
        }

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCreater_id() {
            return creater_id;
        }

        public void setCreater_id(int creater_id) {
            this.creater_id = creater_id;
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

        public String getMember_time() {
            return member_time;
        }

        public void setMember_time(String member_time) {
            this.member_time = member_time;
        }

        public int getMemner_status() {
            return memner_status;
        }

        public void setMemner_status(int memner_status) {
            this.memner_status = memner_status;
        }

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getExpect_aims() {
            return expect_aims;
        }

        public void setExpect_aims(String expect_aims) {
            this.expect_aims = expect_aims;
        }

        public int getHide_expect() {
            return hide_expect;
        }

        public void setHide_expect(int hide_expect) {
            this.hide_expect = hide_expect;
        }

        public int getIs_alert() {
            return is_alert;
        }

        public void setIs_alert(int is_alert) {
            this.is_alert = is_alert;
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

        public int getTask_status() {
            return task_status;
        }

        public void setTask_status(int task_status) {
            this.task_status = task_status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCreater_avatar() {
            return creater_avatar;
        }

        public void setCreater_avatar(String creater_avatar) {
            this.creater_avatar = creater_avatar;
        }

        public List<NoJoinBean> getJoin() {
            return join;
        }

        public void setJoin(List<NoJoinBean> join) {
            this.join = join;
        }

        public List<NoJoinBean> getNo_join() {
            return no_join;
        }

        public void setNo_join(List<NoJoinBean> no_join) {
            this.no_join = no_join;
        }

        public List<NoJoinBean> getOver_list() {
            return over_list;
        }

        public void setOver_list(List<NoJoinBean> over_list) {
            this.over_list = over_list;
        }

        public List<NoJoinBean> getIng_list() {
            return ing_list;
        }

        public void setIng_list(List<NoJoinBean> ing_list) {
            this.ing_list = ing_list;
        }

        public static class NoJoinBean implements Serializable {
            /**
             * id : 47
             * nickname : 13185025851
             * avatar_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497090394139368.jpg
             * target_value : 60000
             * reality_value : 0
             */

            private int id;
            private String nickname;
            private String avatar_url;
            private String target_value;
            private String reality_value;
            private int percent;

            public int getPercent() {
                return percent;
            }

            public void setPercent(int percent) {
                this.percent = percent;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public String getTarget_value() {
                return target_value;
            }

            public void setTarget_value(String target_value) {
                this.target_value = target_value;
            }

            public String getReality_value() {
                return reality_value;
            }

            public void setReality_value(String reality_value) {
                this.reality_value = reality_value;
            }
        }
    }

}

