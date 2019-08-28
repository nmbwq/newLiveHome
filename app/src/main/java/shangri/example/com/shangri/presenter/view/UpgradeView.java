package shangri.example.com.shangri.presenter.view;

import java.util.List;

import shangri.example.com.shangri.model.bean.response.AppVersionBean;
import shangri.example.com.shangri.model.bean.response.liftRuleBean;

/**
 *
 * Created by chengaofu on 2017/7/1.
 */

public interface UpgradeView extends BaseView{

    void liftRule(liftRuleBean resultBean); //立即升级接口

    void handUpgrade(); //立即升级接口
}
