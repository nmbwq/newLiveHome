package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.ImageUpload;

/**
 * 用户反馈
 * Created by mschen on 2017/6/30.
 */

public interface UserFeedBackView extends BaseView{

    void uploadFeedBcakContent();
    void imageUpload(ImageUpload resultBean);
}
