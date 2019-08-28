package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.ReadPhotoBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;
import shangri.example.com.shangri.model.bean.response.wantedStatusBean;

/**
 * 用户登录
 * Created by chengaofu on 2017/6/27.
 */

public interface ResumeView extends BaseView {

    void resumeUploading(legalIndexBean resultBean);

    void resumeAdd();
    void resumePhotoDel();
    void resumePhotoFirst();


    void resumeUpdate();

    void readPhoto(ReadPhotoBean resultBean);

    void resumeIndex(ResumeIndexBean resultBean);
    void platfromType(BossPlatBean resultBean);
    void wantedStatus(wantedStatusBean resultBean);



}
