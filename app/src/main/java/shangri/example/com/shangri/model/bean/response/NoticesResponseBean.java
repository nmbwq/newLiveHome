package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/5.
 */

public class NoticesResponseBean implements Serializable {
    /**
     * code : 0
     * message : 获取成功
     * data : {"notices":[{"id":"58","title":"客户卡","content":"","img_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/notice/2018-07-23/5b557843a53f9.jpg","notice_img":"","is_pop":"0"},{"id":"57","title":"fkhgk","content":"","img_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/notice/2018-07-23/5b557808c7e9a.jpg","notice_img":"","is_pop":"0"}]}
     */
    //弹出福利推送提示需要参数
//    是否已弹出 1已弹出 0未弹出
    String is_alert;
    //    赠送的波豆数
    int get_bd;

    public int getGet_bd() {
        return get_bd;
    }

    public void setGet_bd(int get_bd) {
        this.get_bd = get_bd;
    }

    public String getIs_alert() {
        return is_alert;
    }

    public void setIs_alert(String is_alert) {
        this.is_alert = is_alert;
    }

    private List<NoticesBean> notices;
    //未读消息数量
    int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<NoticesBean> getNotices() {
        return notices;
    }

    public void setNotices(List<NoticesBean> notices) {
        this.notices = notices;
    }

    public static class NoticesBean {
        /**
         * id : 58
         * title : 客户卡
         * content :
         * img_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/notice/2018-07-23/5b557843a53f9.jpg
         * notice_img :
         * is_pop : 0
         */

        private String id;
        private String title;
        private String content;
        private String img_url;
        private String notice_img;
        private String is_pop;
        private String conn_url;

        private int url_to;

        public int getUrl_to() {
            return url_to;
        }

        public void setUrl_to(int url_to) {
            this.url_to = url_to;
        }

        public String getConn_url() {
            return conn_url;
        }

        public void setConn_url(String conn_url) {
            this.conn_url = conn_url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getNotice_img() {
            return notice_img;
        }

        public void setNotice_img(String notice_img) {
            this.notice_img = notice_img;
        }

        public String getIs_pop() {
            return is_pop;
        }

        public void setIs_pop(String is_pop) {
            this.is_pop = is_pop;
        }
    }

}
