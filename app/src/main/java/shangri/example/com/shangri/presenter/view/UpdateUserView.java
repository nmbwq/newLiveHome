package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.ImageUpload;

/**
 * 修改用户资料
 * Created by chengaofu on 2017/6/29.
 */

public interface UpdateUserView extends BaseView{

    void updateUser(ImageUpload resultBean);
}
