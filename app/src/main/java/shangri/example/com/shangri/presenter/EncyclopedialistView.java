package shangri.example.com.shangri.presenter;


import shangri.example.com.shangri.model.bean.response.EncyclopediaList;
import shangri.example.com.shangri.model.bean.response.MessageResonse;
import shangri.example.com.shangri.presenter.view.BaseView;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface EncyclopedialistView extends BaseView {

    void encyclopediaPlatfromList(EncyclopediaList resultBean);//角色选择

    void messageList(MessageResonse resultBean);//角色选择


    void wikiFocus(EncyclopediaList resultBean);//关注列表

    void addRuzhu();//我要入驻

    void wikiDoCollect();//关注

    void wikiCancelCollect();//关注


    void messageRead();
    void consultShare();



}
