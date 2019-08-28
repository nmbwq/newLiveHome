package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/1.
 */

public class SortModelBean implements Serializable {


    private List<PlatfromsBean> platfroms;

    public List<PlatfromsBean> getPlatfroms() {
        return platfroms;
    }

    public void setPlatfroms(List<PlatfromsBean> platfroms) {
        this.platfroms = platfroms;
    }

    public static class PlatfromsBean {
        /**
         * id : 2
         * name : 菠萝街直播
         * icon_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2017-12-11/151298472448674197.jpg
         */

        private String id;
        private String name;
        private String icon_url;
        //1添加公会时公会名称布局  2 添加主播 布局
        private String is_paltfrom;

        private String table_flag;


        public String getTable_flag() {
            return table_flag;
        }

        public void setTable_flag(String table_flag) {
            this.table_flag = table_flag;
        }

        public String getIs_paltfrom() {
            return is_paltfrom;
        }

        public void setIs_paltfrom(String is_paltfrom) {
            this.is_paltfrom = is_paltfrom;
        }

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

        public String getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(String icon_url) {
            this.icon_url = icon_url;
        }
    }

}
