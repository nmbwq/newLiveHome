package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chengaofu on 2017/6/28.
 */

public class PageDataBean implements Serializable {

    private String browseCount;
    private Long id;
    private List<String> pictureUrlList;
    private Long releaseTime;
    private String title;
    private String summary;
    private Integer distanceReleaseTime;
    private Long praiseCount;
    private Byte isPraise;



    public void setBrowseCount(String browseCount) {
        this.browseCount = browseCount;
    }
    public String getBrowseCount() {
        return browseCount;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getDistanceReleaseTime() {
        return distanceReleaseTime;
    }

    public void setDistanceReleaseTime(Integer distanceReleaseTime) {
        this.distanceReleaseTime = distanceReleaseTime;
    }

    public Long getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Long praiseCount) {
        this.praiseCount = praiseCount;
    }

    public Byte getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(Byte isPraise) {
        this.isPraise = isPraise;
    }

    public void setPictureUrlList(List<String> pictureUrlList) {
        this.pictureUrlList = pictureUrlList;
    }
    public List<String> getPictureUrlList() {
        return pictureUrlList;
    }

    public Long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
}
