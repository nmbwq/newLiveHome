package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.SelectGuildBean;
import shangri.example.com.shangri.model.bean.response.SortModelBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface GuildListView extends BaseView{

    void listGuildData(SortModelBean resultBean);//角色选择

    void selectGuild(SelectGuildBean resultBean);
}
