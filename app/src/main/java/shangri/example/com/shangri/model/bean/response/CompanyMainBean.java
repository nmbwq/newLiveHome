package shangri.example.com.shangri.model.bean.response;

import java.util.List;

/**
 * Description:
 * Data：2018/11/7-17:07
 * Author: lin
 */
public class CompanyMainBean {
    String token ;
    List<AnchorStar> anchor;
    Company company;
    List<CompanyPlatfrom> company_platfrom;
    List<CompanyAnchor> company_anchor;
    List<CompanyPhoto> company_photo;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<AnchorStar> getAnchor() {
        return anchor;
    }

    public void setAnchor(List<AnchorStar> anchor) {
        this.anchor = anchor;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<CompanyPlatfrom> getCompany_platfrom() {
        return company_platfrom;
    }

    public void setCompany_platfrom(List<CompanyPlatfrom> company_platfrom) {
        this.company_platfrom = company_platfrom;
    }

    public List<CompanyAnchor> getCompany_anchor() {
        return company_anchor;
    }

    public void setCompany_anchor(List<CompanyAnchor> company_anchor) {
        this.company_anchor = company_anchor;
    }

    public List<CompanyPhoto> getCompany_photo() {
        return company_photo;
    }

    public void setCompany_photo(List<CompanyPhoto> company_photo) {
        this.company_photo = company_photo;
    }
    public static class AnchorStar{
         String anchor_name;
         String anchor_logo;

        public String getAnchor_logo() {
            return anchor_logo;
        }

        public void setAnchor_logo(String anchor_logo) {
            this.anchor_logo = anchor_logo;
        }

        public String getAnchor_name() {
            return anchor_name;
        }

        public void setAnchor_name(String anchor_name) {
            this.anchor_name = anchor_name;
        }
    }
    public class Company{
          String id;
          String registrant_id;
          String company_name;
          String company_short_name;
          String company_scale;
          String anchor_scale;
          String location;
          String logo;
          String telephone;
          String company_description;
          String home_url;
          String license_status;
          String face_status;
          String hot_number;

        public String getHot_number() {
            return hot_number;
        }

        public void setHot_number(String hot_number) {
            this.hot_number = hot_number;
        }

        public String getLicense_status() {
            return license_status;
        }

        public void setLicense_status(String license_status) {
            this.license_status = license_status;
        }

        public String getFace_status() {
            return face_status;
        }

        public void setFace_status(String face_status) {
            this.face_status = face_status;
        }

        public String getId() {
             return id;
         }

         public void setId(String id) {
             this.id = id;
         }

         public String getRegistrant_id() {
             return registrant_id;
         }

         public void setRegistrant_id(String registrant_id) {
             this.registrant_id = registrant_id;
         }

         public String getCompany_name() {
             return company_name;
         }

         public void setCompany_name(String company_name) {
             this.company_name = company_name;
         }

         public String getCompany_short_name() {
             return company_short_name;
         }

         public void setCompany_short_name(String company_short_name) {
             this.company_short_name = company_short_name;
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

         public String getHome_url() {
             return home_url;
         }

         public void setHome_url(String home_url) {
             this.home_url = home_url;
         }
     }
     public class CompanyPlatfrom{
          String plat_name;
          String plat_logo;
          String id;

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

         public String getPlat_logo() {
             return plat_logo;
         }

         public void setPlat_logo(String plat_logo) {
             this.plat_logo = plat_logo;
         }
     }
     public class CompanyAnchor{
         String anchor_logo;
         String anchor_name;
         String id;

         public String getId() {
             return id;
         }

         public void setId(String id) {
             this.id = id;
         }

         public String getAnchor_logo() {
             return anchor_logo;
         }

         public void setAnchor_logo(String anchor_logo) {
             this.anchor_logo = anchor_logo;
         }

         public String getAnchor_name() {
             return anchor_name;
         }

         public void setAnchor_name(String anchor_name) {
             this.anchor_name = anchor_name;
         }
     }
     public class CompanyPhoto{
         String img_url;
         String id;

         public String getId() {
             return id;
         }

         public void setId(String id) {
             this.id = id;
         }

         public String getImg_url() {
             return img_url;
         }

         public void setImg_url(String img_url) {
             this.img_url = img_url;
         }
     }
}
