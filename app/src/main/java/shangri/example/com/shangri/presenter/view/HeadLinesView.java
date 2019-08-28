package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.CountBean;
import shangri.example.com.shangri.model.bean.response.HeadLinesData;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface HeadLinesView extends BaseView{

    void bannerDetail(HeadLinesData resultBean);//咨询banner图数据

    void praise(int position);

    void Browse(int position);

    void search(HeadLinesData resultBean);


    void Collect(int position);
    void mineCount(CountBean resultBean);
}
