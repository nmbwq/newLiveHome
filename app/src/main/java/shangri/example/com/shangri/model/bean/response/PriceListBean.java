package shangri.example.com.shangri.model.bean.response;

import java.util.List;

import shangri.example.com.shangri.model.bean.request.BaseBeen;

/**
 * Created by Administrator on 2017/12/30.
 */

public class PriceListBean extends BaseBeen {



        /**
         * list : {"current_page":1,"data":[{"title":"提现","operate_num":0.2,"status":"3","create_time":"1544258800","description":"发生的发生过"},{"title":"提现","operate_num":100,"status":"2","create_time":"1543820919","description":""},{"title":"提现","operate_num":0.1,"status":"2","create_time":"1543818471","description":""},{"title":"提现","operate_num":0.1,"status":"2","create_time":"1543816596","description":""},{"title":"提现","operate_num":10,"status":"2","create_time":"1543660356","description":""},{"title":"提现","operate_num":7,"status":"2","create_time":"1542772424","description":""},{"title":"提现","operate_num":7,"status":"2","create_time":"1542772424","description":""},{"title":"提现","operate_num":7,"status":"2","create_time":"1542772424","description":""},{"title":"提现","operate_num":7,"status":"2","create_time":"1542772423","description":""},{"title":"提现","operate_num":7,"status":"2","create_time":"1542772423","description":""},{"title":"提现","operate_num":11,"status":"2","create_time":"1542278498","description":""},{"title":"提现","operate_num":11,"status":"2","create_time":"1542278498","description":""}],"first_page_url":"http://test.ilivehome.net/api/invitation/user/price/list?page=1","from":1,"last_page":3,"last_page_url":"http://test.ilivehome.net/api/invitation/user/price/list?page=3","next_page_url":"http://test.ilivehome.net/api/invitation/user/price/list?page=2","path":"http://test.ilivehome.net/api/invitation/user/price/list","per_page":12,"prev_page_url":null,"to":12,"total":26}
         */

        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * current_page : 1
             * data : [{"title":"提现","operate_num":0.2,"status":"3","create_time":"1544258800","description":"发生的发生过"},{"title":"提现","operate_num":100,"status":"2","create_time":"1543820919","description":""},{"title":"提现","operate_num":0.1,"status":"2","create_time":"1543818471","description":""},{"title":"提现","operate_num":0.1,"status":"2","create_time":"1543816596","description":""},{"title":"提现","operate_num":10,"status":"2","create_time":"1543660356","description":""},{"title":"提现","operate_num":7,"status":"2","create_time":"1542772424","description":""},{"title":"提现","operate_num":7,"status":"2","create_time":"1542772424","description":""},{"title":"提现","operate_num":7,"status":"2","create_time":"1542772424","description":""},{"title":"提现","operate_num":7,"status":"2","create_time":"1542772423","description":""},{"title":"提现","operate_num":7,"status":"2","create_time":"1542772423","description":""},{"title":"提现","operate_num":11,"status":"2","create_time":"1542278498","description":""},{"title":"提现","operate_num":11,"status":"2","create_time":"1542278498","description":""}]
             * first_page_url : http://test.ilivehome.net/api/invitation/user/price/list?page=1
             * from : 1
             * last_page : 3
             * last_page_url : http://test.ilivehome.net/api/invitation/user/price/list?page=3
             * next_page_url : http://test.ilivehome.net/api/invitation/user/price/list?page=2
             * path : http://test.ilivehome.net/api/invitation/user/price/list
             * per_page : 12
             * prev_page_url : null
             * to : 12
             * total : 26
             */

            private int current_page;
            private String first_page_url;
            private int from;
            private int last_page;
            private String last_page_url;
            private String next_page_url;
            private String path;
            private int per_page;
            private Object prev_page_url;
            private int to;
            private int total;
            private List<DataBean> data;

            public int getCurrent_page() {
                return current_page;
            }

            public void setCurrent_page(int current_page) {
                this.current_page = current_page;
            }

            public String getFirst_page_url() {
                return first_page_url;
            }

            public void setFirst_page_url(String first_page_url) {
                this.first_page_url = first_page_url;
            }

            public int getFrom() {
                return from;
            }

            public void setFrom(int from) {
                this.from = from;
            }

            public int getLast_page() {
                return last_page;
            }

            public void setLast_page(int last_page) {
                this.last_page = last_page;
            }

            public String getLast_page_url() {
                return last_page_url;
            }

            public void setLast_page_url(String last_page_url) {
                this.last_page_url = last_page_url;
            }

            public String getNext_page_url() {
                return next_page_url;
            }

            public void setNext_page_url(String next_page_url) {
                this.next_page_url = next_page_url;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public int getPer_page() {
                return per_page;
            }

            public void setPer_page(int per_page) {
                this.per_page = per_page;
            }

            public Object getPrev_page_url() {
                return prev_page_url;
            }

            public void setPrev_page_url(Object prev_page_url) {
                this.prev_page_url = prev_page_url;
            }

            public int getTo() {
                return to;
            }

            public void setTo(int to) {
                this.to = to;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * title : 提现
                 * operate_num : 0.2
                 * status : 3
                 * create_time : 1544258800
                 * description : 发生的发生过
                 */

                private String title;
                private double operate_num;
                private String status;
                private String create_time;
                private String description;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public double getOperate_num() {
                    return operate_num;
                }

                public void setOperate_num(double operate_num) {
                    this.operate_num = operate_num;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }
            }
        }

}
