package shangri.example.com.shangri.model.bean.response;

import java.util.List;

/**
 * Created by admin on 2017/12/25.
 */

public class  BossPlatBean {


    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private List<PlatfromBean> platfrom;
    private List<RecuitTypeBean> recuitType;

    public List<PlatfromBean> getPlatfrom() {
        return platfrom;
    }

    public void setPlatfrom(List<PlatfromBean> platfrom) {
        this.platfrom = platfrom;
    }

    public List<RecuitTypeBean> getRecuitType() {
        return recuitType;
    }

    public void setRecuitType(List<RecuitTypeBean> recuitType) {
        this.recuitType = recuitType;
    }

    public static class PlatfromBean {
        /**
         * id : 1
         * plat_name : 虎牙直播
         * status : 1
         */

        private String id;
        private String plat_name;
        private String status;
        private boolean isClick;
        String logo;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public PlatfromBean(String plat_name, boolean isClick) {
            this.plat_name = plat_name;
            this.isClick = isClick;
        }

        public PlatfromBean(String id, String plat_name, boolean isClick) {
            this.id = id;
            this.plat_name = plat_name;
            this.isClick = isClick;
        }

        public PlatfromBean(String id, String plat_name, String status, boolean isClick) {
            this.id = id;
            this.plat_name = plat_name;
            this.status = status;
            this.isClick = isClick;
        }

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

        public String getPlat_name() {
            return plat_name;
        }

        public void setPlat_name(String plat_name) {
            this.plat_name = plat_name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class RecuitTypeBean {
        /**
         * id : 1
         * type_name : 秀场直播
         * status : 1
         */

        private String id;
        private String type_name;
        private String status;

        private boolean isClick;


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

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
