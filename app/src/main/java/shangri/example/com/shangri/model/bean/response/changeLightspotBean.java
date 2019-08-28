package shangri.example.com.shangri.model.bean.response;

import java.util.List;

/**
 * Created by admin on 2017/12/25.
 */

public class changeLightspotBean {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * name : 反倒是
         */

        private String name;
        boolean isclick;

        public boolean isIsclick() {
            return isclick;
        }

        public void setIsclick(boolean isclick) {
            this.isclick = isclick;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
