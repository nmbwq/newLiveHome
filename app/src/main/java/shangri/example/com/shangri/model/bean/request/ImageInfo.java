package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/30.
 */

public class ImageInfo {
    private String place;
    private String img_url;


    public ImageInfo() {
    }

    public ImageInfo(String place, String img_url) {
        this.place = place;
        this.img_url = img_url;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
