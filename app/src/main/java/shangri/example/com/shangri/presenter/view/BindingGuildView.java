package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.AddSeccussBean;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.HostBindingBean;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface BindingGuildView extends BaseView{

    void onSuccess();//角色选择
    void myGuildDtaList(MyGuildListDataBean resultBean);//角色选择
    void SupportFromList(SupportFromList bean);//角色选择
    void guildUpgrade(AddSeccussBean bean);//角色选择

    void  bingSuccess();//   主播绑定成功

    void platfromType(BossPlatBean mAccountDataBean);//   获取平台

    void Uploading(legalIndexBean resultBean);//   上传图片
}
