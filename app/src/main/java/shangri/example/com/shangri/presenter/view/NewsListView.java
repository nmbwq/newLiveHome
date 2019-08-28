package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.BannerInfoBean;
import shangri.example.com.shangri.model.bean.response.PageDataBean;
import shangri.example.com.shangri.model.bean.response.PageInfoBean;
import shangri.example.com.shangri.model.bean.response.PraiseInfoBean;

import java.util.List;

/**
 * 资讯列表
 * Created by chengaofu on 2017/6/28.
 */

public interface NewsListView extends BaseView{

    //请求得到资讯列表
    void requestNewsList(List<PageDataBean> pageData, PageInfoBean pageInfo);
    //点赞(取消点赞)
    void praise(PraiseInfoBean resultBean);
    //得到banner图
    void getBannerDetailByTypeId(List<BannerInfoBean> bannerInfoBeanList);


}
