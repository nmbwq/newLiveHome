package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.NewsTypeInfoBean;

import java.util.List;

/**
 * 多种类型
 * Created by chengaofu on 2017/6/29.
 */

public interface NewsTypeView extends BaseView{
    //获取资讯名称
    void getType(List<NewsTypeInfoBean> newsTypeInfoBeanList);


}
