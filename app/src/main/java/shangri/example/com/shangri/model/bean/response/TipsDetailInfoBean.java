package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 *  查看公告响应详情
 * Created by chengaofu on 2017/6/29.
 */

public class TipsDetailInfoBean implements Serializable {

    private String content;
    private Byte enable;
    private String id;
    private String readCount;
    private Long releaseTime;
    private String title;
    private String type;
    private Long updateTime;
    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setEnable(Byte enable) {
        this.enable = enable;
    }
    public Byte getEnable() {
        return enable;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }
    public String getReadCount() {
        return readCount;
    }

    public void setReleaseTime(Long releaseTime) {
        this.releaseTime = releaseTime;
    }
    public Long getReleaseTime() {
        return releaseTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
    public Long getUpdateTime() {
        return updateTime;
    }
}
