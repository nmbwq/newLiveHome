package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.BossPlatBean;

/**
 * Description:
 * Data：2018/11/9-11:04
 * Author: lin
 */
public interface EnterPlatformView extends BaseView {
    //公司入驻平台接口
    void enterPlatfrom();
//    直播平台、直播类型接口
    void platformType(BossPlatBean bossPlatBean);

}
