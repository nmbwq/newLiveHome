package shangri.example.com.shangri.ui.activity;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
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
import shangri.example.com.shangri.model.bean.event.InputWindowListener;
import shangri.example.com.shangri.model.bean.request.AddNewBean;
import shangri.example.com.shangri.model.bean.response.AnchorSerchBean;
import shangri.example.com.shangri.model.bean.response.TwoBean;
import shangri.example.com.shangri.model.bean.response.taskSelectListBean;
import shangri.example.com.shangri.presenter.SelectManPresenter;
import shangri.example.com.shangri.presenter.view.SelectmanView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.ui.view.IMMListenerRelativeLayout;
import shangri.example.com.shangri.ui.view.ListViewForScrollView;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.ActivityManager;
import shangri.example.com.shangri.util.KeyBoardUtil;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.SoftKeyBoardListener;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 选择执行人界面
 * Created by admin on 2017/12/22.
 */

public class SelectManActivity extends BaseActivity<SelectmanView, SelectManPresenter> implements SelectmanView {
    @BindView(R.id.rv_partol)
    ListViewForScrollView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.im_sousuo)
    ImageView imSousuo;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_manager)
    TextView tvManager;
    @BindView(R.id.tv_manager_line)
    TextView tvManagerLine;
    @BindView(R.id.rl_manager)
    RelativeLayout rlManager;
    @BindView(R.id.tv_anchor)
    TextView tvAnchor;
    @BindView(R.id.tv_anchor_line)
    TextView tvAnchorLine;
    @BindView(R.id.ll_anchor)
    RelativeLayout llAnchor;
    @BindView(R.id.ll_select)
    LinearLayout llSelect;
    @BindView(R.id.im_image)
    ImageView imImage;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.search_not_found)
    LinearLayout searchNotFound;
    @BindView(R.id.tv_sum_number)
    TextView tv_sum_number;
    @BindView(R.id.tv_sum_number1)
    TextView tv_sum_number1;
    @BindView(R.id.tv_type_name)
    TextView tv_type_name;


    @BindView(R.id.bottom_sum_number)
    TextView bottom_sum_number;
    @BindView(R.id.botom_people_number)
    TextView botom_people_number;

    @BindView(R.id.rl_top)
    IMMListenerRelativeLayout rl_top;


    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;

    private ProgressDialogFragment mProgressDialog;
    private int currPage = 1;

    private List<TwoBean> mdataList = new ArrayList<>();

    private List<taskSelectListBean.PersonsBean> mList = new ArrayList<>();
    private CommonAdapter<taskSelectListBean.PersonsBean> OtherAdapter;
    //    排序方式 默认0 0 按上期完成量倒排 1 按上期完成百分比倒排
    public int sort = 0;
    String guildid;
    String type;
    //总目标
    int Sumnumber = 0;
    //分配的总目标
    int AddSumnumber = 0;
    //参加的人数
    int num = 0;
    boolean flag = true;
    int position;
    boolean isBack = false;
    //点击搜索先存缓存
    boolean isSousuo = false;

    String theme = "";
    String content = "";
    String start_time = "";
    String end_time = "";
    String expect_aims = "";
    String hide_expect = "";
    String expire_remind = "";

    String task_id = "";


    //弹窗
    AlertDialog dialog;
    Map map = new HashMap();

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_select_man_list;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_select_man_list;
    }

    @Override
    protected SelectManPresenter createPresenter() {
        return new SelectManPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        guildid = getIntent().getStringExtra("guild_id");
        type = getIntent().getStringExtra("type");
        String number = getIntent().getStringExtra("number");
        theme = getIntent().getStringExtra("theme");
        content = getIntent().getStringExtra("content");
        start_time = getIntent().getStringExtra("start_time");
        end_time = getIntent().getStringExtra("end_time");
        expect_aims = getIntent().getStringExtra("expect_aims");
        hide_expect = getIntent().getStringExtra("hide_expect");
        expire_remind = getIntent().getStringExtra("expire_remind");
        Sumnumber = Integer.parseInt(number);
        task_id = getIntent().getStringExtra("task_id");
        Log.d("Debug", "选择界面task_id" + task_id);
        initSpringView();
        loadData();
        tv_sum_number.setText(Sumnumber + "");
        tv_sum_number1.setText(Sumnumber + "");
        SoftKeyBoardListener.setListener(SelectManActivity.this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
            }

            @Override
            public void keyBoardHide(int height) {

            }
        });

    }


    @Override
    public void requestFailed(String message) {

    }


    EditText et_number1;

    public void loadData() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(this.getSupportFragmentManager());
        currPage = 1;
        mPresenter.taskSelectanchor(guildid, "", sort + "", type, start_time, end_time);
        OtherAdapter = new CommonAdapter<taskSelectListBean.PersonsBean>(SelectManActivity.this, mList, R.layout.company_fenpei_number) {
            @Override
            public void convert(final ViewHolder helper, final taskSelectListBean.PersonsBean item) {
                helper.setText(R.id.tv_name, item.getNickname());
                CircleImageView im_photo = helper.getView(R.id.im_photo);
                Glide.with(mContext)
                        .load(item.getAvatar_url() + "")
                        .placeholder(R.mipmap.bg_touxiang)
                        .crossFade()
//                        .transform(new CornersTransform(SelectManActivity.this, 50))
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
//
            }
        };
        rv_partol.setAdapter(OtherAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        isBack = false;
        isSousuo = false;
        mPresenter.taskSelectanchor(guildid, "", sort + "", type, start_time, end_time);
    }

    private void initSpringView() {
        sv_partol.setGive(SpringView.Give.TOP);
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(this));
        sv_partol.setFooter(new SpringViewFooter(this));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                if (!NetWorkUtil.isNetworkConnected(SelectManActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_FRESH;
                    currPage = 1;
//                    mPresenter.taskSelectanchor(guildid, "", sort + "", type);
                }
                sv_partol.onFinishFreshAndLoad(); //停止加载

            }

            @Override
            public void onLoadmore() {
                if (!NetWorkUtil.isNetworkConnected(SelectManActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_LOAD_MORE;
//                    requestTastList();
                }
                sv_partol.onFinishFreshAndLoad(); //停止加载

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
        rl_top.setListener(new InputWindowListener() {
            @Override
            public void show() {
                Log.d("Debug", "软键盘弹起");
            }

            @Override
            public void hidden() {
                Log.d("Debug", "软键盘收藏");
            }
        });

    }

    public static <T> String mapToJson(Map<String, T> map) {
        Gson gson = new Gson();
        return gson.toJson(map);

    }


    @OnClick({R.id.im_sousuo, R.id.setting_back, R.id.rl_manager, R.id.ll_anchor, R.id.tv_fabu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                if (!NetWorkUtil.isNetworkConnected(SelectManActivity.this)) {
                   finish();
                }else {
                isBack = true;
                map.clear();
                for (int i = 0; i < mList.size(); i++) {
                    map.put(mList.get(i).getRegister_id() + "", mList.get(i).getValue() + "");
                }
                if (map.size() > 0) {
                    mPresenter.cachAdd(mapToJson(map));
                } else {
                    finish();
                }  }

                break;
            case R.id.im_sousuo:
                isSousuo = true;
                map.clear();
                for (int i = 0; i < mList.size(); i++) {
                    map.put(mList.get(i).getRegister_id() + "", mList.get(i).getValue() + "");
                }
                if (map.size() > 0) {
                    mPresenter.cachAdd(mapToJson(map));
                }
                break;
            case R.id.tv_fabu:
                map.clear();
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).getValue() != 0) {
                        map.put(mList.get(i).getRegister_id() + "", mList.get(i).getValue() + "");
                    }
                }
                AddNewBean addNewBean = new AddNewBean();
                addNewBean.setGuild_id(guildid);
                addNewBean.setTheme(theme);
                addNewBean.setContent(content);
                addNewBean.setStart_time(start_time);
                addNewBean.setEnd_time(end_time);
                addNewBean.setExpect_aims(Sumnumber + "");
                addNewBean.setHide_expect(hide_expect);
                addNewBean.setExpire_remind(expire_remind);
                addNewBean.setType(type);
                addNewBean.setAssigns(mapToJson(map));
                addNewBean.setTask_id(task_id);

                Log.d("Debug", "开始发请求");

                if (Integer.parseInt(botom_people_number.getText().toString()) > 0) {
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(this.getSupportFragmentManager());
                    mPresenter.taskIssue(addNewBean);
                } else {
                    ToastUtil.showShort("无分配人员，不能发布任务");
                }

                break;
            case R.id.rl_manager:
                sort = 0;
                map.clear();
                for (int i = 0; i < mList.size(); i++) {
                    map.put(mList.get(i).getRegister_id() + "", mList.get(i).getValue() + "");
                }
                String jsons1 = mapToJson(map);
                Log.d("Debug", "转化成的JSON为" + jsons1);
                if (map.size() > 0) {
                    mPresenter.cachAdd(jsons1);
                }

                break;
            case R.id.ll_anchor:
                sort = 1;
                map.clear();
                for (int i = 0; i < mList.size(); i++) {
                    map.put(mList.get(i).getRegister_id() + "", mList.get(i).getValue() + "");
                }
                if (map.size() > 0) {
                    mPresenter.cachAdd(mapToJson(map));
                }
                break;
        }
    }

    @Override
    public void taskSelectanchor(taskSelectListBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        mList.clear();
//        mList=resultBean.getPersons();
        if (resultBean.getPersons().size() > 0) {
            rv_partol.setVisibility(View.VISIBLE);
            AddSumnumber = 0;
            num = 0;
            for (int i = 0; i < resultBean.getPersons().size(); i++) {
                AddSumnumber = AddSumnumber + resultBean.getPersons().get(i).getValue();
                if (resultBean.getPersons().get(i).getValue() > 0) {
                    num++;
                }
            }
            bottom_sum_number.setText(AddSumnumber + "");
            botom_people_number.setText(num + "");
            if (AddSumnumber > Sumnumber) {
                tv_sum_number1.setText(AddSumnumber - Sumnumber + "");
                tv_type_name.setText("目标超额量");
            } else {
                tv_sum_number1.setText(Sumnumber - AddSumnumber + "");
                tv_type_name.setText("目标剩余量");
            }
        } else {
            rv_partol.setVisibility(View.GONE);
        }
        for (int i = 0; i < resultBean.getPersons().size(); i++) {
            taskSelectListBean.PersonsBean personsBean = resultBean.getPersons().get(i);
            personsBean.setTag(i);
            mList.add(personsBean);
        }
        OtherAdapter.setmDatas(mList);
    }

    @Override
    public void anchorOplayer(AnchorSerchBean resultBean) {

    }

    @Override
    public void cacheAdd() {
        if (isBack) {
            finish();
        } else {
            if (isSousuo) {
                Log.d("Debug", "点击搜索界面");
                Intent intent = new Intent(SelectManActivity.this, SerchTaskFenpeiActivity.class);
                intent.putExtra("guild_id", guildid);
                intent.putExtra("type", type);
                intent.putExtra("start_time", start_time);
                intent.putExtra("end_time", end_time);
                startActivity(intent);
            } else {
                if (sort == 1) {
                    tvManager.setTextColor(getResources().getColor(R.color.text_color_light_black));
                    tvManagerLine.setVisibility(View.GONE);
                    tvAnchor.setTextColor(getResources().getColor(R.color.text_color_task));
                    tvAnchorLine.setVisibility(View.VISIBLE);
                    mPresenter.taskSelectanchor(guildid, "", sort + "", type, start_time, end_time);

                } else {
                    tvManager.setTextColor(getResources().getColor(R.color.text_color_task));
                    tvManagerLine.setVisibility(View.VISIBLE);
                    tvAnchor.setTextColor(getResources().getColor(R.color.text_color_light_black));
                    tvAnchorLine.setVisibility(View.GONE);
                    mPresenter.taskSelectanchor(guildid, "", sort + "", type, start_time, end_time);

                }
            }
        }


    }

    @Override
    public void Add() {
        Log.d("Debug", "添加成功");
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        ActivityManager.getInstance().finishActivity(ActivityAddNewTask.class);
        finish();
    }


    public AlertDialog homeDialog1(final int osition) {
        View view = LayoutInflater.from(SelectManActivity.this).inflate(R.layout.company_number_edite_layout, null);
        final TextView tv_ok = view.findViewById(R.id.tv_ok);
        final EditText input = view.findViewById(R.id.input);
        input.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        KeyBoardUtil.KeyBoard(SelectManActivity.this, "open");
        //点击事件
        dialog = new AlertDialog.Builder(SelectManActivity.this).create();
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList.get(osition).setValue(Integer.parseInt(input.getText().toString()));
                mList.get(osition).setFlag(true);
                AddSumnumber = 0;
                num = 0;
                for (int i = 0; i < mList.size(); i++) {
                    AddSumnumber = AddSumnumber + mList.get(i).getValue();
                    if (mList.get(i).getValue() > 0) {
                        num++;
                    }
                }
                bottom_sum_number.setText(AddSumnumber + "");
                botom_people_number.setText(num + "");
                if (AddSumnumber > Sumnumber) {
                    tv_sum_number1.setText(AddSumnumber - Sumnumber + "");
                    tv_type_name.setText("目标超额量");
                } else {
                    tv_sum_number1.setText(Sumnumber - AddSumnumber + "");
                    tv_type_name.setText("目标剩余量");
                }

                OtherAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
        window.setLayout(dip2px(SelectManActivity.this, 330), -1);
        dialog.setCanceledOnTouchOutside(false);
        //使弹出输入法
        InputMethodManager im2 = (InputMethodManager) SelectManActivity.this
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


    @OnClick(R.id.setting_back)
    public void onViewClicked() {
    }

    //按系统返回键时出发
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!NetWorkUtil.isNetworkConnected(SelectManActivity.this)) {
                finish();
            }else {
            isBack = true;
            map.clear();
            for (int i = 0; i < mList.size(); i++) {
                map.put(mList.get(i).getRegister_id() + "", mList.get(i).getValue() + "");
            }
            if (map.size() > 0) {
                mPresenter.cachAdd(mapToJson(map));
            } else {
                finish();
            }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
