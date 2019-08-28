package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.ChoiceAnchorsBean;
import shangri.example.com.shangri.model.bean.response.NoticesResponseBean;
import shangri.example.com.shangri.model.bean.response.SortModel;
import shangri.example.com.shangri.model.bean.response.upgradeAlertBean;
import shangri.example.com.shangri.presenter.ChoiceAnchorsPresenter;
import shangri.example.com.shangri.presenter.view.ChoiceAnchorsView;
import shangri.example.com.shangri.ui.adapter.SortAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.SideBar;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.CharacterParser;
import shangri.example.com.shangri.util.KeyBoardUtil;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PinyinComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 选择主播
 * Created by admin on 2017/12/22.
 */

public class ChoiceAnchorsActivity extends BaseActivity<ChoiceAnchorsView, ChoiceAnchorsPresenter> implements ChoiceAnchorsView {
    @BindView(R.id.sv_partol)
    SpringView sv_partol;

    @BindView(R.id.country_lvcountry)
    ListView sortListView;

    @BindView(R.id.sidrbar)
    SideBar sideBar;

    @BindView(R.id.tv_guild_title)
    TextView tv_guild_title;

    @BindView(R.id.iv_add_guild)
    TextView iv_add_guild;

    @BindView(R.id.editText2)
    EditText editText2;

    private ProgressDialogFragment mProgressDialog;
    private SortAdapter adapter;
    private List<SortModel> SourceDateList; // 数据
    private CharacterParser characterParser;
    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;

    private int currPage = 1;
    private int mPageNum = 0; //总页数
    private String guildid;
    private String search;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_choice_anchors;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_choice_anchors;
    }

    @Override
    protected ChoiceAnchorsPresenter createPresenter() {
        return new ChoiceAnchorsPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        KeyBoardUtil.closeKeybord(editText2, ChoiceAnchorsActivity.this);

        if (getIntent().getStringExtra("guildid") != null) {
            guildid = getIntent().getStringExtra("guildid");
        }
        tv_guild_title.setText("选择主播");
        iv_add_guild.setVisibility(View.GONE);
        initSpringView();
        onRefreshData();
        SourceDateList = new ArrayList<>();
        adapter = new SortAdapter(this, SourceDateList, 2);
        sortListView.setAdapter(adapter);
        characterParser = CharacterParser.getInstance();
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position);
                }

            }
        });
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

//                Toast.makeText(ChoiceGuildActivity.this, SourceDateList.get(position).getName(), Toast.LENGTH_SHORT).show();
                TextView tvTitle = view
                        .findViewById(R.id.tv_user_item_name);

                tvTitle.setTextColor(ContextCompat.getColor(ChoiceAnchorsActivity.this, R.color.text_color_ok_orange));
                // 设置Intent回调 并设置回调内容
                Intent intent = new Intent();
                intent.putExtra("anchorsid", SourceDateList.get(position).getRegister_guild_id());
                intent.putExtra("anchorsname", SourceDateList.get(position).getName());
                setResult(4, intent);
                finish();
            }
        });
    }

    @OnClick({R.id.iv_add_guild, R.id.setting_back, R.id.tv_serch})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_guild:
                finish();
                break;
            case R.id.setting_back:
                finish();
                break;
            case R.id.tv_serch:
                search = editText2.getText().toString().trim();
//                if (TextUtils.isEmpty(search)){
//                    ToastUtil.showShort("搜索主播昵称/id");
//                    return;
//                }
                onRefreshData();
                break;


        }
    }

    @Override
    public void listAnchorsData(ChoiceAnchorsBean resultBean) {
        filledData(resultBean);// 填充数据

        PinyinComparator pinyinComparator = new PinyinComparator();
        Collections.sort(SourceDateList, pinyinComparator);
        adapter.addData(SourceDateList);
        Log.d("", "");

    }

    @Override
    public void noticePop(NoticesResponseBean resultBean) {

    }

    @Override
    public void myNoread(NoticesResponseBean resultBean) {

    }

    @Override
    public void invitationAlert(NoticesResponseBean resultBean) {

    }

    @Override
    public void upgradeAlert(upgradeAlertBean resultBean) {

    }

    @Override
    public void popGives(upgradeAlertBean resultBean) {

    }

    @Override
    public void givesGet() {

    }

    @Override
    public void oncilckBottom() {

    }

    private void initSpringView() {
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(this));
        sv_partol.setFooter(new SpringViewFooter(this));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                if (!NetWorkUtil.isNetworkConnected(ChoiceAnchorsActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_FRESH;
                    onRefreshData();
                }
            }

            @Override
            public void onLoadmore() {
                if (!NetWorkUtil.isNetworkConnected(ChoiceAnchorsActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_LOAD_MORE;
                    sv_partol.onFinishFreshAndLoad(); //停止加载
//                    requestPatolsList();
                }

            }
        });

    }

    private void requestPatolsList() {
        if (currPage < mPageNum) {
            currPage++;
            sv_partol.onFinishFreshAndLoad();
            mPresenter.getAnchorsList(guildid, String.valueOf(currPage), search);
        } else {
            sv_partol.onFinishFreshAndLoad(); //停止加载
        }
    }

    private void onRefreshData() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(this.getSupportFragmentManager());
        currPage = 1;
        mPresenter.getAnchorsList(guildid, String.valueOf(currPage), search);
    }

    /**
     * 填充数据
     *
     * @param date
     * @return
     */
    @SuppressWarnings("JavaDoc")
    private void filledData(ChoiceAnchorsBean date) {
        mPageNum = date.getTotal_page();

        List<SortModel> mSortList = new ArrayList<>();
        for (int i = 0; i < date.getAnchors().size(); i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date.getAnchors().get(i).getAnchor_name());
            sortModel.setRegister_guild_id(date.getAnchors().get(i).getRegister_guild_id());
            sortModel.setId(date.getAnchors().get(i).getUid());
            String name = date.getAnchors().get(i).getAnchor_name();
            String pinyin = characterParser.getSelling(name);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        if (currPage == 1) {
            SourceDateList.clear();
        }

        SourceDateList.addAll(mSortList);
    }

    @Override
    public void requestFailed(String message) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
