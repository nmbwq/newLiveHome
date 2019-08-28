package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/3/28.
 */

public class AddJobBean extends BaseBeen {
//    职位类型
    private String anchor_type;
//    平台(逗号隔开)
    private String job_plat;
    //工作区域
    private String work_position;
    //工作范围 1 线上 2 线下 0全部
    private String job_method;
    //薪资范围(1000-5000)
    private String pay;
    //底薪(2000)
    private String keep_pay;
    //结算周期 1月结 2周结 3日结 4全部
    private String salary_type;
    //亮点
    private String lightspot;
    //详细描述
    private String job_description;
    //福利待遇 (逗号隔开)
    private String welfare;

    public AddJobBean() {
    }

    public String getAnchor_type() {
        return anchor_type;
    }

    public void setAnchor_type(String anchor_type) {
        this.anchor_type = anchor_type;
    }

    public String getJob_plat() {
        return job_plat;
    }

    public void setJob_plat(String job_plat) {
        this.job_plat = job_plat;
    }

    public String getWork_position() {
        return work_position;
    }

    public void setWork_position(String work_position) {
        this.work_position = work_position;
    }

    public String getJob_method() {
        return job_method;
    }

    public void setJob_method(String job_method) {
        this.job_method = job_method;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getKeep_pay() {
        return keep_pay;
    }

    public void setKeep_pay(String keep_pay) {
        this.keep_pay = keep_pay;
    }

    public String getSalary_type() {
        return salary_type;
    }

    public void setSalary_type(String salary_type) {
        this.salary_type = salary_type;
    }

    public String getLightspot() {
        return lightspot;
    }

    public void setLightspot(String lightspot) {
        this.lightspot = lightspot;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }
}
