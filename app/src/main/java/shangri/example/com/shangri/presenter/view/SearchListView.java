package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.CollectBean;
import shangri.example.com.shangri.model.bean.response.InterlocutionBean;

/**
 * 搜索
 * Created by chengaofu on 2017/6/28.
 */

public interface SearchListView extends BaseView{
    void search(BannerHomeLookBean resultBean);

    void interlocution(InterlocutionBean resultBean); //

    void good(int position);

    void bad(int position);

    void getCollect(CollectBean resultBean);
    void addRequestCollect(int position);
    void DeleteRequestCollect(int position);

    void addPeixunLike(int position);
    void DeletePeixunLike(int position);

    void addPeixunColect(int position);
    void DeletePeixunColect(int position);

    void praise(int position);

    void Browse(int position);
}
