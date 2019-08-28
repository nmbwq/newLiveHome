package shangri.example.com.shangri.ui.fragment;

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
import shangri.example.com.shangri.model.bean.response.BossDataBean;
import shangri.example.com.shangri.model.bean.response.PositionListBean;
import shangri.example.com.shangri.presenter.HotPresent;
import shangri.example.com.shangri.presenter.view.HotView;
import shangri.example.com.shangri.ui.adapter.BossAdapter;
import shangri.example.com.shangri.ui.adapter.ZhiweListAdapter;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;

/**
 * Description:热门招聘
 * Data：2018/11/8-21:17
 * Author: lin
 */
public class HotFragment extends BaseFragment<HotView,HotPresent> implements HotView {

    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
//    @BindView(R.id.springview)
//    SpringView springView;
    @BindView(R.id.activity_empty_linshi1)
    LinearLayout activity_empty_linshi1;
    @BindView(R.id.image_type_empty)
    ImageView image_type_empty;
    @BindView(R.id.tv_text1_empty)
    TextView tv_text1_empty;
    @BindView(R.id.tv_text2_empty)
    TextView tv_text2_empty;
    private ZhiweListAdapter mAdapter;
    private List<PositionListBean.ListBean.DataBean> mList = new ArrayList<>();
    @Override
    protected int getNormalLayoutId() {
        return R.layout.fragment_hot_view;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.fragment_hot_view;
    }

    @Override
    protected HotPresent createPresenter() {
        return new HotPresent(getActivity(),this);
    }

    @Override
    protected void initViewsAndEvents() {
        image_type_empty.setImageDrawable(getResources().getDrawable(R.mipmap.sousuo_kong));
        tv_text1_empty.setText("对不起,这里空空如也呀~");
        tv_text2_empty.setText("");
        recycler_view.setLayoutManager(new FastLinearScrollManger(getActivity()));
        mAdapter = new ZhiweListAdapter(getActivity(), R.layout.item_zhiwei_item, mList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        recycler_view.setAdapter(mAdapter);
//        initSpringView();
        mPresenter.getHot();
    }

    private void initSpringView() {
//        springView.setGive(SpringView.Give.TOP);
//        springView.setType(SpringView.Type.FOLLOW);
//        springView.setHeader(new SpringViewHeader(getActivity()));
//        springView.setFooter(new SpringViewFooter(getActivity()));
//        springView.setListener(new SpringView.OnFreshListener() {
//            @Override
//            public void onRefresh() {
//                mPage = 1;
//                mPresenter.getHot(""+CompanyIntroductionFragment.iidd,mPage+"");
//            }
//
//            @Override
//            public void onLoadmore() {
//                mPresenter.getHot(""+CompanyIntroductionFragment.iidd,mPage+"");
//            }
//        });

    }

    @Override
    public void getHotPosition(PositionListBean resultBean) {
        Log.e("recruitList: ","success" );
        if (resultBean.getList().getData().isEmpty()){
            recycler_view.setVisibility(View.GONE);
            activity_empty_linshi1.setVisibility(View.VISIBLE);
        }else {
            mList.clear();
            recycler_view.setVisibility(View.VISIBLE);
            activity_empty_linshi1.setVisibility(View.GONE);
            mAdapter.addAll(resultBean.getList().getData());
        }
    }

    @Override
    public void requestFailed(String message) {
        Log.e("recruitList: ","requestFailed : "+message );
    }
}
