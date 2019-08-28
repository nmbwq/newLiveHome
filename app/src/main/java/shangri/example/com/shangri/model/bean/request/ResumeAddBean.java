package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/6.
 */

public class ResumeAddBean extends BaseBeen {

    private String nickname;
    private String sex;
    private String age;
    private String telephone;
    private String date_of_birth;

    private String resume_id;

    private String anchor_type;
    private String job_plat;
    private String room_id;
    private String job_method;
    private int height;
    private int weight;
    private int live_age;
    private int pay_low;
    private int pay_high;
    private String per_style;
    private int is_open;

    private String resume_photo;
    //    求职状态ID
    private String wanted_status;
    private String chat_id;

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getWanted_status() {
        return wanted_status;
    }

    public void setWanted_status(String wanted_status) {
        this.wanted_status = wanted_status;
    }

    public String getResume_photo() {
        return resume_photo;
    }

    public void setResume_photo(String resume_photo) {
        this.resume_photo = resume_photo;
    }

    public String getJob_method() {
        return job_method;
    }

    public void setJob_method(String job_method) {
        this.job_method = job_method;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLive_age() {
        return live_age;
    }

    public void setLive_age(int live_age) {
        this.live_age = live_age;
    }

    public int getPay_low() {
        return pay_low;
    }

    public void setPay_low(int pay_low) {
        this.pay_low = pay_low;
    }

    public int getPay_high() {
        return pay_high;
    }

    public void setPay_high(int pay_high) {
        this.pay_high = pay_high;
    }

    public String getPer_style() {
        return per_style;
    }

    public void setPer_style(String per_style) {
        this.per_style = per_style;
    }

    public int getIs_open() {
        return is_open;
    }

    public void setIs_open(int is_open) {
        this.is_open = is_open;
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

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getResume_id() {
        return resume_id;
    }

    public void setResume_id(String resume_id) {
        this.resume_id = resume_id;
    }

    public ResumeAddBean() {
    }

    public ResumeAddBean(String nickname, String sex, String age, String telephone, String date_of_birth) {
        this.nickname = nickname;
        this.sex = sex;
        this.age = age;
        this.telephone = telephone;
        this.date_of_birth = date_of_birth;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
}
