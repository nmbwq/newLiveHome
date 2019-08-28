package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.NewsDetailInfoBean;
import shangri.example.com.shangri.model.bean.response.PraiseInfoBean;

/**
 * 资讯详情
 * Created by chengaofu on 2017/6/29.
 */

public interface NewsDetailView extends BaseView {

    void getNewsDetail(NewsDetailInfoBean bean);

    void praise(PraiseInfoBean resultBean); //点赞
    void praise(); //点赞

    void collect();  //   收藏
}
