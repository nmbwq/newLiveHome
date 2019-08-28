package shangri.example.com.shangri.ui.activity;


import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.AllListBean;
import shangri.example.com.shangri.model.bean.response.LookMeCompanyBean;
import shangri.example.com.shangri.model.bean.response.ManagerChectBean;
import shangri.example.com.shangri.model.bean.response.anchorChectBean;
import shangri.example.com.shangri.presenter.anchorChectPresenter;
import shangri.example.com.shangri.presenter.view.anchorChectView;
import shangri.example.com.shangri.ui.adapter.ManagerChectAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.AndroidInterface.AnchorChectFace;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 主播审核列表
 * Created by admin on 2017/12/22.
 */

public class ManagerChectListActivity extends BaseActivity<anchorChectView, anchorChectPresenter> implements anchorChectView, AnchorChectFace {
    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.activity_empty_linshi1)
    LinearLayout activity_empty_linshi;
    @BindView(R.id.image_type_empty)
    ImageView image_type_empty;
    @BindView(R.id.tv_text1_empty)
    TextView tv_text1_empty;
    @BindView(R.id.tv_text2_empty)
    TextView tv_text2_empty;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;


    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    private ProgressDialogFragment mProgressDialog;
    private int currPage = 1;

    private ManagerChectAdapter mAdapter;
    private List<ManagerChectBean.GuildAdminBean> mPatrolList = new ArrayList<>();


    //弹窗
    AlertDialog dialog;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_list1;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_list1;
    }

    @Override
    protected anchorChectPresenter createPresenter() {
        return new anchorChectPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        image_type_empty.setImageDrawable(getResources().getDrawable(R.mipmap.guanliyuan_kong));
        tv_text1_empty.setText("您还没有管理员呢");
        tv_text2_empty.setText("邀请管理员加入公会吧~");
        initSpringView();
        CompanyInterfaceUtils.setChectBack(this);
        title.setText("管理员申请");
        rv_partol.setLayoutManager(new FastLinearScrollManger(ManagerChectListActivity.this));
        mAdapter = new ManagerChectAdapter(this, R.layout.item_my_checkmanager, mPatrolList, CompanyInterfaceUtils.anchorChectFace);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        rv_partol.setAdapter(mAdapter);
        loadData();
    }


    @Override
    public void requestFailed(String message) {

    }


    public void loadData() {
        if (!NetWorkUtil.isNetworkConnected(ManagerChectListActivity.this)) {
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
            mPresenter.managerApplys();
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
                ACTION_TYPE = ACTION_FRESH;
                loadData();
            }

            @Override
            public void onLoadmore() {
                ACTION_TYPE = ACTION_LOAD_MORE;
//                    requestTastList();

            }
        });

    }

    private void requestTastList() {
        int mPageNum = 0;
        if (currPage < mPageNum) {
            currPage++;
            sv_partol.onFinishFreshAndLoad();
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

    @OnClick({R.id.setting_back, R.id.reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.reload:
                loadData();
                break;

        }
    }


    public AlertDialog homeDialog1(final String register_guild_id, final String guild_id) {
        View view = LayoutInflater.from(ManagerChectListActivity.this).inflate(R.layout.refuse_dialog_layout, null);


        TextView tv_commit = view.findViewById(R.id.tv_commit);
        final EditText et_message = view.findViewById(R.id.et_message);


        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.ManagerCheck(register_guild_id, guild_id, "-1", et_message.getText().toString());
                dialog.dismiss();
            }
        });

        //点击事件
        dialog = new AlertDialog.Builder(ManagerChectListActivity.this).create();
        dialog.show();


        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
        window.setLayout(dip2px(ManagerChectListActivity.this, 330), -1);
        dialog.setCanceledOnTouchOutside(false);
        //使弹出输入法
        InputMethodManager im2 = (InputMethodManager) ManagerChectListActivity.this
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


    @Override
    public void anchorApplys(anchorChectBean resultBean) {

    }

    @Override
    public void managerApplys(ManagerChectBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        Log.d("Debug", "返回的数据长度为" + resultBean.getGuild_admin().size());
        if (currPage == 1) {
            mPatrolList.clear();
            if (resultBean.getGuild_admin().size() > 0) {
                rv_partol.setVisibility(View.VISIBLE);
                activity_empty_linshi.setVisibility(View.GONE);
            } else {
                rv_partol.setVisibility(View.GONE);
                activity_empty_linshi.setVisibility(View.VISIBLE);
            }
        }
        mAdapter.addAll(resultBean.getGuild_admin());


    }

    @Override
    public void viewCompany(LookMeCompanyBean resultBean) {

    }


    @Override
    public void anchorCheck() {

    }

    @Override
    public void managerCheck() {
        currPage = 1;
        mPresenter.managerApplys();
    }

    @Override
    public void anchorCheckFace(String register_guild_id, String check_status, String check_mark) {

    }

    @Override
    public void adminPass(String admin_reg_id, String guild_id, String status, String mask) {
        if (status.equals("1")) {
            Log.d("Debug", "管理员做审核通过操作");
            mPresenter.ManagerCheck(admin_reg_id, guild_id, "1", "");
        } else {
            homeDialog1(admin_reg_id, guild_id);
            Log.d("Debug", "管理员拒绝操作");
        }
    }

    @Override
    public void robhim(AllListBean.ListBean bean) {


    }


}
