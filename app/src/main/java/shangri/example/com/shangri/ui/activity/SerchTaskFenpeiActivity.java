package shangri.example.com.shangri.ui.activity;


import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.CommonAdapter;
import shangri.example.com.shangri.adapter.ViewHolder;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.AnchorSerchBean;
import shangri.example.com.shangri.model.bean.response.taskSelectListBean;
import shangri.example.com.shangri.presenter.SelectManPresenter;
import shangri.example.com.shangri.presenter.view.SelectmanView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.ui.view.ListViewForScrollView;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.KeyBoardUtil;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 分配任务 选择界面
 * Created by admin on 2017/12/22.
 */

public class SerchTaskFenpeiActivity extends BaseActivity<SelectmanView, SelectManPresenter> implements SelectmanView {
    @BindView(R.id.rv_partol)
    ListViewForScrollView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.search_content)
    EditText mSearchText;
    @BindView(R.id.search_delete)
    ImageView mDeleteImage;
    @BindView(R.id.search_cancel)
    TextView mCancelText;
    @BindView(R.id.search_not_found)
    LinearLayout mSearchNotFound;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;


    @BindView(R.id.search_no)
    LinearLayout search_no;


    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;

    private ProgressDialogFragment mProgressDialog;
    private int currPage = 1;

    private List<taskSelectListBean.PersonsBean> mList = new ArrayList<>();
    private CommonAdapter<taskSelectListBean.PersonsBean> mAdapter;

    private boolean mIsCancel = true; //是否取消, 默认可以取消
    private String mSearchContent = "21"; //搜索内容

    String guildid;    //    任务ID
    String type;
    Map map = new HashMap();

    public static <T> String mapToJson(Map<String, T> map) {
        Gson gson = new Gson();
        return gson.toJson(map);

    }

    String start_time = "";
    String end_time = "";

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_serch_fenpei_list;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_serch_fenpei_list;
    }

    @Override
    protected SelectManPresenter createPresenter() {
        return new SelectManPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        //不允许左滑返回
        mSwipeBackLayout.setEnableGesture(false);
        guildid = getIntent().getStringExtra("guild_id");
        type = getIntent().getStringExtra("type");
        start_time = getIntent().getStringExtra("start_time");
        end_time = getIntent().getStringExtra("end_time");
        initSpringView();
        loadData();
        mSearchText.setFocusable(true);
        KeyBoardUtil.KeyBoard(SerchTaskFenpeiActivity.this, "open");

        mSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String content = editable.toString();
                if (TextUtils.isEmpty(content)) {
                    mDeleteImage.setVisibility(View.GONE);
                    mIsCancel = true;
                    mCancelText.setText(getResources().getString(R.string.cancel));
                } else {
                    mDeleteImage.setVisibility(View.VISIBLE);
                    mIsCancel = false;
                    mCancelText.setText(getResources().getString(R.string.search));
                }
            }
        });

        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mSearchContent = mSearchText.getText().toString();
                    if (TextUtils.isEmpty(mSearchContent)) {
                        ToastUtil.showShort("请输入内容后搜索");
                        return false;
                    }
                    KeyBoardUtil.hide_keyboard_from(SerchTaskFenpeiActivity.this, mSearchText);
                    searchBefore();
                    if (!NetWorkUtil.isNetworkConnected(SerchTaskFenpeiActivity.this)) {
                        rl_net_info.setVisibility(View.GONE);
                        layout_network_break.setVisibility(View.VISIBLE);
                        ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    } else {
                        mProgressDialog.show(SerchTaskFenpeiActivity.this.getSupportFragmentManager());
                        rl_net_info.setVisibility(View.VISIBLE);
                        layout_network_break.setVisibility(View.GONE);
                        mPresenter.taskSelectanchor(guildid, mSearchContent, "", type, start_time, end_time);
                    }

                    return true;
                }
                return false;
            }
        });

    }

    //点击搜索前要做的事
    private void searchBefore() {
        currPage = 1;
        mAdapter.notifyDataSetChanged();
        rv_partol.setVisibility(View.VISIBLE);
        mSearchNotFound.setVisibility(View.INVISIBLE);
    }


    @Override
    public void requestFailed(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
    }


    public void loadData() {
        mAdapter = new CommonAdapter<taskSelectListBean.PersonsBean>(SerchTaskFenpeiActivity.this, mList, R.layout.company_fenpei_number) {
            @Override
            public void convert(final ViewHolder helper, final taskSelectListBean.PersonsBean item) {
                helper.setText(R.id.tv_name, item.getNickname());
                CircleImageView im_photo = helper.getView(R.id.im_photo);
                Glide.with(mContext)
                        .load(item.getAvatar_url() + "")
                        .placeholder(R.mipmap.bg_touxiang)
                        .crossFade()
                        .into(im_photo);
                helper.setText(R.id.tv_task_number, item.getPre_target_value() + "");
                helper.setText(R.id.tv_finish_number, item.getPre_reality_value() + "");
                helper.setText(R.id.tv_task_percen, item.getPercent() + "%");
                if (item.getValue() == 0) {
                    helper.setText(R.id.et_number, "");
                } else {
                    helper.setText(R.id.et_number, item.getValue() + "");
                }
                final TextView et_number = helper.getView(R.id.et_number);
                TextView tv4 = helper.getView(R.id.tv4);

                LinearLayout ll_number = helper.getView(R.id.ll_number);
                if (item.getStatus() == 1) {
                    et_number.setText("不可操作");
                    et_number.setEnabled(false);
                    et_number.setTextColor(getResources().getColor(R.color.text_color_light_black));
                    tv4.setTextColor(getResources().getColor(R.color.text_color_light_black));
                    ll_number.setBackground(getResources().getDrawable(R.drawable.color_rejust_shape5));
                } else {
                    et_number.setEnabled(true);
                    if (item.getValue() == 0) {
                        et_number.setText("分配数量");
                    } else {
                        et_number.setText(item.getValue() + "");
                    }

                    et_number.setTextColor(getResources().getColor(R.color.text_color_task));
                    tv4.setTextColor(getResources().getColor(R.color.text_color_task));
                    ll_number.setBackground(getResources().getDrawable(R.drawable.color_rejust_shape4));
                }

                et_number.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        homeDialog1(item.getTag());
                    }
                });

            }
        };
        rv_partol.setAdapter(mAdapter);
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
                if (!NetWorkUtil.isNetworkConnected(SerchTaskFenpeiActivity.this)) {
                    rl_net_info.setVisibility(View.GONE);
                    layout_network_break.setVisibility(View.VISIBLE);
                    ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    rl_net_info.setVisibility(View.VISIBLE);
                    layout_network_break.setVisibility(View.GONE);
                    currPage = 1;
                    mProgressDialog.show(SerchTaskFenpeiActivity.this.getSupportFragmentManager());
                    mPresenter.taskSelectanchor(guildid, mSearchContent, "", type, start_time, end_time);
                }
            }

            @Override
            public void onLoadmore() {
                if (!NetWorkUtil.isNetworkConnected(SerchTaskFenpeiActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_LOAD_MORE;
//                    requestTastList();
                }
                sv_partol.onFinishFreshAndLoad();

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

    @OnClick({R.id.search_delete, R.id.search_cancel, R.id.reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
                searchBefore();
                mSearchContent = mSearchText.getText().toString();
                if (!NetWorkUtil.isNetworkConnected(SerchTaskFenpeiActivity.this)) {
                    rl_net_info.setVisibility(View.GONE);
                    layout_network_break.setVisibility(View.VISIBLE);
                    ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                } else {
                    mProgressDialog.show(this.getSupportFragmentManager());
                    rl_net_info.setVisibility(View.VISIBLE);
                    layout_network_break.setVisibility(View.GONE);
                    mPresenter.taskSelectanchor(guildid, mSearchContent, "", type, start_time, end_time);
                }
                break;
            case R.id.search_cancel:
                if (mIsCancel) { //取消
                    finish();
                } else {  //搜索
                    searchBefore();
                    mSearchContent = mSearchText.getText().toString();
                    if (!NetWorkUtil.isNetworkConnected(SerchTaskFenpeiActivity.this)) {
                        rl_net_info.setVisibility(View.GONE);
                        layout_network_break.setVisibility(View.VISIBLE);
                        ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    } else {
                        mProgressDialog.show(this.getSupportFragmentManager());
                        rl_net_info.setVisibility(View.VISIBLE);
                        layout_network_break.setVisibility(View.GONE);
                        mPresenter.taskSelectanchor(guildid, mSearchContent, "", type, start_time, end_time);
                    }
                }
                break;
            case R.id.search_delete:
                mSearchText.setText("");
                break;
        }
    }


    //弹窗
    AlertDialog dialog;
    //分配的总目标
    int AddSumnumber = 0;
    //参加的人数
    int num = 0;

    public AlertDialog homeDialog1(final int osition) {
        View view = LayoutInflater.from(SerchTaskFenpeiActivity.this).inflate(R.layout.company_number_edite_layout, null);
        final TextView tv_ok = view.findViewById(R.id.tv_ok);
        final EditText input = view.findViewById(R.id.input);
        input.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        KeyBoardUtil.KeyBoard(SerchTaskFenpeiActivity.this, "open");
        //点击事件
        dialog = new AlertDialog.Builder(SerchTaskFenpeiActivity.this).create();
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList.get(osition).setValue(Integer.parseInt(input.getText().toString()));
                mList.get(osition).setFlag(true);
//                AddSumnumber = 0;
//                num = 0;
//                for (int i = 0; i < mList.size(); i++) {
//                    AddSumnumber = AddSumnumber + mList.get(i).getValue();
//                    if (mList.get(i).isFlag()) {
//                        num++;
//                    }
//                }
                mAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
        window.setLayout(dip2px(SerchTaskFenpeiActivity.this, 330), -1);
        dialog.setCanceledOnTouchOutside(false);
        //使弹出输入法
        InputMethodManager im2 = (InputMethodManager) SerchTaskFenpeiActivity.this
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
    public void taskSelectanchor(taskSelectListBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        search_no.setVisibility(View.GONE);
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        mList.clear();
        if (resultBean.getPersons().size() > 0) {
            sv_partol.setVisibility(View.VISIBLE);
            mSearchNotFound.setVisibility(View.GONE);
        } else {
            sv_partol.setVisibility(View.GONE);
            mSearchNotFound.setVisibility(View.VISIBLE);
        }
        for (int i = 0; i < resultBean.getPersons().size(); i++) {
            taskSelectListBean.PersonsBean personsBean = resultBean.getPersons().get(i);
            personsBean.setTag(i);
            mList.add(personsBean);
        }
        mAdapter.setmDatas(mList);
    }

    @Override
    public void anchorOplayer(AnchorSerchBean resultBean) {

    }

    @Override
    public void cacheAdd() {
        Log.d("Debug", "请求成功");
        finish();
    }

    @Override
    public void Add() {

    }

    //按系统返回键时出发
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            map.clear();
            for (int i = 0; i < mList.size(); i++) {
                map.put(mList.get(i).getRegister_id() + "", mList.get(i).getValue() + "");
            }

            String jsons1 = mapToJson(map);
            Log.d("Debug", "转化成的JSON为" + jsons1);
            if (map.size() == 0) {
                finish();
            } else {
                mPresenter.cachAdd(jsons1);
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
