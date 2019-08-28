package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.BuyDetailBean;

/**
 * Description:
 * Data：2018/11/7-10:53
 * Author: lin
 */
public interface BuyDetailView extends BaseView {
    //购买明细
    void buyDetail(BuyDetailBean detailBean);
}
