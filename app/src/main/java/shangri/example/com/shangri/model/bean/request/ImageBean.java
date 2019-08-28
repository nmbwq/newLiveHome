package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2017/5/11.
 * do what to 2017/5/11
 */

public class ImageBean extends BaseBeen{

    public String image;
    public String file_name;
    public String type;

    public String photo_url;



    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}
