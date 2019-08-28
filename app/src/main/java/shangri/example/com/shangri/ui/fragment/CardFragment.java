package shangri.example.com.shangri.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.presenter.MineFragmentPresenter;
import shangri.example.com.shangri.presenter.view.MineFragmentView;
import shangri.example.com.shangri.ui.activity.AnchoDetailActivity;
import shangri.example.com.shangri.ui.activity.OtherCoachListActivity;
import shangri.example.com.shangri.ui.activity.OtherTaskListActivity;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.TimeUtil;

/**
 * 我的
 * Created by chengaofu on 2017/6/21.
 */

public class CardFragment extends BaseFragment<MineFragmentView, MineFragmentPresenter> implements MineFragmentView {


    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_plantfrom_name)
    TextView tvPlantfromName;
    @BindView(R.id.ll_anchor_show)
    LinearLayout llAnchorShow;
    @BindView(R.id.manager_receive_task)
    RelativeLayout managerReceiveTask;
    @BindView(R.id.manager_push_task)
    RelativeLayout managerPushTask;
    @BindView(R.id.manager_push_coach)
    RelativeLayout managerPushCoach;
    @BindView(R.id.ll_manager_show)
    LinearLayout llManagerShow;
    @BindView(R.id.guild_push_task)
    RelativeLayout guildPushTask;
    @BindView(R.id.guild_push_coach)
    RelativeLayout guildPushCoach;
    @BindView(R.id.ll_guild_show)
    LinearLayout llGuildShow;
    @BindView(R.id.anchor_receive_task)
    RelativeLayout anchorReceiveTask;
    @BindView(R.id.anchor_receive_coach)
    RelativeLayout anchorReceiveCoach;
    @BindView(R.id.ll_anchor_task_show)
    LinearLayout llAnchorTaskShow;
    @BindView(R.id.ll_anchor_baobiao)
    LinearLayout llAnchorBaobiao;
    private ProgressDialogFragment mProgressDialog;

    private String guild_id;
    private String guild_name;
    private String uid;
    private String anchor_name;
    //空字符串   自己查看自己名品   //有数据   查看其它人的名片
    private String register_role = "";
    private String register_id = "";
    //  点击自己名片  主播 0
    // 会长 管理员 1    看其他的名片   不用传（现在是传0操作）
    public String type = "";


    public static CardFragment getInstance(String guild_id, String guild_name, String uid, String anchor_name, String register_role, String register_id) {
        CardFragment fragment = new CardFragment();
        Bundle bundle = new Bundle();
        bundle.putString("guild_id", guild_id);
        bundle.putString("guild_name", guild_name);
        bundle.putString("uid", uid);
        bundle.putString("anchor_name", anchor_name);
        bundle.putString("register_role", register_role);
        bundle.putString("register_id", register_id);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void initViewsAndEvents() {
        Bundle arguments = getArguments();
        guild_id = arguments.getString("guild_id");
        guild_name = arguments.getString("guild_name");
        uid = arguments.getString("uid");
        anchor_name = arguments.getString("anchor_name");
        register_role = arguments.getString("register_role");
        register_id = arguments.getString("register_id");
        if (uid != null) {
            if (uid.length() > 0) {
                tvId.setText(uid);
            }
        }
        if (anchor_name != null) {
            if (anchor_name.length() > 0) {
                tvPlantfromName.setText(anchor_name);
            }
        }
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
//        mProgressDialog.show(getActivity().getSupportFragmentManager());

        if (register_role.length() > 0) {

            switch (register_role) {
                case "1":
                    llAnchorTaskShow.setVisibility(View.GONE);
                    llAnchorBaobiao.setVisibility(View.GONE);
                    llAnchorShow.setVisibility(View.GONE);

                    llManagerShow.setVisibility(View.GONE);
                    llGuildShow.setVisibility(View.VISIBLE);

                    break;
                case "2":
                    llAnchorTaskShow.setVisibility(View.VISIBLE);
                    llAnchorBaobiao.setVisibility(View.VISIBLE);
                    llAnchorShow.setVisibility(View.VISIBLE);

                    llManagerShow.setVisibility(View.GONE);
                    llGuildShow.setVisibility(View.GONE);
                    break;
                default:
                    llAnchorTaskShow.setVisibility(View.GONE);
                    llAnchorBaobiao.setVisibility(View.GONE);
                    llAnchorShow.setVisibility(View.GONE);
                    llManagerShow.setVisibility(View.VISIBLE);
                    llGuildShow.setVisibility(View.GONE);
                    break;
            }

        } else {

            if (UserConfig.getInstance().getRole().equals("1")) {
                llAnchorTaskShow.setVisibility(View.GONE);
                llAnchorBaobiao.setVisibility(View.GONE);
                llAnchorShow.setVisibility(View.GONE);

                llManagerShow.setVisibility(View.GONE);
                llGuildShow.setVisibility(View.VISIBLE);

            } else if (UserConfig.getInstance().getRole().equals("2")) {
                llAnchorTaskShow.setVisibility(View.VISIBLE);
                llAnchorBaobiao.setVisibility(View.VISIBLE);
                llAnchorShow.setVisibility(View.VISIBLE);

                llManagerShow.setVisibility(View.GONE);
                llGuildShow.setVisibility(View.GONE);
            } else {
                llAnchorTaskShow.setVisibility(View.GONE);
                llAnchorBaobiao.setVisibility(View.GONE);
                llAnchorShow.setVisibility(View.GONE);
                llManagerShow.setVisibility(View.VISIBLE);
                llGuildShow.setVisibility(View.GONE);
            }
        }


    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    protected int getNormalLayoutId() {
        return R.layout.fragment_card;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.fragment_card;
    }


    @Override
    protected MineFragmentPresenter createPresenter() {
        return new MineFragmentPresenter(getContext(), this);

    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void accountData(AccountDataBean mAccountDataBean) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        assert rootView != null;
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @OnClick({R.id.ll_anchor_baobiao, R.id.manager_receive_task, R.id.manager_push_task, R.id.manager_push_coach, R.id.guild_push_task, R.id.guild_push_coach, R.id.anchor_receive_task, R.id.anchor_receive_coach})
    public void onViewClicked(View view) {
//        Intent intent = new Intent(getActivity(), OtherCoachListActivity.class);
        switch (view.getId()) {
            case R.id.manager_receive_task:
                OtherTaskListActivity.sym = 1;
                Intent intent1 = new Intent(getActivity(), OtherTaskListActivity.class);
                intent1.putExtra("register_id", register_id + "");
                intent1.putExtra("guild_id", guild_id + "");
                intent1.putExtra("title", "收到的任务");
                startActivity(intent1);
                break;
            case R.id.manager_push_task:
                OtherTaskListActivity.sym = 0;
                Intent intent2 = new Intent(getActivity(), OtherTaskListActivity.class);
                intent2.putExtra("register_id", register_id + "");
                intent2.putExtra("guild_id", guild_id + "");
                intent2.putExtra("title", "发布的任务");
                startActivity(intent2);
                break;
            case R.id.manager_push_coach:
                Intent intent = new Intent(getActivity(), OtherCoachListActivity.class);
                intent.putExtra("register_id", register_id + "");
                intent.putExtra("guild_id", guild_id + "");
                intent.putExtra("title", "发布的辅导");
                startActivity(intent);
                break;
            case R.id.guild_push_task:
                OtherTaskListActivity.sym = 0;
                Intent intent3 = new Intent(getActivity(), OtherTaskListActivity.class);
                intent3.putExtra("register_id", register_id + "");
                intent3.putExtra("guild_id", guild_id + "");
                intent3.putExtra("title", "发布的任务");
                startActivity(intent3);
                break;
            case R.id.guild_push_coach:
                Intent intent4 = new Intent(getActivity(), OtherCoachListActivity.class);
                intent4.putExtra("register_id", register_id + "");
                intent4.putExtra("guild_id", guild_id + "");
                intent4.putExtra("title", "发布的辅导");
                startActivity(intent4);
                break;
            case R.id.anchor_receive_task:
                OtherTaskListActivity.sym = 1;
                Intent intent5 = new Intent(getActivity(), OtherTaskListActivity.class);
                intent5.putExtra("register_id", register_id + "");
                intent5.putExtra("guild_id", guild_id + "");
                intent5.putExtra("title", "收到的任务");
                startActivity(intent5);
                break;
            case R.id.anchor_receive_coach:
                Intent intent6 = new Intent(getActivity(), OtherCoachListActivity.class);
                intent6.putExtra("register_id", register_id + "");
                intent6.putExtra("guild_id", guild_id + "");
                intent6.putExtra("title", "收到的辅导");
                startActivity(intent6);
                break;
            case R.id.ll_anchor_baobiao:
                long currentTime = 0;
                try {
                    currentTime = TimeUtil.getCurrentTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Intent intent7 = new Intent(getActivity(), AnchoDetailActivity.class);
                intent7.putExtra("isFromCard",true);
                intent7.putExtra("platfrom_name",guild_name);
                intent7.putExtra("anchor_uid",uid);
                intent7.putExtra("guild_id",guild_id);
                startActivity(intent7);
                break;

        }
    }
}
