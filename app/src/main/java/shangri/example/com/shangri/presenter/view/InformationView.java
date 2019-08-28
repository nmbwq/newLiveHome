package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.LabelDataBean;
import shangri.example.com.shangri.model.bean.response.SortModelBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface InformationView extends BaseView {

    void nickname();//角色选择

    void PlatfromList(SortModelBean resultBean);

    void labelData(LabelDataBean resultBean);

    void addInfo(String s);
}
