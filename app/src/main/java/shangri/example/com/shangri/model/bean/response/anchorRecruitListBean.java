package shangri.example.com.shangri.model.bean.response;

import java.util.List;

/**
 * Created by admin on 2017/12/25.
 */

public class anchorRecruitListBean {


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
         * name : 全部职位
         * type : 1
         * anchor_type : 0
         * img_url : https://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/plat_icon/kugou.png
         * status : 1
         * create_time : 1542076001
         * sort : 0
         */

        private int id;
        private String name;
        private String type;
        private String anchor_type;
        private String img_url;
        private String status;
        private String create_time;
        private String sort;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAnchor_type() {
            return anchor_type;
        }

        public void setAnchor_type(String anchor_type) {
            this.anchor_type = anchor_type;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
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

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }
    }

}
