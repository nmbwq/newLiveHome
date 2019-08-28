package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/4.
 */

public class upgradePackageBean implements Serializable {

    /**
     * is_already_full : 0
     * usable_bd : 11755
     * state : -购买成功后立即生效，时间到期后自动下架
     * -确保账户余额充足，方可购买该服务
     * -位置顺序以实际浏览人数为参照
     * -购买位置限定前三个，当最早购买者时间到达，位置空出 后，方可继续购买
     * bd_ratio : 10
     * list : [{"id":1,"hf_bd":35,"hold_time":6},{"id":2,"hf_bd":60,"hold_time":12},{"id":3,"hf_bd":100,"hold_time":24},{"id":4,"hf_bd":180,"hold_time":48}]
     */

    private int is_already_full;
    private int usable_bd;
    private String state;
    private int bd_ratio;
    private List<ListBean> list;

    public int getIs_already_full() {
        return is_already_full;
    }

    public void setIs_already_full(int is_already_full) {
        this.is_already_full = is_already_full;
    }

    public int getUsable_bd() {
        return usable_bd;
    }

    public void setUsable_bd(int usable_bd) {
        this.usable_bd = usable_bd;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getBd_ratio() {
        return bd_ratio;
    }

    public void setBd_ratio(int bd_ratio) {
        this.bd_ratio = bd_ratio;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1
         * hf_bd : 35
         * hold_time : 6
         */

        private int id;
        private int hf_bd;
        private int hold_time;
        boolean isClick;

        public ListBean(boolean isClick) {
            this.isClick = isClick;
        }

        public boolean isClick() {
            return isClick;
        }

        public void setClick(boolean click) {
            isClick = click;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getHf_bd() {
            return hf_bd;
        }

        public void setHf_bd(int hf_bd) {
            this.hf_bd = hf_bd;
        }

        public int getHold_time() {
            return hold_time;
        }

        public void setHold_time(int hold_time) {
            this.hold_time = hold_time;
        }
    }
}
