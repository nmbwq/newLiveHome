package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/3.
 */

public class IdIdentBean extends BaseBeen {
    public String legal_photo="";
    public String similarity="";
    public String id_img="";



    public int face_id;

    public int  type;


    //法务照片缓存
    public String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public IdIdentBean() {
    }

    public String getId_img() {
        return id_img;
    }

    public void setId_img(String id_img) {
        this.id_img = id_img;
    }

    public String getLegal_photo() {
        return legal_photo;
    }

    public void setLegal_photo(String legal_photo) {
        this.legal_photo = legal_photo;
    }

    public String getSimilarity() {
        return similarity;
    }

    public void setSimilarity(String similarity) {
        this.similarity = similarity;
    }

    public int getFace_id() {
        return face_id;
    }

    public void setFace_id(int face_id) {
        this.face_id = face_id;
    }
}
