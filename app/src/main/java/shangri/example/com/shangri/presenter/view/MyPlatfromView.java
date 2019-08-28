package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.ImageUpload;
import shangri.example.com.shangri.model.bean.response.MyPlatfromList;
import shangri.example.com.shangri.model.bean.response.SortModelBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface MyPlatfromView extends BaseView {

    void myPlatfromList(MyPlatfromList resultBean);//角色选择

    void PlatfromList(SortModelBean resultBean);

    void uploadCover(ImageUpload resultBean);

    void addPlatfrom(String s);
}
