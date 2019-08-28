package shangri.example.com.shangri.model.bean.response;

public class CertificationBean {




        /**
         * is_face : 1
         * is_company : 0
         * legal_name : 韩松
         * company_name :
         */

        private int is_face;
        private int is_company;
        private String legal_name;
        private String company_name;

        public int getIs_face() {
            return is_face;
        }

        public void setIs_face(int is_face) {
            this.is_face = is_face;
        }

        public int getIs_company() {
            return is_company;
        }

        public void setIs_company(int is_company) {
            this.is_company = is_company;
        }

        public String getLegal_name() {
            return legal_name;
        }

        public void setLegal_name(String legal_name) {
            this.legal_name = legal_name;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

}
