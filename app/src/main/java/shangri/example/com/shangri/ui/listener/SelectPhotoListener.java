package shangri.example.com.shangri.ui.listener;

/**
 * 选择相册还是 选择拍照 保存图片
 * Created by chengaofu on 2017/7/1.
 */

public interface SelectPhotoListener {

    void takePhoto();

    void selectFromAlbum();

    void savePhoto();

}
