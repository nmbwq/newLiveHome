package shangri.example.com.shangri.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.CompanyMainBean;
import shangri.example.com.shangri.ui.activity.UserFeedBackActivity;
import shangri.example.com.shangri.ui.listener.ImagesAddListener;
import shangri.example.com.shangri.ui.view.RoundImageView;
import shangri.example.com.shangri.util.InputUtil;
import shangri.example.com.shangri.util.KeyBoardUtil;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Description:添加主播适配器
 * Data：2018/11/8-15:06
 * Author: lin
 */
public class AddStarAdapter extends BaseListAdapter<CompanyMainBean.AnchorStar> {
    Context mContext;
    private ImagesAddListener onClickAddListener;
    private onClickItem onClickItem;

    public AddStarAdapter(Context context, int layoutId, List<CompanyMainBean.AnchorStar> datas, ImagesAddListener listener,onClickItem onClickItem) {
        super(context, layoutId, datas);
        this.mContext = context;
        this.onClickAddListener = listener;
        this.onClickItem = onClickItem;
    }

    @Override
    public void convert(shangri.example.com.shangri.util.ViewHolder helper, CompanyMainBean.AnchorStar anchorStar) {
        final int pos = helper.getAdapterPosition();
//        helper.setText(R.id.tv_title, anchorStar.getAnchor_name());
        final RoundImageView imageView = helper.getView(R.id.iv_icon);
        final EditText editText = helper.getView(R.id.et_nickname);
        RelativeLayout picSelector = helper.getView(R.id.ll_add_head);
        if (anchorStar.getAnchor_logo().length()>0){
            Glide.with(mContext).load(anchorStar.getAnchor_logo()).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    imageView.setImageBitmap(resource);
                }
            });
        }else {
            Glide.with(mContext).load(R.mipmap.mxzb_mrtx).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    imageView.setImageBitmap(resource);
                }
            });
        }
//        if (anchorStar.getAnchor_name().length()>0){
//            editText.setText(anchorStar.getAnchor_name()+"");
//        }else {
//            editText.setText("");
//        }
        final String url = anchorStar.getAnchor_logo();
        picSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KeyBoardUtil.KeyBoard(mContext,"close");
                if (onClickAddListener != null) {
                    onClickAddListener.add(pos);
                }
            }
        });
//        InputUtil.hideInputMethdView(mContext,editText);
        KeyBoardUtil.KeyBoard(mContext,"close");
//        editText.clearFocus();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editText.clearFocus();
                onClickItem.onClick(editText.getText().toString()+"",pos);
            }
        });

    }

    @Override
    public void convert(ViewHolder helper, CompanyMainBean.AnchorStar companyPlatfrom, List<Object> payloads) {

    }

    public interface onClickItem{
        void onClick(String text,int position);
    }


}