package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.MessagesBean;

/**
 * Description:
 * Data：2018/11/4-9:58
 * Author: lin
 */
public interface MessagesListView extends BaseView{
    void getMessage(MessagesBean messagesBean);
    void setIsRead(MessagesBean messagesBean);
}
