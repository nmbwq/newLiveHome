package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class companyListBean implements Serializable {


    private List<CompanysBean> companys;

    public List<CompanysBean> getCompanys() {
        return companys;
    }

    public void setCompanys(List<CompanysBean> companys) {
        this.companys = companys;
    }

    public static class CompanysBean {
        /**
         * id : 3
         * company_name : YY 测试 公司
         */

        private int id;
        private String company_name;
        private int register_id;
        //选择提醒天数用
        boolean flag;

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public CompanysBean(int id, String company_name) {
            this.id = id;
            this.company_name = company_name;
        }

        public int getRegister_id() {
            return register_id;
        }

        public void setRegister_id(int register_id) {
            this.register_id = register_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }
    }

}



