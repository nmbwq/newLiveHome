package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/9.  约聊功能   会长抢主播功能
 */

public class GrabAnchorOrderBean implements Serializable {


    /**
     * chat_id : 1
     */

    private int chat_id;

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

}
