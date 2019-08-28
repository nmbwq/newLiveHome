package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import shangri.example.com.shangri.adapter.RecommendAdapter1;
import shangri.example.com.shangri.adapter.RecommendAdapter2;
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
import shangri.example.com.shangri.ui.adapter.AnchorDetailAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.ui.webview.AnchorDetailWebView;
import shangri.example.com.shangri.ui.webview.ZhiWeiWebView;
import shangri.example.com.shangri.util.AndroidInterface.ApllayFace;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.CornersTransform;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 简历详情
 * Created by admin on 2017/12/22.
 */

public class AnchorDropDetailActivity extends BaseActivity<LowWorksPayView, LawWorksPayPresenter> implements LowWorksPayView, ApllayFace {
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
    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.im_hot)
    ImageView imHot;
    @BindView(R.id.tv_anchor_name)
    TextView tvAnchorName;
    @BindView(R.id.tv_xianxia)
    TextView tvXianxia;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.rl1)
    RelativeLayout rl1;
    @BindView(R.id.tv_plat_name)
    TextView tvPlatName;
    @BindView(R.id.tv_welface)
    TextView tvWelface;
    @BindView(R.id.tv_low_money)
    TextView tvLowMoney;
    @BindView(R.id.rl2)
    RelativeLayout rl2;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.im1)
    ImageView im1;
    @BindView(R.id.tv_adress)
    TextView tvAdress;
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.im_is_guanfang)
    ImageView imIsGuanfang;
    @BindView(R.id.rl3)
    RelativeLayout rl3;
    @BindView(R.id.item_boss_head)
    RelativeLayout item_boss_head;


    private ProgressDialogFragment mProgressDialog;
    private int currPage = 1;

    private RecommendAdapter2 mAdapter;
    private List<anchorDetailBean.UserInfoBean> mList = new ArrayList<>();


    String type_name = "";
    String recruit_id = "";
    //选择item的数据
    anchorDetailBean.UserInfoBean getInfo = new anchorDetailBean.UserInfoBean();


    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_combo_list1;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_combo_list1;
    }

    @Override
    protected LawWorksPayPresenter createPresenter() {
        return new LawWorksPayPresenter(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!NetWorkUtil.isNetworkConnected(AnchorDropDetailActivity.this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            sv_partol.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            currPage = 1;
            mPresenter.sendResume();
        }
    }

    @Override
    protected void initViewsAndEvents() {
        initSpringView();
        CompanyInterfaceUtils.setApllayBack(this);
        type_name = getIntent().getStringExtra("type_name");
        recruit_id = getIntent().getStringExtra("recruit_id");
        Log.d("Debug", "传递过来recruit_id" + recruit_id);
        Log.d("Debug", "传递过来type_name" + type_name);
        if (type_name == null) {
            type_name = "";
        }
        if (recruit_id == null) {
            recruit_id = "";
        }
        title.setText(type_name);
        reload();
        right.setVisibility(View.GONE);
        rv_partol.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new RecommendAdapter2(this, R.layout.item_host_collection, mList, new RecommendAdapter2.onItemClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(AnchorDropDetailActivity.this, LookAnchorDetailActivity.class);
                intent.putExtra("id", mAdapter.getAll().get(position).getResume_id());
                startActivity(intent);
            }
        });
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        rv_partol.setAdapter(mAdapter);
    }


    public void reload() {
        if (!NetWorkUtil.isNetworkConnected(AnchorDropDetailActivity.this)) {
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
            mPresenter.anchorDetail(recruit_id);
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
            mPresenter.anchorDetail(recruit_id);
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

    @OnClick({R.id.setting_back, R.id.item_boss_head, R.id.right, R.id.reload})
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
            case R.id.item_boss_head:
                PositionListBean.ListBean.DataBean dataBean = new PositionListBean.ListBean.DataBean();
                dataBean.setId(Integer.parseInt(data.getId()));
                dataBean.setTitle(data.getTitle());
                dataBean.setPay_low(Integer.parseInt(data.getPay_low()));
                dataBean.setPay_high(Integer.parseInt(data.getPay_high()));
                dataBean.setCompany(data.getCompany());
                dataBean.setJob_method(Integer.parseInt(data.getJob_method()));
                dataBean.setJob_plat(data.getJob_plat());
                dataBean.setAnchor_type(data.getAnchor_type());
                dataBean.setSalary_type(Integer.parseInt(data.getSalary_type()));
                dataBean.setWelfare(data.getWelfare());
                dataBean.setWork_position(data.getWork_position());
                dataBean.setWork_address(data.getWork_address());
                dataBean.setHot(Integer.parseInt(data.getHot()));
                dataBean.setContact_phone(data.getContact_phone());
                dataBean.setPublish_type(Integer.parseInt(data.getPublish_type()));
                dataBean.setLightspot(data.getLightspot());
                dataBean.setQq(data.getQq());
                dataBean.setWeixin(data.getWeixin());
                dataBean.setEmail(data.getEmail());
                dataBean.setStatus(Integer.parseInt(data.getStatus()));
                dataBean.setJob_description(data.getJob_description());
                dataBean.setInfo_url(data.getInfo_url());
                dataBean.setCheck_number_real(Integer.parseInt(data.getCheck_number_real()));
                dataBean.setShare_number(data.getShare_number());
                dataBean.setAnchor_send_resume(data.getAnchor_send_resume());
                dataBean.setPlat_name(data.getPlat_name());
                dataBean.setType_name(data.getType_name());
                dataBean.setIs_ggw(data.getIs_ggwl());
                startActivity(new Intent(this, ZhiWeiWebView.class).putExtra("bean", dataBean));
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
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

    }

    @Override
    public void upList(upListBean resultBean) {

    }

    anchorDetailBean.RecruitBean data;

    @Override
    public void anchorDetail(anchorDetailBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        data = resultBean.getRecruit();
        //        是否广告位 1是 0否
        if (data.getIs_ggwl() == 1 || data.getHot().equals("1")) {
            imHot.setVisibility(View.VISIBLE);
        } else {
            imHot.setVisibility(View.GONE);
        }

//        发布类型 1系统（爬取的）2 官方（手动发布）
        if (data.getPublish_type().equals("3")) {
            imIsGuanfang.setVisibility(View.VISIBLE);
        } else {
            imIsGuanfang.setVisibility(View.GONE);
        }
        switch (data.getJob_method()) {
            case "1":
                tvXianxia.setText("线上");
                break;
            case "2":
                tvXianxia.setText("线下");
                break;
            case "3":
                tvXianxia.setText("线上/线下");
                break;
        }
        switch (data.getSalary_type()) {
            case "1":
                tvWeek.setText("月结");
                break;
            case "2":
                tvWeek.setText("周结");
                break;
            case "3":
                tvWeek.setText("日结");
                break;
            case "4":
                tvWeek.setText("月结/周结/日结");
                break;
        }
        tvMoney.setText(Integer.parseInt(data.getPay_low()) / 1000 + "K" + " - " + Integer.parseInt(data.getPay_high()) / 1000 + "K");
        if (data.getPlat_name().size() == 0) {
            tvPlatName.setVisibility(View.GONE);
        } else {
            tvPlatName.setVisibility(View.VISIBLE);
            tvPlatName.setText(data.getPlat_name().get(0).getPlat_name());
        }

        if (data.getWelfare().size() == 0) {
            tvWelface.setVisibility(View.GONE);
        } else {
            tvWelface.setVisibility(View.VISIBLE);
            if (data.getWelfare().size() == 1) {
                tvWelface.setText(data.getWelfare().get(0));
            } else {
                tvWelface.setText(data.getWelfare().get(0) + " | " + data.getWelfare().get(1));
            }
        }
        //底薪最高价和最低价
        if (Double.parseDouble(data.getKeep_pay()) == 0) {
            tvLowMoney.setVisibility(View.GONE);
        } else {
            if (Double.parseDouble(data.getKeep_pay()) >= 1000) {
                tvLowMoney.setText("底薪" + Integer.parseInt(data.getKeep_pay()) / 1000 + "K");
            } else {
                tvLowMoney.setText("底薪" + Integer.parseInt(data.getKeep_pay()));
            }
            tvLowMoney.setVisibility(View.VISIBLE);
        }

        tvAdress.setText(data.getWork_position());
        tvCompanyName.setText(data.getCompany());

        if (currPage == 1) {
            mList.clear();
            if (resultBean.getUserInfo().size() > 0) {
                rv_partol.setVisibility(View.VISIBLE);
                activity_empty_linshi.setVisibility(View.GONE);
            } else {
                rv_partol.setVisibility(View.GONE);
                activity_empty_linshi.setVisibility(View.VISIBLE);
            }
        }
        mAdapter.addAll(resultBean.getUserInfo());
        mList = mAdapter.getAll();


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


    private static PopupWindow mCardPopWindow1;

    /**
     * 展示形象卡
     */
    private void showCardPopupWindow1(String url, final String phone) {
        //设置contentView
        final View contentView = LayoutInflater.from(AnchorDropDetailActivity.this).inflate(R.layout.have_resume_card_layout1, null);
        mCardPopWindow1 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mCardPopWindow1.setContentView(contentView);
        //设置各个控件的点击响应
        final ImageView im_photo = contentView.findViewById(R.id.im_photo);
        Glide.with(AnchorDropDetailActivity.this)
                .load(url)
                .placeholder(R.mipmap.bg_touxiang)
                .crossFade()
                .transform(new CornersTransform(AnchorDropDetailActivity.this, 10))
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
        View rootview = LayoutInflater.from(AnchorDropDetailActivity.this).inflate(R.layout.bosswebview, null);
        mCardPopWindow1.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindow1;

    /**
     * * 拨打电话
     */


    private void showPopupWindow1(final String text, final String phone) {
        //设置contentView
        final View contentView = LayoutInflater.from(AnchorDropDetailActivity.this).inflate(R.layout.compact_add_gonghui5, null);
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
        View rootview = LayoutInflater.from(AnchorDropDetailActivity.this).inflate(R.layout.bosswebview, null);
        mPopWindow1.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void adminApllayFace(String registrants_id, String guild_id) {

    }

    @Override
    public void sendInfo(anchorDetailBean.UserInfoBean bean) {
        Log.d("Debug", "interfacec返回到这里");
        mPresenter.whetherCheck(getInfo.getAnchor_id() + "", getInfo.getRecruit_id() + "", getInfo.getResume_message_id() + "");
        //发送形象卡操作
        getInfo = bean;
        if (bean.getTelephone().length() == 0) {
            Intent intent = new Intent(AnchorDropDetailActivity.this, AnchorDetailWebView.class);
            intent.putExtra("id", getInfo.getResume_id() + "");
            intent.putExtra("Register_id", getInfo.getAnchor_id() + "");
            intent.putExtra("Telephone", getInfo.getResume_telephone() + "");
            intent.putExtra("Nickname", getInfo.getNickname() + "");
            intent.putExtra("Info_url", getInfo.getInfo_url() + "");
            intent.putExtra("isFromOther", true);
            startActivity(intent);

        } else {
            showPopupWindow1(bean.getNickname(), bean.getTelephone());
        }
    }

    @Override
    public void sendInfo1(upListBean.ListBean bean) {

    }
}
