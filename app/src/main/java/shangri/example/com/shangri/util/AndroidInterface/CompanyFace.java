package shangri.example.com.shangri.util.AndroidInterface;

/**
 * Created by Administrator on 2018/5/14.
 */

public interface CompanyFace {
    //    公会解绑管理员
    void adminRelieve(String guild_id, String admin_reg_id);

    //   公司离职管理员
    void adminLeave(String admin_reg_id);

    //    公司离职主播
    void companyBreakanchor(String guild_id, String admin_reg_id);

    //管理员移除主播
    void adminBreakanchorFace(String guild_id, String anchor_string);

    //管理员添加主播
    void adminAddanchorFace(String guild_id, String anchor_string);


}
