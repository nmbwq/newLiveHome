package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.text.Html;
import android.widget.TextView;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.TipsPageDataBean;
import shangri.example.com.shangri.util.RegexUtil;
import shangri.example.com.shangri.util.ViewHolder;

import java.util.List;

/**
 * 平台公告 adapter
 * Created by chengaofu on 2017/6/29.
 */

public class PlatformTipsAdapter extends BaseListAdapter<TipsPageDataBean> {

    public PlatformTipsAdapter(Context context, int layoutId, List<TipsPageDataBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
    }


    @Override
    public void convert(ViewHolder helper, TipsPageDataBean data) { //, List<Object> payloads
        TextView title = helper.getView(R.id.platform_tips_title);
        title.setText(data.getTitle());
        TextView date = helper.getView(R.id.platform_tips_date);
        //日期 1970-01-01
        date.setText(RegexUtil.format(data.getReleaseTime(), RegexUtil.defaultDatePattern));
        TextView content = helper.getView(R.id.platform_tips_content);
        content.setText(Html.fromHtml(data.getContentText()));
    }
    @Override
    public void convert(ViewHolder helper, TipsPageDataBean tipsPageDataBean, List<Object> payloads) {

    }
}
