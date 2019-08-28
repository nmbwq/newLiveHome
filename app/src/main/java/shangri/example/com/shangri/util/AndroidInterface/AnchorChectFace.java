package shangri.example.com.shangri.util.AndroidInterface;

import shangri.example.com.shangri.model.bean.response.AllListBean;

/**
 * Created by Administrator on 2018/5/14.
 */

public interface AnchorChectFace {
    //    主播审核接口
    void anchorCheckFace(String register_guild_id, String check_status, String check_mark);

    void adminPass(String admin_reg_id, String guild_id, String status, String mask);
    void robhim(AllListBean.ListBean bean);
}
