package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/3.
 */

public class alertBean extends BaseBeen {
    private String inspect_id;
    private String task_id;
    //获取对比度需要穿的参数
    private String live_img;
    private String icd_img;
    //验证身份证和姓名需要传的参数
    private String name;
    private String id_num;
    private String id_img;


    //营业执照认证存储需要参数
    private String company_name;
    private String codeUSC;
    private String license_photo;

    //修改公会比率需要参数
    private String guild_id;
    private String ratio;

    //主播列表接口
    private String type;
//    private String guild_id;
    private String table_flag;

    //删除主播需要的参数
    private String anchor_uid;
    //添加主播需要的参数

    private String  anchor_id;
    private String  page;

    //信息列表删除接口需要参数
    private String  chat_id;

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getAnchor_id() {
        return anchor_id;
    }

    public void setAnchor_id(String anchor_id) {
        this.anchor_id = anchor_id;
    }

    public String getAnchor_uid() {
        return anchor_uid;
    }

    public void setAnchor_uid(String anchor_uid) {
        this.anchor_uid = anchor_uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTable_flag() {
        return table_flag;
    }

    public void setTable_flag(String table_flag) {
        this.table_flag = table_flag;
    }

    public String getGuild_id() {
        return guild_id;
    }

    public void setGuild_id(String guild_id) {
        this.guild_id = guild_id;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getId_img() {
        return id_img;
    }

    public void setId_img(String id_img) {
        this.id_img = id_img;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCodeUSC() {
        return codeUSC;
    }

    public void setCodeUSC(String codeUSC) {
        this.codeUSC = codeUSC;
    }

    public String getLicense_photo() {
        return license_photo;
    }

    public void setLicense_photo(String license_photo) {
        this.license_photo = license_photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num;
    }

    public String getLive_img() {
        return live_img;
    }

    public void setLive_img(String live_img) {
        this.live_img = live_img;
    }

    public String getIcd_img() {
        return icd_img;
    }

    public void setIcd_img(String icd_img) {
        this.icd_img = icd_img;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public alertBean(String inspect_id) {
        this.inspect_id = inspect_id;
    }

    public alertBean() {
    }

    public String getInspect_id() {
        return inspect_id;
    }

    public void setInspect_id(String inspect_id) {
        this.inspect_id = inspect_id;
    }
}
