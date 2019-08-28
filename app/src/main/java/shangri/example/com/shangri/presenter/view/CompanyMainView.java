package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.request.AddStarBean;
import shangri.example.com.shangri.model.bean.response.CompanyMainBean;

/**
 * Description:
 * Data：2018/11/7-17:12
 * Author: lin
 */
public interface CompanyMainView extends BaseView {
//    公司主页接口
    void getCompanyMain(CompanyMainBean bean);
    //公司入驻平台接口
    void enterPlatfrom();
    //公司明星主播接口
    void setStarAnchor();
    //公司相册接口
    void upPhotoAlbum();
    //删除图片接口
    void deleteImg();
}
