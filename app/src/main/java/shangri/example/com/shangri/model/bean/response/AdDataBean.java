package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/12/25.
 */

public class AdDataBean implements Serializable {


    /**
     * ad : {"id":"2","photo":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/advertising/2018-06-12/5b1fa04292b0f.jpg","photo_x":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/advertising/2018-06-12/5b1fa042b777d.jpg","http_url":"http://test.ilivehome.net/api/read/article/web/145","downym":"0","jsdx":["1","2","3"]}
     */

    private AdBean ad;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AdBean getAd() {
        return ad;
    }

    public void setAd(AdBean ad) {
        this.ad = ad;
    }

    public static class AdBean implements Serializable {
        /**
         * id : 2
         * photo : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/advertising/2018-06-12/5b1fa04292b0f.jpg
         * photo_x : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/advertising/2018-06-12/5b1fa042b777d.jpg
         * http_url : http://test.ilivehome.net/api/read/article/web/145
         * downym : 0
         * jsdx : ["1","2","3"]
         */

        private String id;
        private String photo;
        private String photo_x;
        private String http_url;
        private String downym;
        private List<String> jsdx;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getPhoto_x() {
            return photo_x;
        }

        public void setPhoto_x(String photo_x) {
            this.photo_x = photo_x;
        }

        public String getHttp_url() {
            return http_url;
        }

        public void setHttp_url(String http_url) {
            this.http_url = http_url;
        }

        public String getDownym() {
            return downym;
        }

        public void setDownym(String downym) {
            this.downym = downym;
        }

        public List<String> getJsdx() {
            return jsdx;
        }

        public void setJsdx(List<String> jsdx) {
            this.jsdx = jsdx;
        }
    }
}
