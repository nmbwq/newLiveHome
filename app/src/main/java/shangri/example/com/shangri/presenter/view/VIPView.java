package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.model.bean.response.PayResponseTaskBean;
import shangri.example.com.shangri.model.bean.response.VIPAdvantageBean;
import shangri.example.com.shangri.model.bean.response.VIPAdvantageExplain;
import shangri.example.com.shangri.model.bean.response.VIPBannerBean;
import shangri.example.com.shangri.model.bean.response.VIPCardBean;

/**
 * Description:VIP view
 * Data：2018/11/21-14:31
 * Author: lin
 */
public interface VIPView extends BaseView {
    void getVIPPackagesList(VIPCardBean bean);

    void getVIPAdvantage(VIPAdvantageBean bean);

    void getVIPAdvantageExplain(VIPAdvantageExplain bean);

    void VIPPay(PayResponseTaskBean resultBean);

    void accountData(AccountDataBean mAccountDataBean);

    void VIPBanner(VIPBannerBean bannerBean);
}
