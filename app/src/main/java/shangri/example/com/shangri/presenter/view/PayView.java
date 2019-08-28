package shangri.example.com.shangri.presenter.view;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import shangri.example.com.shangri.model.bean.request.PayrequestBean;
import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.DaysPriceBean;
import shangri.example.com.shangri.model.bean.response.LegalPayResponseTaskBean;
import shangri.example.com.shangri.model.bean.response.MyAnchorListBeanResponse;
import shangri.example.com.shangri.model.bean.response.MyTaskBean;
import shangri.example.com.shangri.model.bean.response.ParticipationTaskBean;
import shangri.example.com.shangri.model.bean.response.PayResponseTaskBean;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.view.BaseView;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface PayView extends BaseView {
  void  getorderInfo(PayResponseTaskBean resultBean);
  void  getLegalInfo(LegalPayResponseTaskBean resultBean);

  void  recruitPay_package(LegalPayResponseTaskBean resultBean);

  void  orderGuild(DaysPriceBean resultBean);
  void memberLate(timeBean shareBean);

}
