package shangri.example.com.shangri.model.bean.request;

/**
 * Description:
 * Data：2018/11/9-16:27
 * Author: lin
 */
public class AddStarBean extends BaseBeen {
    String anchor;
    String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAnchor() {
        return anchor;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }
}
