package shangri.example.com.shangri.PinterestRecycle;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import shangri.example.com.shangri.R;


class StaggeredGridHolder extends RecyclerView.ViewHolder {
    TextView sg_item;

    public StaggeredGridHolder(final View itemView) {
        super(itemView);
        sg_item = (TextView) itemView.findViewById(R.id.sg_item);
    }
}

