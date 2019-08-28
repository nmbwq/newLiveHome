package shangri.example.com.shangri.model.bean.response;

import java.util.List;

/**
 * Description:VIP权益
 * Data：2018/11/22-11:15
 * Author: lin
 */
public class VIPAdvantageBean {

    List<Setting> settings;

    public List<Setting> getSettings() {
        return settings;
    }

    public void setSettings(List<Setting> settings) {
        this.settings = settings;
    }

    public class Setting {
        String id;
        String package_name;
        String toke_tel_count;
        String call_tel_count;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPackage_name() {
            return package_name;
        }

        public void setPackage_name(String package_name) {
            this.package_name = package_name;
        }

        public String getToke_tel_count() {
            return toke_tel_count;
        }

        public void setToke_tel_count(String toke_tel_count) {
            this.toke_tel_count = toke_tel_count;
        }

        public String getCall_tel_count() {
            return call_tel_count;
        }

        public void setCall_tel_count(String call_tel_count) {
            this.call_tel_count = call_tel_count;
        }
    }
}
