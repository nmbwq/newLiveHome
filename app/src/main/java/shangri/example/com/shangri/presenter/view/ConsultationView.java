package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.BanagetBean;
import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.CultivateBean;
import shangri.example.com.shangri.model.bean.response.HeadLinesData;
import shangri.example.com.shangri.model.bean.response.LookBannerBean;
import shangri.example.com.shangri.model.bean.response.MoreBean;
import shangri.example.com.shangri.model.bean.response.NewsBean;
import shangri.example.com.shangri.model.bean.response.TrainingArticleBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface ConsultationView extends BaseView{

    void bannerDetail(BannerHomeLookBean resultBean);//咨询banner图数据

    void praise(int position);

    void Browse(int position);

    void search(HeadLinesData resultBean);

    void Collect(int position);
    void PeixunSearch(BannerHomeLookBean resultBean);


    void  newsSucceed(NewsBean  newsBean);
    void cultivateSucceed(CultivateBean cultivateBean);

    void  lookBannerSucceed(LookBannerBean lookBannerBean);

    void   trainingArticleSucceed(TrainingArticleBean  trainingArticleBean);



}
