package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.model.bean.response.BdBills;
import shangri.example.com.shangri.model.bean.response.PayResponseTaskBean;
import shangri.example.com.shangri.model.bean.response.VirtualCoinBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;

/**
 * Description:
 * Data：2018/11/29-10:20
 * Author: lin
 */
public interface VirtualCoinView extends BaseView {

    void getVirtualCoin(VirtualCoinBean bean);

    void virtualCoinPay(PayResponseTaskBean resultBean);

    void accountData(AccountDataBean mAccountDataBean);

    void myBdBills(BdBills bills);

    void legalProtocol(companyMyBean bean);
}
