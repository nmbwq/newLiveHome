package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/3/28.
 */

public class AddPhoneBean extends BaseBeen {
    private String job;
    private String name;
    //获取通讯录时候用
    private String telephone;
    //获取通讯录时候用
    private String company_id;

    //删除时候用的参数
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }
}
