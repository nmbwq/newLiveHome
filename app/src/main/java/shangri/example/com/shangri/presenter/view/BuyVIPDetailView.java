package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.BuyDetailBean;
import shangri.example.com.shangri.model.bean.response.BuyVIPDetailBean;

/**
 * Description:
 * Data：2018/11/7-10:53
 * Author: lin
 */
public interface BuyVIPDetailView extends BaseView {
    //购买明细
    void buyDetail(BuyVIPDetailBean detailBean);
}
