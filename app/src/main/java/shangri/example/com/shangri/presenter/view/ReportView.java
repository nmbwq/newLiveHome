package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.request.ReqReportBean;

public interface ReportView extends BaseView {
    void loadData(ReqReportBean reportBean);
}
