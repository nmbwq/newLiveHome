package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.TipsDetailInfoBean;

/**
 * 公告详情
 * Created by chengaofu on 2017/6/29.
 */

public interface TipsDetailView extends BaseView{

    void getMsgById(TipsDetailInfoBean bannerInfoBeanList);

}
