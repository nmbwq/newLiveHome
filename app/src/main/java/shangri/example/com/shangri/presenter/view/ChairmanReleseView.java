package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.NoticesResponseBean;
import shangri.example.com.shangri.model.bean.response.ReadPhotoBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;
import shangri.example.com.shangri.model.bean.response.changeLightspotBean;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;
import shangri.example.com.shangri.model.bean.response.welfareListPlatBean;

/**
 * 用户登录
 * Created by chengaofu on 2017/6/27.
 */

public interface ChairmanReleseView extends BaseView {
    void companyAdd(NewCompanyBean resultBean);//角色选择

    void platfromType(BossPlatBean mAccountDataBean);//

    void welfareList(welfareListPlatBean mAccountDataBean);//

    void changeLightspot(changeLightspotBean mAccountDataBean);//



    void  jobAdd();
    void  updatePosition();
    void legalIsface(IsFaceResponse resultBean);
    void limitNumber(IsFaceResponse resultBean);


}
