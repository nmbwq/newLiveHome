package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.upgradePackageBean;
import shangri.example.com.shangri.presenter.PostbarPresenter;
import shangri.example.com.shangri.presenter.view.PostbarView;
import shangri.example.com.shangri.ui.adapter.PostbarAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * S升级职位栏
 */
public class UpgradePostBarActivity extends BaseActivity<PostbarView, PostbarPresenter> implements PostbarView {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.commit)
    TextView commit;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv_show_message)
    TextView tvShowMessage;
    private ProgressDialogFragment mProgressDialog;
    private PostbarAdapter mAdapter;
    private List<upgradePackageBean.ListBean> mList = new ArrayList<>();
    //   招聘信息ID
    String recruit_id;
    //可用波豆
    int usable_bd;
    //点击购买时间需要波豆数
    int BoDouNumber;
    //选中套餐的id
    int package_id;

    //    广告位是否已满 1已满 0未满
    int is_already_full;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_upgrade_postbar_lauot;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_upgrade_postbar_lauot;
    }

    @Override
    protected PostbarPresenter createPresenter() {
        return new PostbarPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        recruit_id = getIntent().getStringExtra("recruit_id");
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new PostbarAdapter(this, R.layout.item_bd1, mList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                for (int i = 0; i < mAdapter.getAll().size(); i++) {
                    mAdapter.getAll().get(i).setClick(false);
                }
                mAdapter.getAll().get(position).setClick(true);
                BoDouNumber = mAdapter.getAll().get(position).getHf_bd();
                package_id = mAdapter.getAll().get(position).getId();
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(this.getSupportFragmentManager());
        mPresenter.upgradePackage();
    }


    @OnClick({R.id.back, R.id.commit, R.id.tv2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.commit:
                if (PointUtils.isFastClick()) {
                    if (is_already_full == 0) {
                        if (usable_bd >= BoDouNumber) {
                            if (mProgressDialog == null) {
                                mProgressDialog = new ProgressDialogFragment();
                            }
                            mPresenter.recruitUpgrade(recruit_id + "", package_id + "");
                        } else {
                            ToastUtil.showShort("波豆数不够，请进行充值");
                        }
                    } else {
                        ToastUtil.showShort("广告位以满");
                    }
                }
                break;
            case R.id.tv2:
                if (PointUtils.isFastClick()) {
                    Intent intent = new Intent(this, VirtualCoinActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    //
    @Override
    public void upgradePackage(upgradePackageBean bean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (mList.size() > 0) {
            mList.clear();
        }
        //默认选中第一个
        for (int i = 0; i < bean.getList().size(); i++) {
            upgradePackageBean.ListBean listBean = bean.getList().get(i);
            if (i == 0) {
                listBean.setClick(true);
                package_id = listBean.getId();
                BoDouNumber = listBean.getHf_bd();
            } else {
                listBean.setClick(false);
            }
            mList.add(listBean);
        }
        mAdapter.setData(mList);
        tv1.setText(bean.getBd_ratio() + "波豆=1元，波豆数不足？请");
        tvShowMessage.setText(bean.getState() + "");
        usable_bd = bean.getUsable_bd();
        is_already_full = bean.getIs_already_full();
    }

    @Override
    public void recruitUpgrade() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        finish();
    }
}
