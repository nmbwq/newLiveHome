package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.NewMessageBean;
import shangri.example.com.shangri.model.bean.response.anchorDetailBean;

/**
 * Description:
 * Data：2018/11/19-18:20
 * Author: lin
 */
public interface NewMessageView extends BaseView {
    void getMessage(NewMessageBean newMessageBean);

    void isReadMessage();

    void anchorDetail(anchorDetailBean bean);
}
