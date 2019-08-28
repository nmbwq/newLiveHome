package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.GuildListBean;
import shangri.example.com.shangri.model.bean.response.ResShareBean;
import shangri.example.com.shangri.model.bean.response.RespAdminBean;
import shangri.example.com.shangri.model.bean.response.RespAnchoDetailBean;

public interface ArchoDetailView extends BaseView {
    void loadData(RespAnchoDetailBean listBean);

    void anchorDelete();
}
