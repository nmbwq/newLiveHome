package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/6.
 */

public class CompanyAddBean extends BaseBeen {

    private String type;
    private String  company_name;
    private String telephone;
    private String company_description;
    private String logo;
    private String company_scale;
    private String anchor_scale;
    private String company_short_name;
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany_short_name() {
        return company_short_name;
    }

    public void setCompany_short_name(String company_short_name) {
        this.company_short_name = company_short_name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCompany_scale() {
        return company_scale;
    }

    public void setCompany_scale(String company_scale) {
        this.company_scale = company_scale;
    }

    public String getAnchor_scale() {
        return anchor_scale;
    }

    public void setAnchor_scale(String anchor_scale) {
        this.anchor_scale = anchor_scale;
    }

    public CompanyAddBean() {
    }

    public CompanyAddBean(String type, String company_name, String telephone, String company_description) {
        this.type = type;
        this.company_name = company_name;
        this.telephone = telephone;
        this.company_description = company_description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCompany_description() {
        return company_description;
    }

    public void setCompany_description(String company_description) {
        this.company_description = company_description;
    }
}
