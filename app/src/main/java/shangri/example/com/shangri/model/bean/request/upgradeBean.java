package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/30.
 */

public class upgradeBean extends BaseBeen {
   public String recruit_id;
    public String package_id;


    public upgradeBean() {
    }

    public String getRecruit_id() {
        return recruit_id;
    }

    public void setRecruit_id(String recruit_id) {
        this.recruit_id = recruit_id;
    }

    public String getPackage_id() {
        return package_id;
    }

    public void setPackage_id(String package_id) {
        this.package_id = package_id;
    }
}
