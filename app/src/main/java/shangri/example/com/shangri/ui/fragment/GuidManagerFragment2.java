package shangri.example.com.shangri.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.base.BaseFragmentAdapter;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.MyGuildPresenter;
import shangri.example.com.shangri.presenter.view.MyGuildView;
import shangri.example.com.shangri.ui.activity.MyGuildActivity;
import shangri.example.com.shangri.ui.adapter.FastMyGuildAdapter;
import shangri.example.com.shangri.ui.adapter.MyGuildAdapter;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.TableUtils;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * Description:
 * Data：2018/11/6-18:51
 * Author: lin
 */
public class GuidManagerFragment2 extends BaseFragment<MyGuildView, MyGuildPresenter> implements MyGuildView {
    @BindView(R.id.recycler_view1)
    RecyclerView recycler_view1;

    @BindView(R.id.activity_empty_linshi)
    LinearLayout activity_empty_linshi;
    @BindView(R.id.image_type_empty)
    ImageView image_type_empty;
    @BindView(R.id.tv_text1_empty)
    TextView tv_text1_empty;
    @BindView(R.id.tv_text2_empty)
    TextView tv_text2_empty;

    @BindView(R.id.ll_info)
    LinearLayout ll_info;


    private List<MyGuildListDataBean.GuildsBean> mNewsList = new ArrayList<>();
    private MyGuildAdapter mAdapter;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.fragment_guid_manager;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.fragment_guid_manager;
    }

    @Override
    protected MyGuildPresenter createPresenter() {
        return new MyGuildPresenter(getActivity(), this);
    }

    @Override
    protected void initViewsAndEvents() {
        image_type_empty.setImageDrawable(getResources().getDrawable(R.mipmap.sousuo_kong));
        tv_text1_empty.setText("对不起,这里空空如也呀~");
        tv_text2_empty.setText("");

        recycler_view1.setLayoutManager(new FastLinearScrollManger(getActivity()));
        mAdapter = new MyGuildAdapter(getActivity(), R.layout.my_guild_item, mNewsList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        recycler_view1.setAdapter(mAdapter);
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            mPresenter.requestListData();
        }

    }

    @Override
    public void myGuildDtaList(MyGuildListDataBean resultBean) {
//        mNewsList = resultBean.getGuilds();
        mNewsList.clear();
        ll_info.setVisibility(View.VISIBLE);
        activity_empty_linshi.setVisibility(View.GONE);
        for (MyGuildListDataBean.GuildsBean bean : resultBean.getGuilds()) {
            if (bean.getCheck_status().equals("0")) {
                mNewsList.add(bean);
            }
        }
        mAdapter.setData(mNewsList);
        if (mNewsList.size() == 0) {
            ll_info.setVisibility(View.GONE);
            activity_empty_linshi.setVisibility(View.VISIBLE);
        } else {
            ll_info.setVisibility(View.VISIBLE);
            activity_empty_linshi.setVisibility(View.GONE);
        }

    }

    @Override
    public void memberLate(timeBean resultBean) {

    }

    @Override
    public void guildRatio() {

    }

    @Override
    public void SupportFromList(SupportFromList resultBean) {

    }

    @Override
    public void requestFailed(String message) {

    }

}
