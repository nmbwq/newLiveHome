package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.RecommendBean;

/**
 * Description:小播推荐
 * Data：2018/11/14-17:54
 * Author: lin
 */
public interface RecommendView extends BaseView {
    void getRecommendList(RecommendBean bean);
}
