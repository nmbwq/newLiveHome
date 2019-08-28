package shangri.example.com.shangri.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxApi;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.api.RxService;
import shangri.example.com.shangri.manage.WrapContentLinearLayoutManager;
import shangri.example.com.shangri.model.bean.request.Praise;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.TrainingArticleBean;
import shangri.example.com.shangri.ui.activity.AudioPlayActivity;
import shangri.example.com.shangri.ui.activity.LoginTypeActivity;
import shangri.example.com.shangri.ui.activity.MoreArticlesActivity;
import shangri.example.com.shangri.ui.activity.NewLoginUserActivity;
import shangri.example.com.shangri.ui.webview.ActivityWebView;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.StartActivityUtils;


/**
 * 培训文章
 */
public class ArticleAdapter extends BaseQuickAdapter<TrainingArticleBean.TrainsBean, BaseViewHolder> {


    private Context context;
    private int size;

    private String img_url;
    private String name;

    int k = 0;

    private int position = 0;
    ;

    public ArticleAdapter(Context context, int layoutResId, @Nullable List<TrainingArticleBean.TrainsBean> data) {
        super(layoutResId, data);

        this.context = context;

    }

    @Override
    protected void convert(BaseViewHolder helper, final TrainingArticleBean.TrainsBean item) {
        position = helper.getAdapterPosition();
        RelativeLayout rl_cultivate_title = helper.getView(R.id.rl_cultivate_title);
        TextView tv_more_makeup_skills = helper.getView(R.id.tv_more_makeup_skills);

        RecyclerView rv_train_item = helper.getView(R.id.rv_train_item);
        WrapContentLinearLayoutManager layoutManager =
                new WrapContentLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);

        rv_train_item.setHasFixedSize(true);
        rv_train_item.setNestedScrollingEnabled(false);
        rv_train_item.setLayoutManager(layoutManager);
        List<TrainingArticleBean.TrainsBean.ListBean> list = item.getList();
        if (list.size()==0) {
            rl_cultivate_title.setVisibility(View.GONE);
            View view = helper.getView(R.id.top_view);
            view.setVisibility(View.GONE);
        } else {
            rl_cultivate_title.setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_description_title, item.getName());
            TrainAdapter trainAdapter = new TrainAdapter(context, R.layout.item_train, list);
//            trainAdapter.addData();
            rv_train_item.setAdapter(trainAdapter);
            trainAdapter.notifyDataSetChanged();
            tv_more_makeup_skills.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String token = UserConfig.getInstance().getToken();
                    if (TextUtils.isEmpty(token)) {
                        StartActivityUtils.startActivity();
                    } else {
                        context.startActivity(new Intent(context, MoreArticlesActivity.class)
                                .putExtra("catagory_id", item.getId())
                                .putExtra("title", item.getName())
                                .putExtra("type", "2")
                                .putExtra("img_url", item.getImg_url()));
                    }

                }
            });


        }


    }


}
