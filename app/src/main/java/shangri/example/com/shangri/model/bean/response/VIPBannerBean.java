package shangri.example.com.shangri.model.bean.response;

import java.util.List;

/**
 * Description: VIPBanner
 * Data：2018/11/23-15:10
 * Author: lin
 */
public class VIPBannerBean {

    List<Banner> banner;

    public List<Banner> getBanner() {
        return banner;
    }

    public void setBanner(List<Banner> banner) {
        this.banner = banner;
    }

    public class Banner{
        String id;
        String place;
        String image_url;
        String to_url;
        String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getTo_url() {
            return to_url;
        }

        public void setTo_url(String to_url) {
            this.to_url = to_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
