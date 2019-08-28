package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 资讯详情 返回结果
 * Created by chengaofu on 2017/6/29.
 */

public class NewsDetailInfoBean implements Serializable {

    private String audioUrl;
    private Long browseCount;
    private Long praiseCount;
    private String content;
    private Long id;
    private Byte isPraise;
    private List<String> pictureUrlList;
    private Long releaseTime;
    private String summary;
    private String title;
    private String videoUrl;

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public Long getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(Long browseCount) {
        this.browseCount = browseCount;
    }

    public Long getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Long praiseCount) {
        this.praiseCount = praiseCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(Byte isPraise) {
        this.isPraise = isPraise;
    }

    public List<String> getPictureUrlList() {
        return pictureUrlList;
    }

    public void setPictureUrlList(List<String> pictureUrlList) {
        this.pictureUrlList = pictureUrlList;
    }

    public Long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
