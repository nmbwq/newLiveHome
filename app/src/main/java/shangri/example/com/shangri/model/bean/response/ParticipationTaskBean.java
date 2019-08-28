package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/6.
 */

public class ParticipationTaskBean implements Serializable {
    /**
     * task : {"start_time":"1516087680","end_time":"1516348500","guild_name":"菠萝街（coco）","platfrom_name":"菠萝街直播"}
     * anchors : [{"register_id":3,"self_aims":"66666.00","uid":8317155,"guild_id":"1234548255","anchor_id":3443,"aims_name":"魅力值增长","total_aims":0,"anchor_name":"Coco猴子🐒"},{"register_id":37,"self_aims":"210.00","uid":9988574,"guild_id":"1234548255","anchor_id":4849,"aims_name":"魅力值增长","total_aims":0,"anchor_name":"你的鹿兮👰🏻"},{"register_id":38,"self_aims":"210.00","uid":9988574,"guild_id":"1234548255","anchor_id":4849,"aims_name":"魅力值增长","total_aims":0,"anchor_name":"你的鹿兮👰🏻"}]
     * current_page : 1
     * total_page : 1
     */

    private TaskBean task;
    private int current_page;
    private int total_page;
    private List<AnchorsBean> anchors;

    public TaskBean getTask() {
        return task;
    }

    public void setTask(TaskBean task) {
        this.task = task;
    }

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

    public List<AnchorsBean> getAnchors() {
        return anchors;
    }

    public void setAnchors(List<AnchorsBean> anchors) {
        this.anchors = anchors;
    }

    public static class TaskBean {
        /**
         * start_time : 1516087680
         * end_time : 1516348500
         * guild_name : 菠萝街（coco）
         * platfrom_name : 菠萝街直播
         */

        private String start_time;
        private String end_time;
        private String guild_name;
        private String platfrom_name;

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

    public static class AnchorsBean {
        /**
         * register_id : 3
         * self_aims : 66666.00
         * uid : 8317155
         * guild_id : 1234548255
         * anchor_id : 3443
         * aims_name : 魅力值增长
         * total_aims : 0
         * anchor_name : Coco猴子🐒
         */

        private int register_id;
        private String self_aims;
        private int uid;
        private String guild_id;
        private int anchor_id;
        private String aims_name;
        private int total_aims;
        private String anchor_name;

        public int getRegister_id() {
            return register_id;
        }

        public void setRegister_id(int register_id) {
            this.register_id = register_id;
        }

        public String getSelf_aims() {
            return self_aims;
        }

        public void setSelf_aims(String self_aims) {
            this.self_aims = self_aims;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getGuild_id() {
            return guild_id;
        }

        public void setGuild_id(String guild_id) {
            this.guild_id = guild_id;
        }

        public int getAnchor_id() {
            return anchor_id;
        }

        public void setAnchor_id(int anchor_id) {
            this.anchor_id = anchor_id;
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

        public String getAnchor_name() {
            return anchor_name;
        }

        public void setAnchor_name(String anchor_name) {
            this.anchor_name = anchor_name;
        }
    }
}
