package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.alipay.Base64;
import shangri.example.com.shangri.model.bean.response.PricePackageBean;
import shangri.example.com.shangri.model.bean.response.WelfareWithdrawalBean;

public interface RedEnvelopeWelfareView   extends BaseView{



        void Success();
        void withdrawDeposit(PricePackageBean bean);

}
