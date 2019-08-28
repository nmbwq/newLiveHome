package shangri.example.com.shangri.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;
import java.util.zip.Inflater;

import shangri.example.com.shangri.R;

public class TextAdapter extends TagAdapter<String> {
    private Context context;

    public TextAdapter(Context context, List<String> datas) {
        super(datas);
        this.context = context;
    }

    @Override
    public View getView(FlowLayout parent, int position, String s) {
        TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.tv_flowlayout, null);
        tv.setText(s);
        return tv;


    }
}
