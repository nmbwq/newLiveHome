package shangri.example.com.shangri.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.adapter.CommonAdapter;
import shangri.example.com.shangri.adapter.ViewHolder;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.LeaveBean;
import shangri.example.com.shangri.model.bean.response.companyAdminListBean;
import shangri.example.com.shangri.model.bean.response.companyBreaklistListBean;
import shangri.example.com.shangri.model.bean.response.companyOrgBean;
import shangri.example.com.shangri.model.bean.response.justBean;
import shangri.example.com.shangri.presenter.CreatPresenter;
import shangri.example.com.shangri.presenter.view.CreatCompanyView;
import shangri.example.com.shangri.swipeMenuListView.SwipeMenu;
import shangri.example.com.shangri.swipeMenuListView.SwipeMenuCreator;
import shangri.example.com.shangri.swipeMenuListView.SwipeMenuItem;
import shangri.example.com.shangri.swipeMenuListView.SwipeMenuListView;
import shangri.example.com.shangri.ui.adapter.CompanyOneAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.AndroidInterface.CompanyFace;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;


/**
 *
 *
 */

public class CompamyInfoActivity extends BaseActivity<CreatCompanyView, CreatPresenter> implements CreatCompanyView, CompanyFace {

    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.im_sousuo)
    ImageView imSousuo;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.im_photo)
    ImageView imPhoto;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_adress)
    TextView tvAdress;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;

    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.im_manager_up)
    ImageView imManagerUp;
    @BindView(R.id.tv_gonghui_name)
    TextView tvGonghuiName;
    @BindView(R.id.tv_manager_number)
    TextView tvManagerNumber;
    @BindView(R.id.ll_manager)
    RelativeLayout llManager;
    @BindView(R.id.rv_manager_partol)
    SwipeMenuListView rvManagerPartol;
    @BindView(R.id.im_leave_up)
    ImageView imLeaveUp;
    @BindView(R.id.tv_leave_name)
    TextView tvLeaveName;
    @BindView(R.id.tv_leave_number)
    TextView tvLeaveNumber;
    @BindView(R.id.ll_leave)
    RelativeLayout llLeave;
    @BindView(R.id.rv_leave_partol)
    SwipeMenuListView rvLeavePartol;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    //显示隐藏界面
    @BindView(R.id.ll_context)
    LinearLayout ll_context;

    @BindView(R.id.tv_ten)
    TextView tv_ten;


    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;

    @BindView(R.id.new_tv_layout_image)
    ImageView newTvLayoutImage;
    @BindView(R.id.new_tv_layout_contont1)
    TextView newTvLayoutContont1;
    @BindView(R.id.new_tv_layout_contont2)
    TextView newTvLayoutContont2;
    @BindView(R.id.rl_2)
    RelativeLayout rl2;
    @BindView(R.id.tv_guild_line)
    TextView tvGuildLine;


    private ProgressDialogFragment mProgressDialog;
    private CompanyOneAdapter mAdapter;
    private List<companyOrgBean.GuildBean> mPatrolList = new ArrayList<>();

    List<LeaveBean> mList = new ArrayList<>();
    public Boolean IsManager = false;
    public Boolean IsLeave = false;
    //公司的id
    public String id = "";

    //   0是跳转公司界面  1 会长创建公司  2管理员绑定公会 3会长绑定公会 4主播绑定公会
    public static int type = 0;
    //弹窗
    AlertDialog dialog;
    //公司信息
    companyOrgBean.CompanyBean company;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_companyinfo_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_companyinfo_layout;
    }

    @Override
    protected CreatPresenter createPresenter() {
        return new CreatPresenter(this, this);
    }


    @Override
    protected void initViewsAndEvents() {
        CompanyOneAdapter.dataIsOpen.clear();
        CompanyInterfaceUtils.setCallBack(this);
        rv_partol.setLayoutManager(new FastLinearScrollManger(CompamyInfoActivity.this));
        mAdapter = new CompanyOneAdapter(this, R.layout.new_item_my_anchor, mPatrolList, CompanyInterfaceUtils.companyFace);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        rv_partol.setAdapter(mAdapter);
        ivRight.setVisibility(View.GONE);
        if (UserConfig.getInstance().getRole().equals("1")) {
            rl2.setVisibility(View.GONE);
            tvGuildLine.setVisibility(View.GONE);
            rvManagerPartol.setVisibility(View.VISIBLE);
            rvLeavePartol.setVisibility(View.VISIBLE);
            llLeave.setVisibility(View.VISIBLE);
            llManager.setVisibility(View.VISIBLE);
            tv_ten.setVisibility(View.VISIBLE);
            ivRight.setVisibility(View.VISIBLE);
        } else if (UserConfig.getInstance().getRole().equals("2")) {
            rl2.setVisibility(View.VISIBLE);
            tvGuildLine.setVisibility(View.VISIBLE);
            rvManagerPartol.setVisibility(View.GONE);
            rvLeavePartol.setVisibility(View.GONE);
            llLeave.setVisibility(View.GONE);
            llManager.setVisibility(View.GONE);
            tv_ten.setVisibility(View.GONE);
        } else {
            rl2.setVisibility(View.VISIBLE);
            tvGuildLine.setVisibility(View.VISIBLE);
            rvManagerPartol.setVisibility(View.GONE);
            rvLeavePartol.setVisibility(View.GONE);
            llLeave.setVisibility(View.GONE);
            llManager.setVisibility(View.GONE);
            tv_ten.setVisibility(View.GONE);
        }
        rvLeavePartol.setVisibility(View.GONE);
        rvManagerPartol.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!NetWorkUtil.isNetworkConnected(this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(this.getSupportFragmentManager());
            if (UserConfig.getInstance().getRole().equals("1")) {
                mPresenter.companyOrg();
                mPresenter.companyAdmins();
                mPresenter.companyBreaklist();
            } else if (UserConfig.getInstance().getRole().equals("2")) {
                mPresenter.companyOrg();
            } else {
                mPresenter.companyOrg();
            }
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }


    @Override
    public void Create() {
        Log.d("Debug", "添加成功");
    }

    @Override
    public void update() {

    }

    @Override
    public void company(companyOrgBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (mAdapter != null) {
            mAdapter.getAll().clear();
        }
        company = resultBean.getCompany();
        //公司布局绘制
        initCompany(resultBean.getCompany());
        if (resultBean.getGuild().size() > 0) {
            if (CompanyOneAdapter.dataIsOpen.size() == 0) {
                for (int i = 0; i < resultBean.getGuild().size(); i++) {
                    justBean justBean = new justBean(false);
                    CompanyOneAdapter.dataIsOpen.add(justBean);
                }
            }
            mAdapter.addAll(resultBean.getGuild());
            mAdapter.notifyDataSetChanged();
            mPatrolList = mAdapter.getAll();
            llInfo.setVisibility(View.VISIBLE);
            llEmpty.setVisibility(View.GONE);
        } else {
            if (UserConfig.getInstance().getRole().equals("1")) {
                newTvLayoutContont1.setText("您还没有公会呢，赶紧加入一个吧！");
                newTvLayoutContont2.setText("加入公会");
            } else {
                newTvLayoutContont1.setText("您还没有公会呢，赶紧绑定一个吧！");
                newTvLayoutContont2.setText("绑定公会");
            }
            //绑定公会操作
            llInfo.setVisibility(View.GONE);
            llEmpty.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void adminsList(companyAdminListBean resultBean) {
//        Log.d("Debug", "请求管理员请求成功");\
        tvManagerNumber.setText(resultBean.getAdmin().size() + " 人");
        if (resultBean.getAdmin().size() > 0) {
            CommonAdapter<companyAdminListBean.AdminBean> managermAdapter = new CommonAdapter<companyAdminListBean.AdminBean>(CompamyInfoActivity.this, resultBean.getAdmin(), R.layout.company_all_manager_item) {
                @Override
                public void convert(ViewHolder helper, final companyAdminListBean.AdminBean item) {
                    final ImageView im_photo = helper.getView(R.id.im_photo);
                    final TextView tv_name = helper.getView(R.id.tv_name);
                    Glide.with(mContext)
                            .load(item.getAvatar_url())
                            .placeholder(R.mipmap.bg_touxiang)
                            .crossFade()
                            .into(im_photo);
                    tv_name.setText(item.getNickname() + "");

                    SwipeMenuCreator creator1 = new SwipeMenuCreator() {

                        @Override
                        public void create(SwipeMenu menu) {
                            //创建一个"打开"功能菜单
                            SwipeMenuItem openItem = new SwipeMenuItem(CompamyInfoActivity.this);
                            // mContext
                            openItem.setBackground(new ColorDrawable(Color.rgb(221, 186, 105)));
//                            openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                            // 宽度：菜单的宽度是一定要有的，否则不会显示
                            openItem.setWidth(160);
                            // 菜单标题
                            openItem.setTitle("离职");
                            // 标题文字大小
                            openItem.setTitleSize(16);
                            // 标题的颜色
                            openItem.setTitleColor(Color.WHITE);
                            // 添加到menu
                            menu.addMenuItem(openItem);
                        }
                    };
                    rvManagerPartol.setMenuCreator(creator1);
                    rvManagerPartol.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                        @Override
                        public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                            switch (index) {
                                case 0:
                                    symbol = 1;
                                    homeDialog1(item.getAdmin_reg_id(), "");
//                                    ToastUtil.showShort("管理员离职操作");
                                    break;
                            }
                        }
                    });
                }
            };
            rvManagerPartol.setAdapter(managermAdapter);
        }

    }

    @Override
    public void breaklist(companyBreaklistListBean resultBean) {

        if (mList.size() > 0) {
            mList.clear();
        }
        tvLeaveNumber.setText(resultBean.getAdmins().size() + resultBean.getAnchors().size() + " 人");
        if (resultBean.getAdmins().size() > 0) {
            for (int i = 0; i < resultBean.getAdmins().size(); i++) {
                companyBreaklistListBean.AdminsBean adminsBean = resultBean.getAdmins().get(i);
                LeaveBean bean = new LeaveBean(adminsBean.getId(), adminsBean.getAdmin_reg_id() + "", adminsBean.getNickname(), adminsBean.getAvatar_url(), true);
                mList.add(bean);
            }
        }
        if (resultBean.getAnchors().size() > 0) {
            for (int i = 0; i < resultBean.getAnchors().size(); i++) {
                companyBreaklistListBean.AnchorsBean anchorsBean = resultBean.getAnchors().get(i);
                LeaveBean bean = new LeaveBean(anchorsBean.getId(), anchorsBean.getUid() + "", anchorsBean.getAnchor_name(), anchorsBean.getAvatar_url(), false);
                mList.add(bean);
            }
        }
        CommonAdapter<LeaveBean> leavemAdapter = new CommonAdapter<LeaveBean>(CompamyInfoActivity.this, mList, R.layout.company_lizhi_item) {
            @Override
            public void convert(ViewHolder helper, LeaveBean item) {
                final ImageView im_photo = helper.getView(R.id.im_photo);
                final TextView tv_name = helper.getView(R.id.tv_name);
                final TextView tv_manager_name = helper.getView(R.id.tv_manager_name);
                final TextView tv_id = helper.getView(R.id.tv_id);
                final TextView tv_name_type = helper.getView(R.id.tv_name_type);


                Glide.with(mContext)
                        .load(item.getAvatar_url())
                        .placeholder(R.mipmap.bg_touxiang)
                        .crossFade()
                        .into(im_photo);
                if (item.isFlag()) {
                    tv_name.setVisibility(View.GONE);
                    tv_id.setVisibility(View.GONE);
                    tv_manager_name.setVisibility(View.VISIBLE);
                    tv_manager_name.setText(item.getName());
                    tv_name_type.setText("管理员");
                } else {
                    tv_name.setVisibility(View.VISIBLE);
                    tv_id.setVisibility(View.VISIBLE);
                    tv_manager_name.setVisibility(View.GONE);
                    tv_name.setText(item.getName());
                    tv_id.setText("ID: " + item.getUid());
                    tv_name_type.setText("主播");
                }

            }
        };
        rvLeavePartol.setAdapter(leavemAdapter);
    }


    private void initCompany(companyOrgBean.CompanyBean bean) {

        Glide.with(CompamyInfoActivity.this)
                .load(bean.getLogo())
                .placeholder(R.mipmap.bg_touxiang)
                .crossFade()
                .into(imPhoto);
        Log.d("Debug", "公司的姓名为" + tvName);
        tvName.setText(bean.getCompany_name() + "");
        tvNumber.setText(bean.getCompany_scale() + "");
        tvAdress.setText(bean.getLocation() + "");
        id = bean.getId();

    }

    @OnClick({R.id.rl_1, R.id.rl_2, R.id.rl_3, R.id.im_sousuo, R.id.reload, R.id.setting_back, R.id.iv_right, R.id.new_tv_layout_contont2, R.id.ll_manager, R.id.ll_leave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
                if (PointUtils.isFastClick()) {
                    if (!NetWorkUtil.isNetworkConnected(this)) {
                        rl_net_info.setVisibility(View.GONE);
                        layout_network_break.setVisibility(View.VISIBLE);
                        ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    } else {
                        rl_net_info.setVisibility(View.VISIBLE);
                        layout_network_break.setVisibility(View.GONE);
                        if (mProgressDialog == null) {
                            mProgressDialog = new ProgressDialogFragment();
                        }
                        mProgressDialog.show(this.getSupportFragmentManager());
                        if (UserConfig.getInstance().getRole().equals("1")) {
                            mPresenter.companyOrg();
                            mPresenter.companyAdmins();
                            mPresenter.companyBreaklist();
                        } else if (UserConfig.getInstance().getRole().equals("2")) {
                            mPresenter.companyOrg();
                        } else {
                            mPresenter.companyOrg();
                        }
                    }
                }
                break;
            case R.id.setting_back:
                finish();
                break;
            case R.id.iv_right:
                if (PointUtils.isFastClick()) {
                    Intent intent1 = new Intent(CompamyInfoActivity.this, UpdateCityActivity.class);
                    intent1.putExtra("bean", company);
                    startActivity(intent1);
                }
//                startActivity(new Intent(CompamyInfoActivity.this, CompanySerchmanActivity.class));
                break;
            case R.id.im_sousuo:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(CompamyInfoActivity.this, CompanySerchmanActivity.class));
                }
                break;
            case R.id.new_tv_layout_contont2:
                if (PointUtils.isFastClick()) {
                    if (UserConfig.getInstance().getRole().equals("1")) {
                        //会长绑定公会
                        ActivityUtils.startActivity(this, BindingGuildeTypectivity.class);
                    } else if (UserConfig.getInstance().getRole().equals("2")) {
                        //主播绑定公会
                        ActivityUtils.startActivity(this, BindingAnchorGuildeTypectivity.class);
                    } else {
                        //管理员绑定公会
                        ActivityUtils.startActivity(this, BindingManagerTypectivity.class);

                    }
                }
                break;

            case R.id.rl_1:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(CompamyInfoActivity.this, MyCompanyTelephoneActivity.class).putExtra("company_id", id));
                }
                break;
            case R.id.rl_2:
                if (PointUtils.isFastClick()) {
                    Intent intent = new Intent(CompamyInfoActivity.this, CallingCardActivity.class);
                    intent.putExtra("flag", true);
                    intent.putExtra("register_id", "");
                    startActivity(intent);
                }
                break;
            case R.id.rl_3:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(CompamyInfoActivity.this, CallingCardActivity.class));
                }
                break;
            case R.id.ll_manager:
                if (PointUtils.isFastClick()) {
                    if (!IsManager) {
                        rvManagerPartol.setVisibility(View.VISIBLE);
                        imManagerUp.setImageDrawable(getResources().getDrawable(R.mipmap.my_sanjiao_down));
                        IsManager = true;
                    } else {
                        rvManagerPartol.setVisibility(View.GONE);
                        imManagerUp.setImageDrawable(getResources().getDrawable(R.mipmap.my_sanjiao_xiangyou));
                        IsManager = false;
                    }
                }
                break;
            case R.id.ll_leave:
                if (PointUtils.isFastClick()) {
                    if (!IsLeave) {
                        rvLeavePartol.setVisibility(View.VISIBLE);
                        imLeaveUp.setImageDrawable(getResources().getDrawable(R.mipmap.my_sanjiao_down));
                        IsLeave = true;
                    } else {
                        rvLeavePartol.setVisibility(View.GONE);
                        imLeaveUp.setImageDrawable(getResources().getDrawable(R.mipmap.my_sanjiao_xiangyou));
                        IsLeave = false;
                    }
                }
                break;
        }
    }


    /**
     * 适配器里面的操作
     *
     * @param guild_id
     * @param admin_reg_id
     */
//     0 公会解绑管理员 1 公会离职管理员 2 //公会离职主播   3//管理员移除主播 4  //管理员增加主播
    int symbol;

    //公会解绑管理员
    @Override
    public void adminRelieve(String guild_id, String admin_reg_id) {
        symbol = 0;
        Log.d("Debug", "到达公会解绑事件");
        homeDialog1(guild_id, admin_reg_id);
    }

    //公会离职管理员
    @Override
    public void adminLeave(String admin_reg_id) {
        symbol = 1;

        homeDialog1(admin_reg_id, "");
    }

    //公会离职主播
    @Override
    public void companyBreakanchor(String guild_id, String admin_reg_id) {
        symbol = 2;

        homeDialog1(guild_id, admin_reg_id);
    }

    //管理员移除主播
    @Override
    public void adminBreakanchorFace(String guild_id, String anchor_string) {
        symbol = 3;
        homeDialog1(guild_id, anchor_string);

    }

    //管理员增加主播
    @Override
    public void adminAddanchorFace(String guild_id, String anchor_string) {
        symbol = 4;
        homeDialog1(guild_id, anchor_string);
    }


    @Override
    public void AnchorLeave() {
        Log.d("Debug", "离职成功");
        mPresenter.companyBreaklist();
        mPresenter.companyOrg();
        dialog.dismiss();
    }

    @Override
    public void companyLizhi() {
        mPresenter.companyOrg();
        mPresenter.companyBreaklist();
        mPresenter.companyAdmins();
        dialog.dismiss();
        DismissProgress();
    }

    @Override
    public void companyJiebang() {
        mPresenter.companyOrg();
        dialog.dismiss();
        DismissProgress();
    }

    //请求回调方法
    @Override
    public void adminBreakanchor() {
        mPresenter.companyOrg();
        dialog.dismiss();
        DismissProgress();
    }

    @Override
    public void adminAddanchors() {
        mPresenter.companyOrg();
        dialog.dismiss();
        DismissProgress();
    }


    public AlertDialog homeDialog1(final String s, final String s1) {
        View view = LayoutInflater.from(CompamyInfoActivity.this).inflate(R.layout.tishi_dialog_layout, null);
        final TextView tv_text = view.findViewById(R.id.tv_text);

        TextView tv_commit = view.findViewById(R.id.tv_commit);
        TextView tv_cancle = view.findViewById(R.id.tv_cancle);

        switch (symbol) {
            case 0:
                tv_text.setText("您确认将此管理员和该公会进行解绑么？解绑后此管理员将被移出该公会。可在“我的管理员”列表找到。");
                break;
            case 1:
                tv_text.setText("您确认对此管理员进行离职操作么？离职操作后此管理员将被移出该公司。");
                break;
            case 2:
                tv_text.setText("您确认对此主播进行离职操作么？离职操作后此主播将被移出该公司。");
                break;
            case 3:
                tv_text.setText("您确认移除此主播么？移除后次主播将不在是您的主播。");
                break;
            case 4:
                tv_text.setText("您确认添加此主播为您的主播么？添加后将消息通知给您的会长。");
                break;
        }


        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (symbol) {
                    case 0:
                        showProgress();
                        mPresenter.companyJiebang(s, s1);
                        break;
                    case 1:
                        showProgress();
                        mPresenter.companyLizhi(s);
                        break;
                    case 2:
                        showProgress();
                        mPresenter.companyBreakanchor(s, s1);
                        break;
                    case 3:
                        showProgress();
                        mPresenter.adminBreakanchor(s, s1);
                        break;
                    case 4:
                        showProgress();
                        mPresenter.adminAddanchor(s, s1);
                        break;
                }

            }
        });

        //点击事件
        dialog = new AlertDialog.Builder(CompamyInfoActivity.this).create();
        dialog.show();
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Window window = dialog.getWindow();
        assert window != null;
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
        window.setLayout(dip2px(CompamyInfoActivity.this, 330), -1);
        dialog.setCanceledOnTouchOutside(false);
        //使弹出输入法
        InputMethodManager im2 = (InputMethodManager) CompamyInfoActivity.this
                .getSystemService(Context.INPUT_METHOD_SERVICE);
//        im2.showSoftInput(mInput, InputMethodManager.SHOW_FORCED);
        dialog.getWindow()
                .clearFlags(
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return dialog;
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue dp尺寸
     * @return
     */
    @SuppressWarnings("JavaDoc")
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    /**
     * 加载动画显示
     */
    public void showProgress() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(CompamyInfoActivity.this.getSupportFragmentManager());
    }

    public void DismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }


}
