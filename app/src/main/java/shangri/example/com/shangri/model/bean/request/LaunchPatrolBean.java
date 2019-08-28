package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/6.
 */

public class LaunchPatrolBean extends BaseBeen {

    private String guild_id;
    private String register_guild_id;
    private String inspect_date;
    private String good_tags_ids;
    private String bad_tags_ids;
    private String comment;
    //新增加上传图片
    public String photo_url;
    public String file_name;

    public String audio_url;
    //    音频时长
    public String audio_time;
    //    是否推送管理员 1推送 会长发起时有效
    public String alert_admin;

    //简历上传图片需要参数
//1第一张 2 第二张 3第三张
    public int place;
    //    图片 base64
    public String image;
    //    职位列表接口需要的参数
    public String status;

    //    职位列表接口需要的参数
    public String page;

    public int capture;

    //    职位详情接口需要的参数
    public String recruit_id;
    public String anchor_id;
    public String resume_message_id;
    public String resume_id;
    public String type;
    public String del_id;
    public String id;

    public  String chat_id;

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDel_id() {
        return del_id;
    }

    public void setDel_id(String del_id) {
        this.del_id = del_id;
    }

    public String catagory_id;

    public String getCatagory_id() {
        return catagory_id;
    }

    public void setCatagory_id(String catagory_id) {
        this.catagory_id = catagory_id;
    }

    public String getResume_id() {
        return resume_id;
    }

    public void setResume_id(String resume_id) {
        this.resume_id = resume_id;
    }

    public String getAnchor_id() {
        return anchor_id;
    }

    public void setAnchor_id(String anchor_id) {
        this.anchor_id = anchor_id;
    }

    public String getResume_message_id() {
        return resume_message_id;
    }

    public void setResume_message_id(String resume_message_id) {
        this.resume_message_id = resume_message_id;
    }

    public String getRecruit_id() {
        return recruit_id;
    }

    public void setRecruit_id(String recruit_id) {
        this.recruit_id = recruit_id;
    }

    public int getCapture() {
        return capture;
    }

    public void setCapture(int capture) {
        this.capture = capture;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAudio_time() {
        return audio_time;
    }

    public void setAudio_time(String audio_time) {
        this.audio_time = audio_time;
    }

    public String getAlert_admin() {
        return alert_admin;
    }

    public void setAlert_admin(String alert_admin) {
        this.alert_admin = alert_admin;
    }

    public String getAudio_url() {
        return audio_url;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getGuild_id() {
        return guild_id;
    }

    public void setGuild_id(String guild_id) {
        this.guild_id = guild_id;
    }

    public String getRegister_guild_id() {
        return register_guild_id;
    }

    public void setRegister_guild_id(String register_guild_id) {
        this.register_guild_id = register_guild_id;
    }

    public String getInspect_date() {
        return inspect_date;
    }

    public void setInspect_date(String inspect_date) {
        this.inspect_date = inspect_date;
    }

    public String getGood_tags_ids() {
        return good_tags_ids;
    }

    public void setGood_tags_ids(String good_tags_ids) {
        this.good_tags_ids = good_tags_ids;
    }

    public String getBad_tags_ids() {
        return bad_tags_ids;
    }

    public void setBad_tags_ids(String bad_tags_ids) {
        this.bad_tags_ids = bad_tags_ids;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
