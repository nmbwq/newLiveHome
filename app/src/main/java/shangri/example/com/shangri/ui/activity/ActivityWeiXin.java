package shangri.example.com.shangri.ui.activity;

import android.view.View;
import android.widget.ImageView;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.BasePresenter;
//import com.tencent.mm.opensdk.modelbiz.JumpToBizProfile;
//import com.tencent.mm.opensdk.openapi.IWXAPI;
//import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 微信关注
 * Created by mschen on 2017/6/30.
 */

public class ActivityWeiXin extends BaseActivity {

    @BindView(R.id.weixin_follow)
    ImageView mWeixinFollow;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_wei_xin;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_wei_xin;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {

        mWeixinFollow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String appId = getResources().getString(R.string.weixinAppid);//开发者平台ID
                return true;
            }
        });

    }

    @OnClick({R.id.weixin_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.weixin_back:        //返回
                finish();
                break;
        }
    }

}
