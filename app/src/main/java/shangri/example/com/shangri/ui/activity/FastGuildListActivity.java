package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.SelectGuildBean;
import shangri.example.com.shangri.model.bean.response.SortModel;
import shangri.example.com.shangri.model.bean.response.SortModelBean;
import shangri.example.com.shangri.presenter.GuildListPresenter;
import shangri.example.com.shangri.presenter.view.GuildListView;
import shangri.example.com.shangri.ui.adapter.SortAdapter;
import shangri.example.com.shangri.ui.view.SideBar;
import shangri.example.com.shangri.util.CharacterParser;
import shangri.example.com.shangri.util.PinyinComparator;

/**
 * mvp复制类
 * Created by admin on 2017/12/22.
 */

public class FastGuildListActivity extends BaseActivity<GuildListView, GuildListPresenter> implements GuildListView {
    @BindView(R.id.country_lvcountry)
    ListView sortListView;

    @BindView(R.id.sidrbar)
    SideBar sideBar;
    @BindView(R.id.tv_guild_title)
    TextView tv_guild_title;

    private PinyinComparator pinyinComparator;
    private SortAdapter adapter;
    private List<SortModel> SourceDateList; // 数据
    private CharacterParser characterParser;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_guild_list;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_guild_list;
    }

    @Override
    protected GuildListPresenter createPresenter() {
        return new GuildListPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        SourceDateList = new ArrayList<>();
        tv_guild_title.setText("选择平台");
        boolean isfromAnchorFast = getIntent().getBooleanExtra("isfromAnchorFast", false);

        if (UserConfig.getInstance().getRole().equals("1")) {
            adapter = new SortAdapter(this, SourceDateList, 1);
        } else {
            adapter = new SortAdapter(this, SourceDateList, 1);
        }
        boolean flag = getIntent().getBooleanExtra("flag", false);
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
                                    final int position, long id) {
                Log.d("Debug", "准备专递过去的is_paltfrom" + SourceDateList.get(position).getIs_paltfrom());
                Intent intent = new Intent();
                intent.putExtra("pingtainame", SourceDateList.get(position).getName());
                intent.putExtra("id", SourceDateList.get(position).getId());
                intent.putExtra("is_paltfrom", SourceDateList.get(position).getIs_paltfrom());
                intent.putExtra("table_flag", SourceDateList.get(position).getTable_flag());
                setResult(2, intent);
                finish();
                Toast.makeText(FastGuildListActivity.this, SourceDateList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        if (UserConfig.getInstance().getRole().equals("2")) {
            if (isfromAnchorFast) {
                //主播快速公会选择直播平台
                mPresenter.quickPlatfrom();
            } else {
                if (flag) {
                    mPresenter.selectGuild("all");
                } else {
                    mPresenter.selectGuild("");
                }
            }
        } else {
            mPresenter.quickPlatfrom();
        }

    }

    @OnClick({R.id.iv_add_guild, R.id.setting_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_guild:
                finish();
                break;
            case R.id.setting_back:
                finish();
                break;
        }
    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    public void listGuildData(SortModelBean resultBean) {
        filledData(resultBean);// 填充数据
        pinyinComparator = new PinyinComparator();
        Collections.sort(SourceDateList, pinyinComparator);
        adapter.addData(SourceDateList);

    }

    @Override
    public void selectGuild(SelectGuildBean resultBean) {
        filledSelectGuildData(resultBean);
        pinyinComparator = new PinyinComparator();
        Collections.sort(SourceDateList, pinyinComparator);
        adapter.addData(SourceDateList);
    }

    /**
     * 填充数据
     *
     * @param date
     * @return
     */
    @SuppressWarnings("JavaDoc")
    private void filledSelectGuildData(SelectGuildBean date) {
        List<SortModel> mSortList = new ArrayList<>();
        for (int i = 0; i < date.getGuilds().size(); i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date.getGuilds().get(i).getGuild_name());
            sortModel.setId(date.getGuilds().get(i).getGuild_id());

//            sortModel.setSex(i % 2);
            String name = date.getGuilds().get(i).getGuild_name();
            String pinyin = characterParser.getSelling(name);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        SourceDateList.clear();
        SourceDateList.addAll(mSortList);
    }

    private void filledData(SortModelBean date) {
        List<SortModel> mSortList = new ArrayList<>();
        for (int i = 0; i < date.getPlatfroms().size(); i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date.getPlatfroms().get(i).getName());
            sortModel.setIconUrl(date.getPlatfroms().get(i).getIcon_url());
            sortModel.setId(date.getPlatfroms().get(i).getId());
            sortModel.setIs_paltfrom(date.getPlatfroms().get(i).getIs_paltfrom());
            sortModel.setTable_flag(date.getPlatfroms().get(i).getTable_flag());
            String name = date.getPlatfroms().get(i).getName();
            String pinyin = characterParser.getSelling(name);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }
            mSortList.add(sortModel);
//            }
        }
        SourceDateList.clear();
        SourceDateList.addAll(mSortList);
    }
}
