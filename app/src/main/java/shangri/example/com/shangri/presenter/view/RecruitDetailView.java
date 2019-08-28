package shangri.example.com.shangri.presenter.view;

import com.chad.library.adapter.base.BaseQuickAdapter;

import shangri.example.com.shangri.model.bean.response.RecruitDetailBean;
import shangri.example.com.shangri.model.bean.response.ResumeDetailBean;

/**
 * Description:
 * Data：2018/11/4-13:09
 * Author: lin
 */
public interface RecruitDetailView extends BaseView {
    void getRecruitDetail(RecruitDetailBean recruitDetailBean);

    void getResumeDetailBean(ResumeDetailBean resumeDetailBean);
}
