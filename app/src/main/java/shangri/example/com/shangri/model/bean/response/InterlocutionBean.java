package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class InterlocutionBean implements Serializable {
        /**
         * current_page : 1
         * total_page : 1
         * qalist : [{"id":"8","question":"【精品】下播以后怎么和粉丝守护去沟通维护？","answer":"直播是让粉丝能与主播更好的沟通线下维护至关重要，线下维护要与粉丝做朋友平时多关心，多聊天也可以再其生日或者节日送上自己的一份小礼物让其知道你们是真正得朋友。","browse_amount":"8","good_amount":"1","bad_amount":"0","isgood":0,"isbad":0},{"id":"7","question":"直播中误把粉丝禁言该如何处理？","answer":"遇到这种情况要第一时间与粉丝道歉，不要让粉丝有反感心里，其次告知管理员如无法解决可先行下播处理。","browse_amount":"10","good_amount":"1","bad_amount":"0","isgood":0,"isbad":0},{"id":"3","question":"该不该禁言黑粉？","answer":"如果人气不好的情况下可以不禁，小黑粉你俩互怼的时候可以吸引游客的眼球","browse_amount":"69","good_amount":"3","bad_amount":"0","isgood":0,"isbad":0},{"id":"6","question":"面对黑粉攻击该怎么做？","answer":"方法有很多，一定要对症下药。\r\n\r\n1.无辜型\r\n\u201c感谢大家的支持\u201d \r\n\u201c黑粉也是粉，谢谢你们愿意留在我的直播间陪伴我~\u201d\r\n \u201c虽然他骂我，但是一直留在我的直播间，说明还是喜欢我的\u201d\r\n\u201c他只是想引起大家的注意而已\u201d\r\n \r\n2霸气示威型\r\n \r\n\u201c嘴巴放干净点噢\u201d \r\n数次无效后\u2026\u2026 \u201c管理员，把他踢出去\u201c \r\n\r\n小说小闹可以当成是玩笑，但是一定要有自己的尊严，玩笑开的次数太多或者太过分的，可以让管理员直接拉黑，这也算是为创建绿色直播贡献自己的力量了。\r\n\r\n3完全无视型\r\n\r\n\u201c我粉丝那么多！弹幕那么多！根本看不过来！直接无视黑粉的存在！我能看到的只有车队、舰队、火箭雨！\u201d \r\n面对智商低的人完全可以不搭理，让他自己自娱自乐说去吧，你能做的只有把自己变得更好。\r\n\r\n有黑粉并不代表自己不够优秀，有人黑你说明有更多的人开始认识你了，当你刚做直播的时候有人黑你吗？有黑粉说明你已经很厉害了，所以黑粉并不代表是坏事，一定要乐观面对，直播的路上不可少的就是自己乐观的心态。乐观做事，乐观生活。","browse_amount":"72","good_amount":"3","bad_amount":"0","isgood":0,"isbad":0},{"id":"5","question":"开播前要做哪些准备工作？","answer":"开播前一定要做好准备工作，有一句话说的好，万事俱备只欠东风。\r\n\r\n1、视听效果调试好，包括灯光效果是否明亮，摄像头成像是否清晰，稳定，声卡效果是否调试好，无杂音，麦克风是否清晰。调试好这些，是保证你能给观众带去良好的视听感受的必要保证。\r\n2、开播你都讲些什么话题，你都有准备好了吗？\r\n3、平台环境熟悉好没有？礼物价格，分成比例，工资结算周期，结算方式，直播注意事项，平台直播政策，公会政策（如果你加了公会的话）是什么样的，都有哪些需要注意的地方等等一系列问题。\r\n希望在你那么努力之后会得到你想要的满意结果。","browse_amount":"24","good_amount":"1","bad_amount":"1","isgood":0,"isbad":0},{"id":"4","question":"主播的几点禁忌？","answer":"冒然与粉丝见面，私下与粉丝守护有利益上得往来，传递负面的情绪舆论给粉丝，诋毁其他主播。","browse_amount":"21","good_amount":"2","bad_amount":"0","isgood":0,"isbad":0}]
         */

        private String current_page;
        private int total_page;
        private List<QalistBean> qalist;

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

        public List<QalistBean> getQalist() {
            return qalist;
        }

        public void setQalist(List<QalistBean> qalist) {
            this.qalist = qalist;
        }

        public static class QalistBean implements Serializable {
            /**
             * id : 8
             * question : 【精品】下播以后怎么和粉丝守护去沟通维护？
             * answer : 直播是让粉丝能与主播更好的沟通线下维护至关重要，线下维护要与粉丝做朋友平时多关心，多聊天也可以再其生日或者节日送上自己的一份小礼物让其知道你们是真正得朋友。
             * browse_amount : 8
             * good_amount : 1
             * bad_amount : 0
             * isgood : 0
             * isbad : 0
             */

            private String id;
            private String question;
            private String answer;
            private String browse_amount;
            private String good_amount;
            private String bad_amount;
            private int isgood;
            private int isbad;
            private int register_collect;
            private String collect_amount;

            public String getCollect_amount() {
                return collect_amount;
            }

            public void setCollect_amount(String collect_amount) {
                this.collect_amount = collect_amount;
            }

            public int getRegister_collect() {
                return register_collect;
            }

            public void setRegister_collect(int register_collect) {
                this.register_collect = register_collect;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getQuestion() {
                return question;
            }

            public void setQuestion(String question) {
                this.question = question;
            }

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }

            public String getBrowse_amount() {
                return browse_amount;
            }

            public void setBrowse_amount(String browse_amount) {
                this.browse_amount = browse_amount;
            }

            public String getGood_amount() {
                return good_amount;
            }

            public void setGood_amount(String good_amount) {
                this.good_amount = good_amount;
            }

            public String getBad_amount() {
                return bad_amount;
            }

            public void setBad_amount(String bad_amount) {
                this.bad_amount = bad_amount;
            }

            public int getIsgood() {
                return isgood;
            }

            public void setIsgood(int isgood) {
                this.isgood = isgood;
            }

            public int getIsbad() {
                return isbad;
            }

            public void setIsbad(int isbad) {
                this.isbad = isbad;
            }
        }
}
