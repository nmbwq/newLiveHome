package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/3.
 */

public class TaskDataBean implements Serializable {

    /**
     * tasks : [{"task_id":"19","platfrom_name":"菠萝街直播","icon_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2017-12-11/151298472448674197.jpg","guild_id":"6876065328","guild_name":"菠萝街（RY）","create_time":"1516957753","theme":"然后然后然后然后","content":"电脑发呵呵","expect_aims":"10000000.00","start_time":"0","end_time":"0","allot_aims":0,"aims_name":"魅力值增长","total_aims":0,"anchor_count":0},{"task_id":"18","platfrom_name":"UP直播","icon_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2017-12-13/151314333441065923.jpg","guild_id":"650418055593","guild_name":"UP（猫咪）","create_time":"1516957622","theme":"提高提高","content":"曾经风风光光哈哈","expect_aims":"100000000.00","start_time":"0","end_time":"0","allot_aims":0,"aims_name":"UP币增长","total_aims":0,"anchor_count":0},{"task_id":"17","platfrom_name":"菠萝街直播","icon_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2017-12-11/151298472448674197.jpg","guild_id":"1234548255","guild_name":"菠萝街（coco）","create_time":"1516955009","theme":"活动开始","content":"看魅力值会不会增长","expect_aims":"10000.00","start_time":"1516954920","end_time":"1517045100","allot_aims":0,"aims_name":"魅力值增长","total_aims":0,"anchor_count":0},{"task_id":"15","platfrom_name":"菠萝街直播","icon_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2017-12-11/151298472448674197.jpg","guild_id":"1234548255","guild_name":"菠萝街（coco）","create_time":"1516949455","theme":"v给花花","content":"吓人洗头膏高跟鞋GV好v好v好吧好爸爸吧唧吧唧好v好v哈哈v好v好概率高GV 刚","expect_aims":"100000100.00","start_time":"1516974600","end_time":"1516974600","allot_aims":0,"aims_name":"魅力值增长","total_aims":0,"anchor_count":0},{"task_id":"14","platfrom_name":"菠萝街直播","icon_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2017-12-11/151298472448674197.jpg","guild_id":"1234548255","guild_name":"菠萝街（coco）","create_time":"1516852375","theme":"测试","content":"testjdkjdbd\n\n\n\n\n\n\n\nididifjdjf","expect_aims":"10000000.00","start_time":"1518753120","end_time":"1522813920","allot_aims":"2200.00","aims_name":"魅力值增长","total_aims":0,"anchor_count":3},{"task_id":"11","platfrom_name":"NOW直播","icon_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2017-12-13/151314328693733338.jpg","guild_id":"432418055593","guild_name":"NOW（猫咪）","create_time":"1516790966","theme":"845181","content":"151591","expect_aims":"1.50","start_time":"1516790880","end_time":"1523443740","allot_aims":"1000.00","aims_name":"收入分成增长","total_aims":0,"anchor_count":1},{"task_id":"10","platfrom_name":"菠萝街直播","icon_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2017-12-11/151298472448674197.jpg","guild_id":"6876065328","guild_name":"菠萝街（RY）","create_time":"1516789487","theme":"主题活动","content":"这里是规则哦","expect_aims":"10.00","start_time":"1517191200","end_time":"1520258400","allot_aims":0,"aims_name":"魅力值增长","total_aims":0,"anchor_count":0},{"task_id":"9","platfrom_name":"NOW直播","icon_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2017-12-13/151314328693733338.jpg","guild_id":"432418055593","guild_name":"NOW（猫咪）","create_time":"1516601506","theme":"舍得给白癜风","content":"个电风扇发布会十点半","expect_aims":"50000.00","start_time":"1516601460","end_time":"1518249300","allot_aims":"1012356.00","aims_name":"收入分成增长","total_aims":7350.79,"anchor_count":2},{"task_id":"7","platfrom_name":"小米直播","icon_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2017-12-11/151298468491184578.jpg","guild_id":"53425479107","guild_name":"小米（猫咪）","create_time":"1516601462","theme":"是否是豆腐干不掉头发够不到人不","content":"风格暗示如果摄入发给版本啊怪不得说二哥而过的分别是大法官白癜风","expect_aims":"10000000.00","start_time":"1516601400","end_time":"1516601400","allot_aims":0,"aims_name":"收益星票增长","total_aims":0,"anchor_count":0},{"task_id":"5","platfrom_name":"NOW直播","icon_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2017-12-13/151314328693733338.jpg","guild_id":"432418055593","guild_name":"NOW（猫咪）","create_time":"1516342164","theme":"二阿哥我让个任务","content":"服个软歌曲绕过去而改变vewqrg","expect_aims":"2000000.00","start_time":"1516342080","end_time":"1516153500","allot_aims":0,"aims_name":"收入分成增长","total_aims":0,"anchor_count":0},{"task_id":"4","platfrom_name":"小米直播","icon_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2017-12-11/151298468491184578.jpg","guild_id":"53425479107","guild_name":"小米（猫咪）","create_time":"1516342128","theme":"哈哈哈哈哈","content":"打小三说的都是你说的介绍的当时说的飞吧sad生娃","expect_aims":"10000.00","start_time":"1516342080","end_time":"1516342080","allot_aims":0,"aims_name":"收益星票增长","total_aims":0,"anchor_count":0}]
     * current_page : 1
     * total_page : 1
     */

    private String current_page;
    private int total_page;
    private List<TasksBean> tasks;

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

    public List<TasksBean> getTasks() {
        return tasks;
    }

    public void setTasks(List<TasksBean> tasks) {
        this.tasks = tasks;
    }

    public static class TasksBean implements Serializable {
        /**
         * task_id : 19
         * platfrom_name : 菠萝街直播
         * icon_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2017-12-11/151298472448674197.jpg
         * guild_id : 6876065328
         * guild_name : 菠萝街（RY）
         * create_time : 1516957753
         * theme : 然后然后然后然后
         * content : 电脑发呵呵
         * expect_aims : 10000000.00
         * start_time : 0
         * end_time : 0
         * allot_aims : 0
         * aims_name : 魅力值增长
         * total_aims : 0
         * anchor_count : 0
         */

        private String task_id;
        private String platfrom_name;
        private String icon_url;
        private String guild_id;
        private String guild_name;
        private String create_time;
        private String theme;
        private String content;
        private String expect_aims;
        private String start_time;
        private String end_time;
        private String allot_aims;
        private String aims_name;
        private String total_aims;
        private String anchor_count;
        private String self_aims;

        public String getSelf_aims() {
            return self_aims;
        }

        public void setSelf_aims(String self_aims) {
            this.self_aims = self_aims;
        }

        public String getTask_id() {
            return task_id;
        }

        public void setTask_id(String task_id) {
            this.task_id = task_id;
        }

        public String getPlatfrom_name() {
            return platfrom_name;
        }

        public void setPlatfrom_name(String platfrom_name) {
            this.platfrom_name = platfrom_name;
        }

        public String getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(String icon_url) {
            this.icon_url = icon_url;
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

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
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

        public String getAllot_aims() {
            return allot_aims;
        }

        public void setAllot_aims(String allot_aims) {
            this.allot_aims = allot_aims;
        }

        public String getAims_name() {
            return aims_name;
        }

        public void setAims_name(String aims_name) {
            this.aims_name = aims_name;
        }

        public String getTotal_aims() {
            return total_aims;
        }

        public void setTotal_aims(String total_aims) {
            this.total_aims = total_aims;
        }

        public String getAnchor_count() {
            return anchor_count;
        }

        public void setAnchor_count(String anchor_count) {
            this.anchor_count = anchor_count;
        }
    }

}
