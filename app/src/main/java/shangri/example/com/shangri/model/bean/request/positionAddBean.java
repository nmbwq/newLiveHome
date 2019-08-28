package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/6.
 */

public class positionAddBean extends BaseBeen {

    private String type;
    private String recruit_id;
    private String anchor_type;
    private String job_plat;
    private String work_position;
    private String job_method;
    private String pay;
    private String keep_pay;
    private String salary_type;
    private String lightspot;
    private String job_description;
    private String welfare;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public positionAddBean() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRecruit_id() {
        return recruit_id;
    }

    public void setRecruit_id(String recruit_id) {
        this.recruit_id = recruit_id;
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
