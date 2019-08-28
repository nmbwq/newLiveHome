package shangri.example.com.shangri.ui.activity;


import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import shangri.example.com.shangri.model.bean.response.ListPhoenBean;
import shangri.example.com.shangri.presenter.CompanyPhonePresenter;
import shangri.example.com.shangri.presenter.view.CompanyPhoneView;
import shangri.example.com.shangri.swipeMenuListView.SwipeMenu;
import shangri.example.com.shangri.swipeMenuListView.SwipeMenuCreator;
import shangri.example.com.shangri.swipeMenuListView.SwipeMenuItem;
import shangri.example.com.shangri.swipeMenuListView.SwipeMenuListView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 公司通讯录
 * Created by admin on 2017/12/22.
 */

public class MyCompanyTelephoneActivity extends BaseActivity<CompanyPhoneView, CompanyPhonePresenter> implements CompanyPhoneView {
    @BindView(R.id.rv_partol)
    SwipeMenuListView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.ll_add)
    RelativeLayout ll_add;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;

    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    private ProgressDialogFragment mProgressDialog;
    private int currPage = 1;
    private List<ListPhoenBean.ContactBean> mPatrolList = new ArrayList<>();


    private CommonAdapter<ListPhoenBean.ContactBean> LeavemAdapter;
    //公司id
    String company_id = "";
    //弹窗
    AlertDialog dialog;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_compantphone_list;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_compantphone_list;
    }

    @Override
    protected CompanyPhonePresenter createPresenter() {
        return new CompanyPhonePresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        initSpringView();
        if (UserConfig.getInstance().getRole().equals("1")) {
            ll_add.setVisibility(View.VISIBLE);
        } else {
            ll_add.setVisibility(View.GONE);
        }
        company_id = getIntent().getStringExtra("company_id");
        LeavemAdapter = new CommonAdapter<ListPhoenBean.ContactBean>(MyCompanyTelephoneActivity.this, null, R.layout.company_phonenumber_item) {
            @Override
            public void convert(ViewHolder helper, ListPhoenBean.ContactBean item) {
                helper.setText(R.id.tv_name, item.getName());
                helper.setText(R.id.tv_type, " ( " + item.getJob() + " ) ");
                helper.setText(R.id.tv_phone, item.getTelephone());
            }


        };

        SwipeMenuCreator creator1 = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                //创建一个"打开"功能菜单
                SwipeMenuItem openItem = new SwipeMenuItem(MyCompanyTelephoneActivity.this);
                // mContext
                openItem.setBackground(new ColorDrawable(Color.rgb(221, 186, 105)));
//                            openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                // 宽度：菜单的宽度是一定要有的，否则不会显示
                openItem.setWidth(160);
                // 菜单标题
                openItem.setTitle("删除");
                // 标题文字大小
                openItem.setTitleSize(16);
                // 标题的颜色
                openItem.setTitleColor(Color.WHITE);
                // 添加到menu
                menu.addMenuItem(openItem);
            }
        };
        if (UserConfig.getInstance().getRole().equals("1")) {
            rv_partol.setMenuCreator(creator1);
        }
        rv_partol.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        homeDialog2(LeavemAdapter.getItem(position).getId() + "");
//                        mPresenter.companyDelcontact(LeavemAdapter.getItem(position).getId() + "");
                        break;
                }
            }
        });
        rv_partol.setAdapter(LeavemAdapter);
        loadData();
    }


    @Override
    public void requestFailed(String message) {

    }


    public void loadData() {
        if (!NetWorkUtil.isNetworkConnected(MyCompanyTelephoneActivity.this)) {
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
            mPresenter.companyContacts(company_id);
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
                if (!NetWorkUtil.isNetworkConnected(MyCompanyTelephoneActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_LOAD_MORE;
//                    requestTastList();
                }

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
    public void companyContacts(ListPhoenBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }


        Log.d("Debug", "返回的数据长度为" + resultBean.getContact().size());
        if (currPage == 1) {
            mPatrolList.clear();
            if (resultBean.getContact().size() > 0) {
                rv_partol.setVisibility(View.VISIBLE);
            } else {
                rv_partol.setVisibility(View.GONE);
            }
        }
        LeavemAdapter.setmDatas(resultBean.getContact());
        mPatrolList = LeavemAdapter.getmDatas();
    }

    @Override
    public void companyAddContacts() {
        currPage = 1;
        mPresenter.companyContacts(company_id);
        dialog.dismiss();
    }

    @Override
    public void companyDelcontact() {
        DismissProgress();
        currPage = 1;
        mPresenter.companyContacts(company_id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.setting_back, R.id.ll_add, R.id.reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.reload:
                loadData();
                break;
            case R.id.ll_add:
                homeDialog1();
                break;
        }
    }


    public AlertDialog homeDialog1() {
        View view = LayoutInflater.from(MyCompanyTelephoneActivity.this).inflate(R.layout.phone_dialog_layout, null);
        final EditText et1 = view.findViewById(R.id.et1);
        final EditText et2 = view.findViewById(R.id.et2);
        final EditText et3 = view.findViewById(R.id.et3);

        TextView tv_commit = view.findViewById(R.id.tv_commit);
        TextView tv_cancle = view.findViewById(R.id.tv_cancle);
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et1.getText().toString().length() == 0) {
                    ToastUtil.showShort("职位不能为空");
                    return;
                }
                if (et2.getText().toString().length() == 0) {
                    ToastUtil.showShort("姓名不能为空");
                    return;
                }
                if (et3.getText().toString().length() == 0) {
                    ToastUtil.showShort("联系方式不能为空");
                    return;
                }
                mPresenter.companyAddcontact(et1.getText().toString(), et2.getText().toString(), et3.getText().toString(), company_id);
            }
        });

        //点击事件
        dialog = new AlertDialog.Builder(MyCompanyTelephoneActivity.this).create();
        dialog.show();

        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
        window.setLayout(dip2px(MyCompanyTelephoneActivity.this, 330), -1);
        dialog.setCanceledOnTouchOutside(false);
        //使弹出输入法
        InputMethodManager im2 = (InputMethodManager) MyCompanyTelephoneActivity.this
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


    public AlertDialog homeDialog2(final String s) {
        View view = LayoutInflater.from(MyCompanyTelephoneActivity.this).inflate(R.layout.tishi_dialog_layout, null);
        final TextView tv_text = view.findViewById(R.id.tv_text);

        TextView tv_commit = view.findViewById(R.id.tv_commit);
        TextView tv_cancle = view.findViewById(R.id.tv_cancle);
        tv_text.setText("您确认删除此员工么？删除后此员工信息将不再展示。");

        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress();
                mPresenter.companyDelcontact(s);
            }
        });

        //点击事件
        dialog = new AlertDialog.Builder(MyCompanyTelephoneActivity.this).create();
        dialog.show();
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
        window.setLayout(dip2px(MyCompanyTelephoneActivity.this, 330), -1);
        dialog.setCanceledOnTouchOutside(false);
        //使弹出输入法
        InputMethodManager im2 = (InputMethodManager) MyCompanyTelephoneActivity.this
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
     * 加载动画显示
     */
    public void showProgress() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(MyCompanyTelephoneActivity.this.getSupportFragmentManager());
    }

    public void DismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }


}
