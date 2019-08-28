package shangri.example.com.shangri.util.sharepic;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.model.bean.response.ResShareBean;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.util.StringUtil;


/**
 * Created by HomgWu on 2017/11/29.
 */

public class SharePicModel extends GenerateModel {

    CircleImageView ivUserIcon;
    TextView tvKaiboNumLeft;
    ImageView ivKaiboArrow;
    TextView tvKaiboNumRight;
    TextView tvKaiboMonthLeft;
    TextView tvKaiboMonthRight;
    TextView tvFaqiNumLeft;
    ImageView ivFaqiArrow;
    TextView tvFaqiNumRight;
    TextView tvFaqiMonthLeft;
    TextView tvFaqiMonthRight;
    Bitmap bitmap;
    TextView tvName;
    TextView tvMonth;
    TextView tvFudaoNumLeft;
    ImageView ivFudaoArrow;
    TextView tvFudaoNumRight;
    TextView tvFudaoMonthLeft;
    TextView tvFudaoMonthRight;
    private View mSharePicView;
    private int mAvatarResId;
    ResShareBean shareBean;
    String path;

    public SharePicModel(ViewGroup rootView) {
        super(rootView);
    }

    @Override
    protected void startPrepare(GeneratePictureManager.OnGenerateListener listener) throws Exception {
        int role = Integer.parseInt(UserConfig.getInstance().getRole());
        if (role == 1) {
            //开播
            mSharePicView = LayoutInflater.from(mContext).inflate(R.layout.layout_share_pic_huizhang, mRootView, false);
            tvKaiboNumLeft = mSharePicView.findViewById(R.id.tv_kaibo_num_left);
            tvKaiboNumRight = mSharePicView.findViewById(R.id.tv_kaibo_num_right);
            ivKaiboArrow = mSharePicView.findViewById(R.id.iv_kaibo_arrow);
            tvKaiboMonthLeft = mSharePicView.findViewById(R.id.tv_kaibo_month_left);
            tvKaiboMonthRight = mSharePicView.findViewById(R.id.tv_kaibo_month_right);

            tvKaiboNumLeft.setText(shareBean.getMonth().getAnchor_count() + "");
            tvKaiboNumRight.setText(shareBean.getPre_month().getAnchor_count() + "");
            tvKaiboMonthLeft.setText(splitMonth(shareBean.getMonth().getMonth()) + "月开播主播");
            tvKaiboMonthRight.setText(splitMonth(shareBean.getPre_month().getMonth()) + "月开播主播");
            setArrow(ivKaiboArrow, shareBean.getMonth().getAnchor_count(), shareBean.getPre_month().getAnchor_count());
            //辅导主播
            tvFudaoNumLeft = mSharePicView.findViewById(R.id.tv_fudao_num_left);
            ivFudaoArrow = mSharePicView.findViewById(R.id.iv_fudao_arrow);
            tvFudaoNumRight = mSharePicView.findViewById(R.id.tv_fudao_num_right);
            tvFudaoMonthLeft = mSharePicView.findViewById(R.id.tv_fudao_month_left);
            tvFudaoMonthRight = mSharePicView.findViewById(R.id.tv_fudao_month_left);

            tvFudaoNumLeft.setText(shareBean.getMonth().getInspect_anchor());
            tvFudaoNumRight.setText(shareBean.getPre_month().getInspect_anchor());
            tvFudaoMonthLeft.setText(splitMonth(shareBean.getMonth().getMonth()) + "月辅导主播");
            tvFudaoMonthRight.setText(splitMonth(shareBean.getPre_month().getMonth()) + "月辅导主播");
            setArrow(ivFudaoArrow, shareBean.getMonth().getInspect_anchor(), shareBean.getPre_month().getInspect_anchor());

            //发起辅导次数
            tvFaqiNumLeft = mSharePicView.findViewById(R.id.tv_faqi_num_left);
            tvFaqiNumRight = mSharePicView.findViewById(R.id.tv_faqi_num_right);
            ivFaqiArrow = mSharePicView.findViewById(R.id.iv_faqi_arrow);
            tvFaqiMonthLeft = mSharePicView.findViewById(R.id.tv_faqi_month_left);
            tvFaqiMonthRight = mSharePicView.findViewById(R.id.tv_faqi_month_right);

            tvFaqiNumLeft.setText(shareBean.getMonth().getInspect_count());
            tvFaqiNumRight.setText(shareBean.getPre_month().getInspect_count());
            tvFaqiMonthLeft.setText(splitMonth(shareBean.getMonth().getMonth()) + "月发起辅导次数");
            tvFaqiMonthRight.setText(splitMonth(shareBean.getPre_month().getMonth()) + "月发起辅导次数");
            setArrow(ivFudaoArrow, shareBean.getMonth().getInspect_count(), shareBean.getPre_month().getInspect_count());
        } else if (role == 2) {//主播
            mSharePicView = LayoutInflater.from(mContext).inflate(R.layout.layout_share_pic_zhubo, mRootView, false);
            tvKaiboNumLeft = mSharePicView.findViewById(R.id.tv_kaibo_num_left);
            tvKaiboNumRight = mSharePicView.findViewById(R.id.tv_kaibo_num_right);
            ivKaiboArrow = mSharePicView.findViewById(R.id.iv_kaibo_arrow);
            tvKaiboMonthLeft = mSharePicView.findViewById(R.id.tv_kaibo_month_left);
            tvKaiboMonthRight = mSharePicView.findViewById(R.id.tv_kaibo_month_right);

            tvKaiboNumLeft.setText(shareBean.getMonth().getLive_time() + "");
            tvKaiboNumRight.setText(shareBean.getPre_month().getLive_time() + "");
            tvKaiboMonthLeft.setText(splitMonth(shareBean.getMonth().getMonth()) + "月累计开播时长");
            tvKaiboMonthRight.setText(splitMonth(shareBean.getPre_month().getMonth()) + "月累计开播时长");
            setArrow(ivKaiboArrow, shareBean.getMonth().getLive_time(), shareBean.getPre_month().getLive_time());

        } else if (role == 3) {//管理员
            mSharePicView = LayoutInflater.from(mContext).inflate(R.layout.layout_share_pic_admin, mRootView, false);
            tvKaiboNumLeft = mSharePicView.findViewById(R.id.tv_kaibo_num_left);
            tvKaiboNumRight = mSharePicView.findViewById(R.id.tv_kaibo_num_right);
            ivKaiboArrow = mSharePicView.findViewById(R.id.iv_kaibo_arrow);
            tvKaiboMonthLeft = mSharePicView.findViewById(R.id.tv_kaibo_month_left);
            tvKaiboMonthRight = mSharePicView.findViewById(R.id.tv_kaibo_month_right);

            tvKaiboNumLeft.setText(shareBean.getMonth().getAnchor_count() + "");
            tvKaiboNumRight.setText(shareBean.getPre_month().getAnchor_count() + "");
            tvKaiboMonthLeft.setText(splitMonth(shareBean.getMonth().getMonth()) + "月开播主播量");
            tvKaiboMonthRight.setText(splitMonth(shareBean.getPre_month().getMonth()) + "月开播主播量");
            setArrow(ivKaiboArrow, shareBean.getMonth().getAnchor_count(), shareBean.getPre_month().getAnchor_count());
        }
        tvName = mSharePicView.findViewById(R.id.tv_name);
        tvMonth = mSharePicView.findViewById(R.id.tv_month);
        tvName.setText("  " + shareBean.getPlatfrom_name() + "");
        tvMonth.setText(shareBean.getMonth().getMonth() + "月");
        ivUserIcon = mSharePicView.findViewById(R.id.iv_user_icon);
        ivUserIcon.setImageBitmap(bitmap);
        prepared(listener);
    }

    @Override
    public View getView() {
        return mSharePicView;
    }

    public void setShareContent(ResShareBean shareBean, Bitmap bitmap) {
        this.shareBean = shareBean;
        this.bitmap = bitmap;
    }

    private String splitMonth(String s) {
        String[] split = s.split("-");
        return split[split.length - 1];
    }

    /**
     * 设置箭头方向
     *
     * @param
     * @param num1
     * @param num2
     */
    public void setArrow(ImageView iv, String num1, String num2) {

        int n1 = StringUtil.string2num(num1);
        int n2 = StringUtil.string2num(num2);

        if (n1 > n2) {
            Drawable dwLeft = mContext.getResources().getDrawable(R.mipmap.ssjt);
            iv.setImageDrawable(dwLeft);
        } else if (n1 == n2) {
            Drawable dwLeft = mContext.getResources().getDrawable(R.mipmap.ssjt);
//            iv.setImageDrawable(dwLeft);
        } else if (n1 < n2) {
            Drawable dwLeft = mContext.getResources().getDrawable(R.mipmap.fx_xjjt);
            iv.setImageDrawable(dwLeft);
        }
    }
}
