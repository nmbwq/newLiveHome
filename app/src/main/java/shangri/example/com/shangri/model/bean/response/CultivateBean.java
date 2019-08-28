package shangri.example.com.shangri.model.bean.response;

import java.util.List;

public class CultivateBean {


    private List<CatagoryBean> catagory;

    public List<CatagoryBean> getCatagory() {
        return catagory;
    }

    public void setCatagory(List<CatagoryBean> catagory) {
        this.catagory = catagory;
    }

    public static class CatagoryBean {
        /**
         * id : 1
         * type : 2
         * name : 一周资讯
         * img_url : http://cdn1.tooohappy.com/article/2017-11-22/15113426265271183.jpg
         */

        private String id;
        private String type;
        private String name;
        private String img_url;
        boolean isClick;

        public boolean isClick() {
            return isClick;
        }

        public void setClick(boolean click) {
            isClick = click;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }
}
