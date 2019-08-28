package shangri.example.com.shangri.model.bean.response;

import java.util.List;

/**
 * Description:VIP权益说明
 * Data：2018/11/22-11:18
 * Author: lin
 */
public class VIPAdvantageExplain {
    List<Advantages> advantages;

    public List<Advantages> getAdvantages() {
        return advantages;
    }

    public void setAdvantages(List<Advantages> advantages) {
        this.advantages = advantages;
    }

    public class Advantages{
        String id;
        String name;
        String detail;
        String image;
        String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
