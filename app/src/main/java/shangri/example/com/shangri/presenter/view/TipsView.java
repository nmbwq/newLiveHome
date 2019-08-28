package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.PageInfoBean;
import shangri.example.com.shangri.model.bean.response.TipsPageDataBean;

import java.util.List;

/**
 * 平台公告
 * Created by chengaofu on 2017/6/29.
 */

public interface TipsView extends BaseView{

    void queryMessageList(List<TipsPageDataBean> pageData, PageInfoBean pageInfo);

//    public void queryMessageFailed();
}
