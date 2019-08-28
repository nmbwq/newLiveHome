package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/3/28.
 */

public class CreatCompanyBean extends BaseBeen {
    private String name;
    private String company_scale;
    private String location;
    private String logo;

    private String company_id;

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany_scale() {
        return company_scale;
    }

    public void setCompany_scale(String company_scale) {
        this.company_scale = company_scale;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
