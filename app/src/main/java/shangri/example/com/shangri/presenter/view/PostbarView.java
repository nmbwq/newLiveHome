package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.model.bean.response.BdBills;
import shangri.example.com.shangri.model.bean.response.PayResponseTaskBean;
import shangri.example.com.shangri.model.bean.response.VirtualCoinBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.model.bean.response.upgradePackageBean;

/**
 * Description:
 * Data：2018/11/29-10:20
 * Author: lin
 */
public interface PostbarView extends BaseView {

    void upgradePackage(upgradePackageBean bean);
    void recruitUpgrade();

}
