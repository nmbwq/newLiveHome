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
public class GuidManagerFragment extends BaseFragment<MyGuildView, MyGuildPresenter> implements MyGuildView {
    @BindView(R.id.recycler_view1)
    RecyclerView recycler_view1;

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
        return new MyGuildPresenter(getActivity(),this);
    }
    @Override
    protected void initViewsAndEvents() {
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
        for (MyGuildListDataBean.GuildsBean bean : resultBean.getGuilds()){
            if (bean.getCheck_status().equals("1")){
                mNewsList.add(bean);
            }
        }
        mAdapter.setData(mNewsList);
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
