package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.AmountBean;
import shangri.example.com.shangri.model.bean.response.InvitationCodeBean;
import shangri.example.com.shangri.model.bean.response.InviteListDataBean;
import shangri.example.com.shangri.model.bean.response.PriceListBean;
import shangri.example.com.shangri.model.bean.response.SevenDayEarningsBean;
import shangri.example.com.shangri.model.bean.response.upgradeAlertBean;

public interface UserBenefitsView  extends  BaseView {

  void Success(AmountBean resultBean);
  void codeSuccess(InvitationCodeBean codeBean);

  void applyPondition(InvitationCodeBean codeBean);
  void invitationMyInvite(InviteListDataBean codeBean);

  void sevenDayEarnings(SevenDayEarningsBean codeBean);
  void userPriceList(PriceListBean codeBean);


  void upgradeAlert(upgradeAlertBean codeBean);
  void receiveSuccess(Object codeBean);

}
