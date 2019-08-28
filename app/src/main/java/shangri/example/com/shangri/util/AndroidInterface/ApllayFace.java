package shangri.example.com.shangri.util.AndroidInterface;

import shangri.example.com.shangri.model.bean.response.anchorDetailBean;
import shangri.example.com.shangri.model.bean.response.upListBean;

/**
 * Created by Administrator on 2018/5/14.
 */

public interface ApllayFace {
    //    公会解绑管理员
    void adminApllayFace(String registrants_id, String guild_id);

    void sendInfo(anchorDetailBean.UserInfoBean bean);

    void sendInfo1(upListBean.ListBean bean);
}
