package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.MoreBean;

public interface MoreArticlesView   extends   BaseView{


    void  moreArticlesSucceed(MoreBean moreBean);
    void Browse(int position);

}
