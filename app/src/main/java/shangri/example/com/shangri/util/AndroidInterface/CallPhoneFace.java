package shangri.example.com.shangri.util.AndroidInterface;

import shangri.example.com.shangri.model.bean.response.AllListBean;
import shangri.example.com.shangri.model.bean.response.wantListBean;

/**
 * Created by Administrator on 2018/5/14.
 */

public interface CallPhoneFace {
    void TakePhone(int position, String phone, String name, String telphone, String anchor_id, String resume_id, String is_linkup);

    void Takecllock(String position, int is_collect, String resume_id);

    void refush();

    void yueliao(String resume_id, int is_chat, wantListBean.ResumesBean bean);

    void yueliao(String resume_id, int is_chat, AllListBean.ListBean bean);

}
