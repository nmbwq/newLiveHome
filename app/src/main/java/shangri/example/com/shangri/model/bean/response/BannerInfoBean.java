package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 * banner的响应结果
 * Created by chengaofu on 2017/6/28.
 */

public class BannerInfoBean implements Serializable {

    /**
     * 主键ID
     */
  
    private Long id;

    /**
     * 编号
     */
    private String code;

    /**
     * 展现位置id(关联展现位置表)
     */
    private  Integer position_id;

    /**
     * 类型ID(关联类型表)
     */
    private  Integer type_id;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片url
     */
    private String picture_url;

    /**
     * 上传者id(关联用户表)
     */
    private Integer upload_id;

    /**
     * 上传时间
     */
    private Long upload_time;

    /**
     *是否投放(1是,0否)
     */
    private  String throwss;

    /**
     * 链接类型(1资讯,2活动URL)
     */
    private  Integer link_type;

    /**
     * 链接地址
     */
    private String link_address;

    /**
     * 链接资讯id(关联资讯表)
     */
    private Long link_info_id;

    /**
     * 更新时间
     */
    private Long upLong_time;

    /**
     * 是否可用(1是,0否)
     */
    private Integer enable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPosition_id() {
        return position_id;
    }

    public void setPosition_id(Integer position_id) {
        this.position_id = position_id;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public Integer getUpload_id() {
        return upload_id;
    }

    public void setUpload_id(Integer upload_id) {
        this.upload_id = upload_id;
    }

    public Long getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(Long upload_time) {
        this.upload_time = upload_time;
    }

    public String getThrowss() {
        return throwss;
    }

    public void setThrowss(String throwss) {
        this.throwss = throwss;
    }

    public Integer getLink_type() {
        return link_type;
    }

    public void setLink_type(Integer link_type) {
        this.link_type = link_type;
    }

    public String getLink_address() {
        return link_address;
    }

    public void setLink_address(String link_address) {
        this.link_address = link_address;
    }

    public Long getLink_info_id() {
        return link_info_id;
    }

    public void setLink_info_id(Long link_info_id) {
        this.link_info_id = link_info_id;
    }

    public Long getUpLong_time() {
        return upLong_time;
    }

    public void setUpLong_time(Long upLong_time) {
        this.upLong_time = upLong_time;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}
