package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 * 消息公告
 * Created by chengaofu on 2017/6/29.
 */

public class TipsPageDataBean implements Serializable {

    /**
     * 表字段 : t_message.id
     */
    private Integer id;

    /**
     * 消息类型(1公告，2提示)
     * 表字段 : t_message.type
     */
    private Integer type;

    /**
     * 标题
     * 表字段 : t_message.title
     */
    private String title;

    /**
     * 发布者ID(关联用户表)
     * 表字段 : t_message.releaser_id
     */
    private Integer releaserId;

    /**
     * 是否可用(1是，0否)
     * 表字段 : t_message.enable
     */
    private Byte enable;

    /**
     * 发布时间
     * 表字段 : t_message.release_time
     */
    private Long releaseTime;

    /**
     * 表字段 : t_message.update_time
     */
    private Long updateTime;

    /**
     * 内容
     * 表字段 : t_message.content
     */
    private String content;

    private String contentText;

    /**
     * 阅读量
     */
    Integer readCount;

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaserId() {
        return releaserId;
    }

    public void setReleaserId(Integer releaserId) {
        this.releaserId = releaserId;
    }

    public Byte getEnable() {
        return enable;
    }

    public void setEnable(Byte enable) {
        this.enable = enable;
    }

    public Long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }
}
