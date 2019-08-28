package shangri.example.com.shangri.ui.activity;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import shangri.example.com.shangri.Constant;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.TipsDetailInfoBean;
import shangri.example.com.shangri.presenter.TipsDetailPresenter;
import shangri.example.com.shangri.presenter.view.TipsDetailView;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.RegexUtil;
import shangri.example.com.shangri.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 公告详情
 * Created by chengaofu on 2017/6/29.
 */

public class MsgDetailActivity extends BaseActivity <TipsDetailView, TipsDetailPresenter> implements TipsDetailView {

    @BindView(R.id.msg_detail_title)
    TextView mMsgTitle;
    @BindView(R.id.msg_detail_date)
    TextView mMsgDate;
    @BindView(R.id.msg_detail_content)
    TextView mMsgContent;

    @Override
    protected TipsDetailPresenter createPresenter() {
        return new TipsDetailPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        long id = getIntent().getLongExtra("id",0);
        mPresenter.getMsgById(id);
    }

    @Override
    public void getMsgById(TipsDetailInfoBean bannerInfoBean) {
        mMsgTitle.setText(bannerInfoBean.getTitle());
        mMsgDate.setText(RegexUtil.format(bannerInfoBean.getReleaseTime(),RegexUtil.defaultDatePattern));
        mMsgContent.setText(Html.fromHtml(bannerInfoBean.getContent()));

    }

    @OnClick({ R.id.msg_detail_back})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_detail_back:
                finish();
                break;
        }
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_msg_detail;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_msg_detail;
    }

    @Override
    public void requestFailed(String message) {
        if(TextUtils.isEmpty(message)) return;
        if(message.contains(String.valueOf(Constant.CODE_100027))){
            ToastUtil.showShort("token 失效，需重新登录");
            ActivityUtils.startActivity(this, NewLoginUserActivity.class);
            finish();
        }
    }

}
