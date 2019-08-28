package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.CollectBean;
import shangri.example.com.shangri.model.bean.response.InterlocutionBean;

/**
 * 我的收藏
 * Created by chengaofu on 2017/6/28.
 */

public interface CollectListView extends BaseView{
    void search(CollectBean resultBean);
}
