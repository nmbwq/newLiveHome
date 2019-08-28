package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.AnchorSerchBean;
import shangri.example.com.shangri.model.bean.response.companyDetailBean;
import shangri.example.com.shangri.model.bean.response.companyListBean;
import shangri.example.com.shangri.model.bean.response.partSelectBean;
import shangri.example.com.shangri.model.bean.response.taskSelectListBean;

/**
 * 公司通讯录
 * Created by chengaofu on 2017/6/27.
 */

public interface SelectmanView extends BaseView {
    void taskSelectanchor(taskSelectListBean resultBean);//公司列表
    void anchorOplayer(AnchorSerchBean resultBean);//公司列表

    void cacheAdd();

    void Add();
}
