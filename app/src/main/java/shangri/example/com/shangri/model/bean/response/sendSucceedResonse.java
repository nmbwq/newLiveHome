package shangri.example.com.shangri.model.bean.response;

import shangri.example.com.shangri.model.bean.request.BaseBeen;

/**
 * Created by admin on 2017/12/26.
 */

public class sendSucceedResonse extends BaseBeen {
    //    是否已沟通 1是 0否
    public int is_linkup;
    //    约聊ID 为0时不跳转约聊界面
    public int chat_id;

    //    剩余次数
    public int count;
    //总次数
    public int all_total;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getAll_total() {
        return all_total;
    }

    public void setAll_total(int all_total) {
        this.all_total = all_total;
    }

    public sendSucceedResonse(int is_linkup, int chat_id) {
        this.is_linkup = is_linkup;
        this.chat_id = chat_id;
    }


    public sendSucceedResonse() {
    }

    public int getIs_linkup() {
        return is_linkup;
    }

    public void setIs_linkup(int is_linkup) {
        this.is_linkup = is_linkup;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }
}
