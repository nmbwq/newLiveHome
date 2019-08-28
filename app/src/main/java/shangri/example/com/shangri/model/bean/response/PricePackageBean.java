package shangri.example.com.shangri.model.bean.response;

import java.util.List;

public class PricePackageBean {


    /**
     * list : [{"id":1,"title":"提现1元","price":1,"create_time":1551321245},{"id":2,"title":"提现20元","price":20,"create_time":1551323149},{"id":3,"title":"提现30元","price":30,"create_time":1551321245},{"id":4,"title":"提现60元","price":60,"create_time":1551321245},{"id":5,"title":"提现100元","price":100,"create_time":1551323183}]
     * is_one_apply : 0
     */

    private int is_one_apply;
    private List<ListBean> list;

    public int getIs_one_apply() {
        return is_one_apply;
    }

    public void setIs_one_apply(int is_one_apply) {
        this.is_one_apply = is_one_apply;
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
         * title : 提现1元
         * price : 1
         * create_time : 1551321245
         */

        private int id;
        private String title;
        private int price;
        private int create_time;

        boolean isselect;

        public boolean isIsselect() {
            return isselect;
        }

        public void setIsselect(boolean isselect) {
            this.isselect = isselect;
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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }
    }
}
