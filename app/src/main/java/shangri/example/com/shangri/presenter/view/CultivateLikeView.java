package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.ReadPhotoBean;
import shangri.example.com.shangri.model.bean.response.RecruitDetailBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface CultivateLikeView extends BaseView{
    void praise();
    void collect();
    void consultShare();
    void jobStatus();
    void comment();

    void upAnchor();

    void residueNumber();

    void  companyAdd(NewCompanyBean resultBean);
    void  commentNum(BannerHomeLookBean resultBean);
    void  resumeDetail(ResumeIndexBean resultBean);
    void  getRecruitDetail(RecruitDetailBean resultBean);

}
