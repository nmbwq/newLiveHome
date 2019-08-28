package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.CommonAdapter;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.MyAnchorListBeanResponse;
import shangri.example.com.shangri.swipeMenuListView.SwipeMenu;
import shangri.example.com.shangri.swipeMenuListView.SwipeMenuCreator;
import shangri.example.com.shangri.swipeMenuListView.SwipeMenuItem;
import shangri.example.com.shangri.swipeMenuListView.SwipeMenuListView;
import shangri.example.com.shangri.swipeMenuListView.SwipeMenuView;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.ui.view.ListViewForScrollView;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class NewMyAnchorAdapter extends BaseListAdapter<MyAnchorListBeanResponse.GuildsBean> {
    private Context mContext;
    private Animation mLikeAnim;
    List<MyAnchorListBeanResponse.GuildsBean> data = new ArrayList<>();

    private CommonAdapter<MyAnchorListBeanResponse.GuildsBean.AnchorsBean> mAdapter;

    public NewMyAnchorAdapter(Context context, int layoutId, List<MyAnchorListBeanResponse.GuildsBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        data = datas;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, final MyAnchorListBeanResponse.GuildsBean guildsBean) {
        final shangri.example.com.shangri.swipeMenuListView.SwipeMenuListView rv_partol = helper.getView(R.id.rv_partol);
        final RelativeLayout ll_my_zhubo = helper.getView(R.id.ll_my_zhubo);

        final ImageView im_up = helper.getView(R.id.im_up);
        helper.setText(R.id.tv_gonghui_name, guildsBean.getGuild_name() + "");
        helper.setText(R.id.tv_gonghui_number, guildsBean.getAnchors_count() + " 人");


        if (guildsBean.getShow()) {
            rv_partol.setVisibility(View.VISIBLE);
            im_up.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.my_sanjiao_down));
        } else {
            im_up.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.my_sanjiao_xiangyou));
            rv_partol.setVisibility(View.GONE);
        }

        ll_my_zhubo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (guildsBean.getShow()) {
                    guildsBean.setShow(false);
                    rv_partol.setVisibility(View.GONE);
                    im_up.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.my_sanjiao_xiangyou));
                } else {
                    Log.d("Debug", "主播人数为" + guildsBean.getAnchors().size());
                    mAdapter = new CommonAdapter<MyAnchorListBeanResponse.GuildsBean.AnchorsBean>(mContext, guildsBean.getAnchors(), R.layout.item_my_anchor_item) {
                        @Override
                        public void convert(shangri.example.com.shangri.adapter.ViewHolder helper, MyAnchorListBeanResponse.GuildsBean.AnchorsBean item) {
//                            CircleImageView im_photo = (CircleImageView) helper.getView(R.id.im_photo);
//                            Glide.with(mContext)
//                                    .load(item.getAvatar_url())
//                                    .placeholder(R.mipmap.bg_touxiang)
//                                    .crossFade()
//                                    .into(im_photo);
//                            helper.setText(R.id.tv_name, item.getAnchor_name() + "");
//                            helper.setText(R.id.tv_id, item.getAnchor_uid() + "");
                        }
                    };
                    rv_partol.setAdapter(mAdapter);
                    im_up.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.my_sanjiao_down));
                    guildsBean.setShow(true);
                    rv_partol.setVisibility(View.VISIBLE);


                    SwipeMenuCreator creator = new SwipeMenuCreator(){

                        @Override
                        public void create(SwipeMenu menu) {

                            //创建一个"打开"功能菜单
                            SwipeMenuItem openItem = new SwipeMenuItem(mContext);
                            // mContext
                            openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,0xCE)));
                            // 宽度：菜单的宽度是一定要有的，否则不会显示
                            openItem.setWidth(dp2px(80));
                            // 菜单标题
                            openItem.setTitle("打开");
                            // 标题文字大小
                            openItem.setTitleSize(16);
                            // 标题的颜色
                            openItem.setTitleColor(Color.WHITE);
                            // 添加到menu
                            menu.addMenuItem(openItem);

                            //创建一个"打开"功能菜单
                            SwipeMenuItem deleteItem = new SwipeMenuItem(mContext);
                            // 设置菜单的背景
                            deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,0x3F, 0x25)));
                            // 宽度：菜单的宽度是一定要有的，否则不会显示
                            deleteItem.setWidth(dp2px(80));
                            // 菜单标题
                            deleteItem.setTitle("删除");
                            // 标题文字大小
                            deleteItem.setTitleSize(16);
                            // 标题的颜色
                            deleteItem.setTitleColor(Color.WHITE);
                            // 给菜单设置一个图标
                            //deleteItem.setIcon(R.drawable.ic_delete);
                            // 添加到menu
                            menu.addMenuItem(deleteItem);
                        }
                    };
                    // 不要忘记了
                    rv_partol.setMenuCreator(creator);

                    rv_partol.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                        @Override
                        public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                            switch (index) {
                                case 0:
                                    ToastUtil.showShort("点击打开操作");
                                    break;
                                case 1:
                                    ToastUtil.showShort("点击删除操作");
                                    break;
                            }
                        }
                    });


                }

            }
        });

    }


    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                mContext.getResources().getDisplayMetrics());
    }
    @Override
    public void convert(ViewHolder helper, MyAnchorListBeanResponse.GuildsBean guildsBean, List<Object> payloads) {

    }
}
