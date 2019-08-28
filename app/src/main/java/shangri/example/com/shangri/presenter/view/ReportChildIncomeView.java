package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.CountBean;
import shangri.example.com.shangri.model.bean.response.GuildListBean;
import shangri.example.com.shangri.model.bean.response.ResShareBean;
import shangri.example.com.shangri.model.bean.response.RespAdminBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.model.bean.response.timeBean;

public interface ReportChildIncomeView extends BaseView {
    void loadData(GuildListBean listBean);

    void loadManagerData(RespAdminBean listBean);

    void onShare(ResShareBean shareBean);

    void companyMy(companyMyBean shareBean);

    void mineCount(CountBean resultBean);

    void memberLate(timeBean resultBean);

    void SupportFromList(SupportFromList resultBean);


}
