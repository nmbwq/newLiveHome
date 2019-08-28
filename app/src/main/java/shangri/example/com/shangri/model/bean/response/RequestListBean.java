package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class RequestListBean implements Serializable {


    private String expire_time;


    private String custom_vip_tel;
    private String custom_vip_wx;
    private String custom_tel;
    private String custom_wx;

    private List<QaBean> qa;

    public String getCustom_vip_tel() {
        return custom_vip_tel;
    }

    public void setCustom_vip_tel(String custom_vip_tel) {
        this.custom_vip_tel = custom_vip_tel;
    }

    public String getCustom_vip_wx() {
        return custom_vip_wx;
    }

    public void setCustom_vip_wx(String custom_vip_wx) {
        this.custom_vip_wx = custom_vip_wx;
    }

    public String getCustom_tel() {
        return custom_tel;
    }

    public void setCustom_tel(String custom_tel) {
        this.custom_tel = custom_tel;
    }

    public String getCustom_wx() {
        return custom_wx;
    }

    public void setCustom_wx(String custom_wx) {
        this.custom_wx = custom_wx;
    }

    public String getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(String expire_time) {
        this.expire_time = expire_time;
    }

    public List<QaBean> getQa() {
        return qa;
    }

    public void setQa(List<QaBean> qa) {
        this.qa = qa;
    }

    public static class QaBean implements Serializable {
        /**
         * id : 2
         * question : 213213
         * answer : 撒旦水电费双方都
         */

        private int id;
        private String question;
        private String answer;

        public int getId() {
            return id;
        }

        public void setId(int id) {
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
    }
}



