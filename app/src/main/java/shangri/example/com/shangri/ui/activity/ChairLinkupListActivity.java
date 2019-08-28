package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.BossDataBean;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.PositionListBean;
import shangri.example.com.shangri.model.bean.response.anchorDetailBean;
import shangri.example.com.shangri.model.bean.response.legalMypagerBean;
import shangri.example.com.shangri.model.bean.response.sendResumeBean;
import shangri.example.com.shangri.model.bean.response.upListBean;
import shangri.example.com.shangri.presenter.LawWorksPayPresenter;
import shangri.example.com.shangri.presenter.view.LowWorksPayView;
import shangri.example.com.shangri.ui.adapter.ListBeanAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.ui.webview.AnchorDetailWebView;
import shangri.example.com.shangri.util.AndroidInterface.ApllayFace;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.CornersTransform;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 主播投递列表
 * Created by admin on 2017/12/22.
 */

public class ChairLinkupListActivity extends BaseActivity<LowWorksPayView, LawWorksPayPresenter> implements LowWorksPayView, ApllayFace {
    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.activity_empty_linshi)
    LinearLayout activity_empty_linshi;

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right)
    TextView right;


    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;
    //1 正常 2审核中 3审核失败 4已关闭  0全部
    int type = 0;
    int mPageNum = 0;


    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    private ProgressDialogFragment mProgressDialog;
    private int currPage = 1;

    private ListBeanAdapter mAdapter;
    private List<upListBean.ListBean> mList = new ArrayList<>();


    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_combo_list;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_combo_list;
    }

    @Override
    protected LawWorksPayPresenter createPresenter() {
        return new LawWorksPayPresenter(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!NetWorkUtil.isNetworkConnected(ChairLinkupListActivity.this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            sv_partol.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            currPage = 1;
            mPresenter.upList();
        }
    }

    @Override
    protected void initViewsAndEvents() {
        initSpringView();
        title.setText("已沟通");
        right.setVisibility(View.GONE);
        reload();
        CompanyInterfaceUtils.setApllayBack(this);
        rv_partol.setLayoutManager(new FastLinearScrollManger(ChairLinkupListActivity.this));
        mAdapter = new ListBeanAdapter(this, R.layout.item_my_checkmanager2, mList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        rv_partol.setAdapter(mAdapter);
    }

    public void reload() {
        if (!NetWorkUtil.isNetworkConnected(ChairLinkupListActivity.this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            sv_partol.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(this.getSupportFragmentManager());
            currPage = 1;
            mPresenter.upList();
        }
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    private void initSpringView() {
        sv_partol.setGive(SpringView.Give.TOP);
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(this));
        sv_partol.setFooter(new SpringViewFooter(this));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                reload();
            }

            @Override
            public void onLoadmore() {
                requestTastList();
            }
        });

    }

    private void requestTastList() {
        if (currPage < mPageNum) {
            currPage++;
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(this.getSupportFragmentManager());
            mPresenter.upList();
        } else {
            sv_partol.onFinishFreshAndLoad(); //停止加载
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.setting_back, R.id.right, R.id.reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.reload:
                reload();
                break;
            case R.id.right:
                break;

        }
    }


    @Override
    public void legalmyPackage(legalMypagerBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void positionList(PositionListBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        mPageNum = resultBean.getList().getLast_page();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

    }

    @Override
    public void sendResume(sendResumeBean resultBean) {

    }

    @Override
    public void upList(upListBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (currPage == 1) {
            mList.clear();
            if (resultBean.getList().size() > 0) {
                sv_partol.setVisibility(View.VISIBLE);
                activity_empty_linshi.setVisibility(View.GONE);
            } else {
                sv_partol.setVisibility(View.GONE);
                activity_empty_linshi.setVisibility(View.VISIBLE);
            }
        }
        mAdapter.addAll(resultBean.getList());
        mList = mAdapter.getAll();
    }

    @Override
    public void anchorDetail(anchorDetailBean resultBean) {

    }

    @Override
    public void noLikeList(BossDataBean resultBean) {

    }

    @Override
    public void companyAdd(NewCompanyBean resultBean) {

    }

    @Override
    public void limitNumber(IsFaceResponse resultBean) {

    }

    @Override
    public void upAnchor() {

    }

    @Override
    public void adminApllayFace(String registrants_id, String guild_id) {

    }

    @Override
    public void sendInfo(anchorDetailBean.UserInfoBean bean) {

    }

    upListBean.ListBean getInfo = new upListBean.ListBean();

    @Override
    public void sendInfo1(upListBean.ListBean bean) {
        Log.d("Debug", "interfacec返回到这里");
        //发送形象卡操作
        getInfo = bean;
        if (bean.getTelephone().length() == 0) {
//            showCardPopupWindow1(bean.getResume_url(), bean.getResume_telephone());
//
//            Intent intent = new Intent(ChairLinkupListActivity.this, symbolWebView.class);
//            intent.putExtra("url", getInfo.getInfo_url());
//            startActivity(intent);
            Intent intent = new Intent(ChairLinkupListActivity.this, AnchorDetailWebView.class);
            intent.putExtra("id", getInfo.getResume_id() + "");
            intent.putExtra("Register_id", getInfo.getAnchor_id() + "");
            intent.putExtra("Telephone", getInfo.getResume_telephone() + "");
            intent.putExtra("Nickname", getInfo.getNickname() + "");
            intent.putExtra("Info_url", getInfo.getInfo_url() + "");
            intent.putExtra("isFromOther", true);
            ChairLinkupListActivity.this.startActivity(intent);
        } else {
            showPopupWindow1(bean.getNickname(), bean.getTelephone());
        }
    }


    private static PopupWindow mCardPopWindow1;

    /**
     * 展示形象卡
     */
    private void showCardPopupWindow1(String url, final String phone) {
        //设置contentView
        final View contentView = LayoutInflater.from(ChairLinkupListActivity.this).inflate(R.layout.have_resume_card_layout1, null);
        mCardPopWindow1 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mCardPopWindow1.setContentView(contentView);
        //设置各个控件的点击响应
        final ImageView im_photo = contentView.findViewById(R.id.im_photo);
        Glide.with(ChairLinkupListActivity.this)
                .load(url)
                .placeholder(R.mipmap.bg_touxiang)
                .crossFade()
                .transform(new CornersTransform(ChairLinkupListActivity.this, 10))
                .into(im_photo);

        TextView tv_next = contentView.findViewById(R.id.tv_next);
        tv_next.setText("立即沟通");
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCardPopWindow1.dismiss();
            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + phone);
                intent.setData(data);
                startActivity(intent);
//                callPhone("18668121550");
                mPresenter.upAnchor(getInfo.getAnchor_id() + "", getInfo.getRecruit_id() + "", getInfo.getResume_message_id() + "", getInfo.getResume_id() + "");
                mCardPopWindow1.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(ChairLinkupListActivity.this).inflate(R.layout.bosswebview, null);
        mCardPopWindow1.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindow1;

    /**
     * * 拨打电话
     */


    private void showPopupWindow1(final String text, final String phone) {
        //设置contentView
        final View contentView = LayoutInflater.from(ChairLinkupListActivity.this).inflate(R.layout.compact_add_gonghui5, null);
        mPopWindow1 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow1.setContentView(contentView);
        //设置各个控件的点击响应
        final LinearLayout ll_text = contentView.findViewById(R.id.ll_text);
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_phone_number = contentView.findViewById(R.id.tv_phone_number);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        tv_next.setText("立即沟通");
        tv_content.setText(text + "给您留了电话，马上撩一下");
        tv_phone_number.setText(phone);
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("Debug", "两个年份之间相差" + TimeUtil.yearChect("1994"));
//                CutImage(ll_text);
                mPopWindow1.dismiss();
            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + phone);
                intent.setData(data);
                startActivity(intent);
//                callPhone("18668121550");
                mPresenter.upAnchor(getInfo.getAnchor_id() + "", getInfo.getRecruit_id() + "", getInfo.getResume_message_id() + "", getInfo.getResume_id() + "");
                mPopWindow1.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(ChairLinkupListActivity.this).inflate(R.layout.bosswebview, null);
        mPopWindow1.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

}
