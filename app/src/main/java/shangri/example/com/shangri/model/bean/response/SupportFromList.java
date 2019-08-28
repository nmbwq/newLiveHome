package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/9.
 */

public class SupportFromList implements Serializable {


    /**
     * code : 0
     * message : success
     * data : {"quick_platfrom":[{"name":"酷狗直播","icon_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2017-12-13/151314337041127286.jpg"},{"name":"千帆直播","icon_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2017-12-13/151314337041127286.jpg"},{"name":"KK直播","icon_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2017-12-13/151314337041127286.jpg"},{"name":"迅雷直播","icon_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2017-12-13/151314337041127286.jpg"}],"auth_platfrom":[{"name":"小米直播","icon_url":"http://cdn1.tooohappy.com/icon/2017-12-11/151298468491184578.jpg"},{"name":"菠萝街直播","icon_url":"http://cdn1.tooohappy.com/icon/2017-12-11/151298472448674197.jpg"},{"name":"火山直播","icon_url":"http://cdn1.tooohappy.com/icon/2017-12-11/151298526223267338.jpg"},{"name":"花椒直播","icon_url":"http://cdn1.tooohappy.com/icon/2017-12-11/151298546566701849.jpg"},{"name":"NOW直播","icon_url":"http://cdn1.tooohappy.com/icon/2017-12-13/151314328693733338.jpg"},{"name":"UP直播","icon_url":"http://cdn1.tooohappy.com/icon/2017-12-13/151314333441065923.jpg"},{"name":"一直播","icon_url":"http://cdn1.tooohappy.com/icon/2017-12-13/151314337041127286.jpg"},{"name":"红人直播","icon_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2018-06-01/152784194596468.jpg"},{"name":"YY直播","icon_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2018-06-01/152784194596468.jpg"},{"name":"陌陌直播","icon_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2018-07-12/153137612882970633.jpg"},{"name":"奇秀直播","icon_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2018-07-18/153187746784538042.jpg"},{"name":"合拍直播","icon_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2018-07-23/153233476562439158.jpg"}]}
     */


    private List<QuickPlatfromBean> quick_platfrom;
    private List<AuthPlatfromBean> auth_platfrom;

    public List<QuickPlatfromBean> getQuick_platfrom() {
        return quick_platfrom;
    }

    public void setQuick_platfrom(List<QuickPlatfromBean> quick_platfrom) {
        this.quick_platfrom = quick_platfrom;
    }

    public List<AuthPlatfromBean> getAuth_platfrom() {
        return auth_platfrom;
    }

    public void setAuth_platfrom(List<AuthPlatfromBean> auth_platfrom) {
        this.auth_platfrom = auth_platfrom;
    }

    public static class QuickPlatfromBean {
        /**
         * name : 酷狗直播
         * icon_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2017-12-13/151314337041127286.jpg
         */

        private String name;
        private String icon_url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(String icon_url) {
            this.icon_url = icon_url;
        }
    }

    public static class AuthPlatfromBean {
        /**
         * name : 小米直播
         * icon_url : http://cdn1.tooohappy.com/icon/2017-12-11/151298468491184578.jpg
         */

        private String name;
        private String icon_url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(String icon_url) {
            this.icon_url = icon_url;
        }
    }

}
