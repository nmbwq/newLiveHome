package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/9.  约聊功能   会长抢主播功能
 */

public class JudgeGradeBean implements Serializable {


    /**
     * is_rob : 0
     */

    private int is_rob;
    private String chat_id;

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public int getIs_rob() {
        return is_rob;
    }

    public void setIs_rob(int is_rob) {
        this.is_rob = is_rob;
    }
}
