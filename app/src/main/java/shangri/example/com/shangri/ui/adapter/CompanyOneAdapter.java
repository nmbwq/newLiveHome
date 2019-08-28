package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.adapter.CommonAdapter;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.MyAnchorListBeanResponse;
import shangri.example.com.shangri.model.bean.response.companyOrgBean;
import shangri.example.com.shangri.model.bean.response.justBean;
import shangri.example.com.shangri.swipeMenuListView.SwipeMenu;
import shangri.example.com.shangri.swipeMenuListView.SwipeMenuCreator;
import shangri.example.com.shangri.swipeMenuListView.SwipeMenuItem;
import shangri.example.com.shangri.swipeMenuListView.SwipeMenuListView;
import shangri.example.com.shangri.ui.activity.CallingCardActivity;
import shangri.example.com.shangri.ui.activity.CompamyInfoActivity;
import shangri.example.com.shangri.ui.view.ListViewForScrollView;
import shangri.example.com.shangri.ui.view.SlideDelete;
import shangri.example.com.shangri.ui.view.SwipeLayout;
import shangri.example.com.shangri.util.AndroidInterface.CompanyFace;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class CompanyOneAdapter extends BaseListAdapter<companyOrgBean.GuildBean> {
    private Context mContext;
    private Animation mLikeAnim;
    private CompanyFace inter;
    //公会id
    String guild_id;

    List<companyOrgBean.GuildBean> data = new ArrayList<>();
    int number;


    public static List<justBean> dataIsOpen = new ArrayList<>();

    /**
     * 是否是打开状态
     */
    private boolean isOpen;
    //存放所有已经打开的菜单
    private List<SwipeLayout> openList = new ArrayList<SwipeLayout>();

    boolean open;


    private CommonAdapter<companyOrgBean.GuildBean.AdminsBean> ManagermAdapter;

    private CommonAdapter<companyOrgBean.GuildBean.OtheranchorsBean> OtherAdapter;
    private CommonAdapter<companyOrgBean.GuildBean.AdminsBean.AnchorsBean> AnchormAdapter;

    public CompanyOneAdapter(Context context, int layoutId, List<companyOrgBean.GuildBean> datas, CompanyFace companyFace) {
        super(context, layoutId, datas);
        mContext = context;
        data = datas;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
        inter = companyFace;
    }

    public void change() {


        Log.d("Debug", "在次返回到适配器里面");
        if (OtherAdapter != null) {
            OtherAdapter.notifyDataSetChanged();
        }
        if (AnchormAdapter != null) {
            AnchormAdapter.notifyDataSetChanged();
        }
        if (ManagermAdapter != null) {
            ManagermAdapter.notifyDataSetChanged();
        }

    }


    @Override
    public void convert(final ViewHolder helper, final companyOrgBean.GuildBean item) {
        final ListViewForScrollView rv_partol = helper.getView(R.id.rv_partol);
        final SwipeMenuListView rv_other_partol = helper.getView(R.id.rv_other_partol);
        final RelativeLayout ll_my_zhubo = helper.getView(R.id.ll_my_zhubo);
        final LinearLayout ll_other = helper.getView(R.id.ll_other);

        number = 0;
        if (UserConfig.getInstance().getRole().equals("2")) {
            number = item.getAdmins().size();
        } else {
            for (int i = 0; i < item.getAdmins().size(); i++) {
                number = number + item.getAdmins().get(i).getAnchors().size();
            }
            number = item.getOtheranchors().size() + item.getAdmins().size() + number;
        }
        final ImageView im_up = helper.getView(R.id.im_up);
        helper.setText(R.id.tv_gonghui_name, item.getGuild_name() + "");
        helper.setText(R.id.tv_gonghui_number, number + " 人");

        Log.d("Debug", "openList的长度为" + dataIsOpen.size());
        if (dataIsOpen.get(helper.getPosition()).isOpen()) {
            rv_partol.setVisibility(View.VISIBLE);
            if (item.getOtheranchors().size() > 0) {
                ll_other.setVisibility(View.VISIBLE);
            } else {
                ll_other.setVisibility(View.GONE);
            }
            im_up.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.my_sanjiao_down));
            Log.d("Debug", "到达开的操作");
        } else {
            ll_other.setVisibility(View.GONE);
            rv_partol.setVisibility(View.GONE);
            Log.d("Debug", "到达管的操作");
            im_up.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.my_sanjiao_xiangyou));
        }

        OtherAdapter = new CommonAdapter<companyOrgBean.GuildBean.OtheranchorsBean>(mContext, item.getOtheranchors(), R.layout.company_other_item) {
            @Override
            public void convert(shangri.example.com.shangri.adapter.ViewHolder helper, final companyOrgBean.GuildBean.OtheranchorsBean item) {

                final ImageView im_photo = helper.getView(R.id.im_photo);
                final TextView tv_name = helper.getView(R.id.tv_name);
                final TextView tv_id = helper.getView(R.id.tv_id);
                final LinearLayout tv_slide = helper.getView(R.id.tv_slide);
                final TextView tv_del = helper.getView(R.id.tv_del);
                final LinearLayout ll_parent = helper.getView(R.id.ll_parent);
                final LinearLayout ll_dianji = helper.getView(R.id.ll_dianji);
                final SwipeLayout swipeLayout = helper.getView(R.id.swipeLayout);
                Glide.with(mContext)
                        .load(item.getAvatar_url())
                        .placeholder(R.mipmap.bg_touxiang)
                        .crossFade()
                        .into(im_photo);
                tv_name.setText(item.getAnchor_name() + "");
                tv_id.setText("ID: " + item.getUid() + "");

                //会长管理员有侧滑删除操作    管理员和主播没有侧滑操作
                if (UserConfig.getInstance().getRole().equals("1")) {
                    tv_slide.setVisibility(View.VISIBLE);
                    tv_del.setText("离职");
                } else if (UserConfig.getInstance().getRole().equals("2")) {
                    tv_slide.setVisibility(View.GONE);
                } else {
                    tv_slide.setVisibility(View.VISIBLE);
                    tv_del.setText("添加");
                }


                tv_del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (UserConfig.getInstance().getRole().equals("1")) {
                         inter.companyBreakanchor(guild_id, item.getUid());
//                            ToastUtil.showShort("其他主播离职操作");

                        } else if (UserConfig.getInstance().getRole().equals("3")) {
                            inter.adminAddanchorFace(guild_id, item.getUid());
//                            ToastUtil.showShort("管理员添加主播操作");
                        }
                    }
                });

                ll_dianji.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Log.d("Debug", "其它主播点击事件");
                        mContext.startActivity(new Intent(mContext, CallingCardActivity.class).putExtra("flag", true).putExtra("register_id", item.getRegister_id()));

                    }
                });

                if (item.isOpen()) {
                    swipeLayout.setOpen(true);
                    swipeLayout.open();
                } else {
                    swipeLayout.setOpen(false);
                    swipeLayout.close();
                }
                swipeLayout.setSwipeChangeListener(new SwipeLayout.OnSwipeChangeListener() {
                    @Override
                    public void onStartOpen(SwipeLayout mSwipeLayout) {
                        ll_parent.requestDisallowInterceptTouchEvent(true);
                        if (!isOpen) {
                            for (SwipeLayout layout : openList) {
                                layout.close();
                            }
                            openList.clear();
                        }
                    }

                    @Override
                    public void onStartClose(SwipeLayout mSwipeLayout) {
                        ll_parent.requestDisallowInterceptTouchEvent(true);
                    }

                    @Override
                    public void onOpen(SwipeLayout mSwipeLayout) {
                        ll_parent.requestDisallowInterceptTouchEvent(true);
                        openList.add(mSwipeLayout);
                        item.setOpen(true);
                    }

                    @Override
                    public void onDraging(SwipeLayout mSwipeLayout) {
                        ll_parent.requestDisallowInterceptTouchEvent(true);
                    }

                    @Override
                    public void onClose(SwipeLayout mSwipeLayout) {
                        ll_parent.requestDisallowInterceptTouchEvent(false);
                        openList.remove(mSwipeLayout);
                        item.setOpen(false);
                    }
                });
            }


        };
        rv_other_partol.setAdapter(OtherAdapter);

        ManagermAdapter = new CommonAdapter<companyOrgBean.GuildBean.AdminsBean>(mContext, item.getAdmins(), R.layout.company_manager_item) {
            @Override
            public void convert(final shangri.example.com.shangri.adapter.ViewHolder helper, final companyOrgBean.GuildBean.AdminsBean adminsBean) {

                final SwipeMenuListView rv_anchor_partol = helper.getView(R.id.rv_anchor_partol);
                final LinearLayout ll_dianji = helper.getView(R.id.ll_dianji);
                final LinearLayout ll_hight = helper.getView(R.id.ll_hight);
                final LinearLayout tv_slide = helper.getView(R.id.tv_slide);
                final ImageView im_photo = helper.getView(R.id.im_photo);
                final TextView tv_name = helper.getView(R.id.tv_name);
                final TextView tv_id = helper.getView(R.id.tv_id);
                final SwipeLayout swipeLayout = helper.getView(R.id.swipeLayout);
                final LinearLayout ll_parent = helper.getView(R.id.ll_parent);
                final TextView tv_zhiding = helper.getView(R.id.tv_zhiding);
                final TextView tv_del = helper.getView(R.id.tv_del);

                //会长管理员有侧滑删除操作    管理员和主播没有侧滑操作
                if (UserConfig.getInstance().getRole().equals("1")) {
                    tv_slide.setVisibility(View.VISIBLE);
                } else if (UserConfig.getInstance().getRole().equals("2")) {
                    tv_slide.setVisibility(View.GONE);
                } else {
                    tv_slide.setVisibility(View.GONE);
                }


                tv_zhiding.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        ToastUtil.showShort("管理员解绑操作");
                        inter.adminRelieve(guild_id, adminsBean.getId());
                        ManagermAdapter.removeData(helper.getPosition());
                    }
                });
                tv_del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        ToastUtil.showShort("管理员离职操作");
                        inter.adminLeave(adminsBean.getId());
                        ManagermAdapter.removeData(helper.getPosition());
                    }
                });
                Glide.with(mContext)
                        .load(adminsBean.getAvatar_url())
                        .placeholder(R.mipmap.bg_touxiang)
                        .crossFade()
                        .into(im_photo);
                tv_name.setText(adminsBean.getNickname() + "");
                tv_id.setText("ID: " + adminsBean.getId() + "");
                if (adminsBean.isOpen()) {
                    swipeLayout.setOpen(true);
                    swipeLayout.open();
                } else {
                    swipeLayout.setOpen(false);
                    swipeLayout.close();
                }
                swipeLayout.setSwipeChangeListener(new SwipeLayout.OnSwipeChangeListener() {

                    @Override
                    public void onStartOpen(SwipeLayout mSwipeLayout) {
                        ll_parent.requestDisallowInterceptTouchEvent(true);
                        if (!isOpen) {
                            for (SwipeLayout layout : openList) {
                                layout.close();
                            }
                            openList.clear();
                        }
                    }

                    @Override
                    public void onStartClose(SwipeLayout mSwipeLayout) {
                        ll_parent.requestDisallowInterceptTouchEvent(true);
                    }

                    @Override
                    public void onOpen(SwipeLayout mSwipeLayout) {
                        ll_parent.requestDisallowInterceptTouchEvent(true);
                        openList.add(mSwipeLayout);
                        adminsBean.setOpen(true);
                    }

                    @Override
                    public void onDraging(SwipeLayout mSwipeLayout) {
                        ll_parent.requestDisallowInterceptTouchEvent(true);
                    }

                    @Override
                    public void onClose(SwipeLayout mSwipeLayout) {
                        ll_parent.requestDisallowInterceptTouchEvent(false);
                        openList.remove(mSwipeLayout);
                        adminsBean.setOpen(false);
                    }
                });

                AnchormAdapter = new CommonAdapter<companyOrgBean.GuildBean.AdminsBean.AnchorsBean>(mContext, adminsBean.getAnchors(), R.layout.company_other_item) {
                    @Override
                    public void convert(shangri.example.com.shangri.adapter.ViewHolder helper, final companyOrgBean.GuildBean.AdminsBean.AnchorsBean item) {
                        final ImageView im_photo = helper.getView(R.id.im_photo);
                        final TextView tv_name = helper.getView(R.id.tv_name);
                        final TextView tv_id = helper.getView(R.id.tv_id);
                        final LinearLayout tv_slide = helper.getView(R.id.tv_slide);
                        final TextView tv_del = helper.getView(R.id.tv_del);
                        final LinearLayout ll_parent = helper.getView(R.id.ll_parent);
                        final LinearLayout ll_dianji = helper.getView(R.id.ll_dianji);
                        final SwipeLayout swipeLayout = helper.getView(R.id.swipeLayout);

                        Glide.with(mContext)
                                .load(item.getAvatar_url())
                                .placeholder(R.mipmap.bg_touxiang)
                                .crossFade()
                                .into(im_photo);
                        tv_name.setText(item.getAnchor_name() + "");
                        tv_id.setText("ID: " + item.getUid() + "");

                        //会长管理员有侧滑删除操作    管理员和主播没有侧滑操作
                        if (UserConfig.getInstance().getRole().equals("1")) {
                            tv_slide.setVisibility(View.VISIBLE);
                            tv_del.setText("离职");
                            tv_del.setBackgroundColor(mContext.getResources().getColor(R.color.ddba69));
                        } else if (UserConfig.getInstance().getRole().equals("2")) {
                            tv_slide.setVisibility(View.GONE);
                        } else {
                            tv_slide.setVisibility(View.VISIBLE);
                            if (adminsBean.getIs_me() == 1) {
                                tv_del.setText("移除");

                                tv_del.setBackgroundColor(mContext.getResources().getColor(R.color.text_color_little_black));
                            } else {
                                tv_del.setText("添加");
                                tv_del.setBackgroundColor(mContext.getResources().getColor(R.color.ddba69));
                            }
                        }

                        tv_del.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if (UserConfig.getInstance().getRole().equals("1")) {
                                       inter.companyBreakanchor(guild_id, item.getUid());
//                                      ToastUtil.showShort("管理员主播离职操作");

                                } else if (UserConfig.getInstance().getRole().equals("3")) {
                                    if (adminsBean.getIs_me() == 1) {
                                        inter.adminBreakanchorFace(guild_id, item.getUid());
//                                        ToastUtil.showShort("自己管理员主播移除操作");
                                    } else {
                                        inter.adminAddanchorFace(guild_id, item.getUid());
//                                        ToastUtil.showShort("添加其他管理员操作");
                                    }

                                }
                            }
                        });


                        if (item.isOpen()) {
                            swipeLayout.setOpen(true);
                            swipeLayout.open();
                        } else {
                            swipeLayout.setOpen(false);
                            swipeLayout.close();
                        }
                        swipeLayout.setSwipeChangeListener(new SwipeLayout.OnSwipeChangeListener() {

                            @Override
                            public void onStartOpen(SwipeLayout mSwipeLayout) {
                                ll_parent.requestDisallowInterceptTouchEvent(true);
                                if (!isOpen) {
                                    for (SwipeLayout layout : openList) {
                                        layout.close();
                                    }
                                    openList.clear();
                                }
                            }

                            @Override
                            public void onStartClose(SwipeLayout mSwipeLayout) {
                                ll_parent.requestDisallowInterceptTouchEvent(true);
                            }

                            @Override
                            public void onOpen(SwipeLayout mSwipeLayout) {
                                ll_parent.requestDisallowInterceptTouchEvent(true);
                                openList.add(mSwipeLayout);
                                adminsBean.setOpen(true);
                            }

                            @Override
                            public void onDraging(SwipeLayout mSwipeLayout) {
                                ll_parent.requestDisallowInterceptTouchEvent(true);
                            }

                            @Override
                            public void onClose(SwipeLayout mSwipeLayout) {
                                ll_parent.requestDisallowInterceptTouchEvent(false);
                                openList.remove(mSwipeLayout);
                                adminsBean.setOpen(false);
                            }
                        });

                        ll_dianji.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
//                                ToastUtil.showShort("管理员主播点击操作");
                                mContext.startActivity(new Intent(mContext, CallingCardActivity.class).putExtra("flag", true).putExtra("register_id", item.getRegister_id()));
                            }
                        });
                    }
                };
                rv_anchor_partol.setAdapter(AnchormAdapter);

                // true 代表展开（列表高度通过计算得到）  false代表关闭
//                Log.d("Debug", "适配器刷新");
//                final LinearLayout.LayoutParams Params = (LinearLayout.LayoutParams) ll_hight.getLayoutParams();
//                Params.height = dp2px(measureListViewWrongHeight(rv_anchor_partol) / 2);
//                ll_hight.setLayoutParams(Params);
//                rv_anchor_partol.setVisibility(View.VISIBLE);

                ll_dianji.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("Debug", "管理员点击事件");
                        mContext.startActivity(new Intent(mContext, CallingCardActivity.class).putExtra("flag", true).putExtra("register_id", adminsBean.getId()));

                    }
                });

            }
        };
        rv_partol.setAdapter(ManagermAdapter);

        ll_my_zhubo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guild_id = item.getGuild_id();
                if (dataIsOpen.get(helper.getPosition()).isOpen()) {
                    rv_partol.setVisibility(View.GONE);
                    ll_other.setVisibility(View.GONE);
                    im_up.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.my_sanjiao_xiangyou));
                    dataIsOpen.get(helper.getPosition()).setOpen(false);
                } else {
                    //其他主播没有数据   布局隐藏
                    if (item.getOtheranchors().size() == 0) {
                        ll_other.setVisibility(View.GONE);
                    } else {
                        ll_other.setVisibility(View.VISIBLE);
                    }
                    rv_partol.setVisibility(View.VISIBLE);
                    im_up.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.my_sanjiao_down));
                    dataIsOpen.get(helper.getPosition()).setOpen(true);
                }

            }
        });

    }

    @Override
    public void convert(ViewHolder helper, companyOrgBean.GuildBean guildBean, List<Object> payloads) {

    }


    public int measureListViewWrongHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return 0;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        // 减掉底部分割线的高度
        int historyHeight = totalHeight
                + (listView.getDividerHeight() * listAdapter.getCount() - 1);

        Log.d("Javine", "wrongHeight = " + historyHeight); //输出最终ListView的高度
        return historyHeight;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                mContext.getResources().getDisplayMetrics());
    }


}
