package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.ChoiceGuildBean;
import shangri.example.com.shangri.model.bean.response.SortModel;
import shangri.example.com.shangri.presenter.ChoiceGuildPresenter;
import shangri.example.com.shangri.presenter.view.ChoiceGuildView;
import shangri.example.com.shangri.ui.adapter.SortAdapter;
import shangri.example.com.shangri.ui.view.SideBar;
import shangri.example.com.shangri.util.CharacterParser;
import shangri.example.com.shangri.util.PinyinComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 选择公会
 * Created by admin on 2017/12/22.
 */

@SuppressWarnings("JavaDoc")
public class ChoiceGuildActivity extends BaseActivity<ChoiceGuildView, ChoiceGuildPresenter> implements ChoiceGuildView {

    @BindView(R.id.country_lvcountry)
    ListView sortListView;

    @BindView(R.id.sidrbar)
    SideBar sideBar;

    @BindView(R.id.tv_guild_title)
    TextView tv_guild_title;

    @BindView(R.id.iv_add_guild)
    TextView iv_add_guild;


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
    protected ChoiceGuildPresenter createPresenter() {
        return new ChoiceGuildPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        tv_guild_title.setText("选择公会");
        iv_add_guild.setVisibility(View.GONE);
        SourceDateList = new ArrayList<>();
        adapter = new SortAdapter(this, SourceDateList, 0);
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

                tvTitle.setTextColor(ContextCompat.getColor(ChoiceGuildActivity.this, R.color.text_color_ok_orange));
                // 设置Intent回调 并设置回调内容
                Intent intent = new Intent();
                intent.putExtra("guildid", SourceDateList.get(position).getId());
                intent.putExtra("content", SourceDateList.get(position).getName());
                setResult(2, intent);
                finish();
            }
        });
        mPresenter.getGuildList();
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
    public void listGuildData(ChoiceGuildBean resultBean) {
        filledData(resultBean);// 填充数据

        PinyinComparator pinyinComparator = new PinyinComparator();
        Collections.sort(SourceDateList, pinyinComparator);
        adapter.addData(SourceDateList);
        Log.d("", "");

    }

    /**
     * 填充数据
     *
     * @param date
     * @return
     */
    @SuppressWarnings("JavaDoc")
    private void filledData(ChoiceGuildBean date) {
        List<SortModel> mSortList = new ArrayList<>();
        for (int i = 0; i < date.getGuilds().size(); i++) {
            Log.d("Debug", "直播的名字" + date.getGuilds().get(i).getGuild_name());
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
}
