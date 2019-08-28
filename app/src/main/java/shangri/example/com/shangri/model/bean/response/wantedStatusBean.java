package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/12/25.
 */

public class wantedStatusBean implements Serializable {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1
         * title : 准主播
         */

        private int id;
        private String title;
        boolean ISelrct;

        public boolean isISelrct() {
            return ISelrct;
        }

        public void setISelrct(boolean ISelrct) {
            this.ISelrct = ISelrct;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
