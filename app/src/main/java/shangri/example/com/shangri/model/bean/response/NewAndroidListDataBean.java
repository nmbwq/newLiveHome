package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/3.
 */

public class NewAndroidListDataBean implements Serializable {

        /**
         * anchors : {"current_page":1,"data":[{"anchor_uid":"7443730","anchor_name":"1988","avatar_url":""}],"first_page_url":"http://zhibozhijia.com/api/mine/guild/anchor/list?page=1","from":1,"last_page":1,"last_page_url":"http://zhibozhijia.com/api/mine/guild/anchor/list?page=1","next_page_url":null,"path":"http://zhibozhijia.com/api/mine/guild/anchor/list","per_page":10,"prev_page_url":null,"to":8,"total":8}
         * quick_anchors : {"current_page":2,"data":[{"anchor_uid":"1038078309","anchor_name":"JD奈斯兔想升级","avatar_url":"http://p3.fx.kgimg.com/v2/fxuserlogo/308b5a2ffa692684cbb62f19ed66bce8.jpg_85x85.jpg"}],"first_page_url":"http://zhibozhijia.com/api/mine/guild/anchor/list?page=1","from":11,"last_page":40,"last_page_url":"http://zhibozhijia.com/api/mine/guild/anchor/list?page=40","next_page_url":"http://zhibozhijia.com/api/mine/guild/anchor/list?page=3","path":"http://zhibozhijia.com/api/mine/guild/anchor/list","per_page":10,"prev_page_url":"http://zhibozhijia.com/api/mine/guild/anchor/list?page=1","to":20,"total":400}
         */

        private AnchorsBean anchors;
        private QuickAnchorsBean quick_anchors;

        public AnchorsBean getAnchors() {
            return anchors;
        }

        public void setAnchors(AnchorsBean anchors) {
            this.anchors = anchors;
        }

        public QuickAnchorsBean getQuick_anchors() {
            return quick_anchors;
        }

        public void setQuick_anchors(QuickAnchorsBean quick_anchors) {
            this.quick_anchors = quick_anchors;
        }

        public static class AnchorsBean {
            /**
             * current_page : 1
             * data : [{"anchor_uid":"7443730","anchor_name":"1988","avatar_url":""}]
             * first_page_url : http://zhibozhijia.com/api/mine/guild/anchor/list?page=1
             * from : 1
             * last_page : 1
             * last_page_url : http://zhibozhijia.com/api/mine/guild/anchor/list?page=1
             * next_page_url : null
             * path : http://zhibozhijia.com/api/mine/guild/anchor/list
             * per_page : 10
             * prev_page_url : null
             * to : 8
             * total : 8
             */

            private int current_page;
            private String first_page_url;
            private int from;
            private int last_page;
            private String last_page_url;
            private Object next_page_url;
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

            public Object getNext_page_url() {
                return next_page_url;
            }

            public void setNext_page_url(Object next_page_url) {
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
                 * anchor_uid : 7443730
                 * anchor_name : 1988
                 * avatar_url :
                 */

                private String anchor_uid;
                private String anchor_name;
                private String avatar_url;
                public boolean open;

                public boolean isOpen() {
                    return open;
                }

                public void setOpen(boolean open) {
                    this.open = open;
                }

                public String getAnchor_uid() {
                    return anchor_uid;
                }

                public void setAnchor_uid(String anchor_uid) {
                    this.anchor_uid = anchor_uid;
                }

                public String getAnchor_name() {
                    return anchor_name;
                }

                public void setAnchor_name(String anchor_name) {
                    this.anchor_name = anchor_name;
                }

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }
            }
        }

        public static class QuickAnchorsBean {
            /**
             * current_page : 2
             * data : [{"anchor_uid":"1038078309","anchor_name":"JD奈斯兔想升级","avatar_url":"http://p3.fx.kgimg.com/v2/fxuserlogo/308b5a2ffa692684cbb62f19ed66bce8.jpg_85x85.jpg"}]
             * first_page_url : http://zhibozhijia.com/api/mine/guild/anchor/list?page=1
             * from : 11
             * last_page : 40
             * last_page_url : http://zhibozhijia.com/api/mine/guild/anchor/list?page=40
             * next_page_url : http://zhibozhijia.com/api/mine/guild/anchor/list?page=3
             * path : http://zhibozhijia.com/api/mine/guild/anchor/list
             * per_page : 10
             * prev_page_url : http://zhibozhijia.com/api/mine/guild/anchor/list?page=1
             * to : 20
             * total : 400
             */

            private int current_page;
            private String first_page_url;
            private int from;
            private int last_page;
            private String last_page_url;
            private String next_page_url;
            private String path;
            private int per_page;
            private String prev_page_url;
            private int to;
            private int total;
            private List<AnchorsBean.DataBean> data;

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

            public String getPrev_page_url() {
                return prev_page_url;
            }

            public void setPrev_page_url(String prev_page_url) {
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

            public List<AnchorsBean.DataBean> getData() {
                return data;
            }

            public void setData(List<AnchorsBean.DataBean> data) {
                this.data = data;
            }
        }
}

