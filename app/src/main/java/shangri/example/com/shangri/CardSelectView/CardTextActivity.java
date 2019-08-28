package shangri.example.com.shangri.CardSelectView;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shangri.example.com.shangri.R;

public class CardTextActivity extends AppCompatActivity {

    private List<SwipeCardBean> mDatas;
    RenRenCallback callback;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatas = SwipeCardBean.initDatas();
                recyclerView.getAdapter().notifyDataSetChanged();

            }
        });

        mDatas = SwipeCardBean.initDatas();

        recyclerView.setLayoutManager(new OverLayCardLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter());

        callback = new RenRenCallback();
        callback.setSwipeListener(new RenRenCallback.OnSwipeListener() {
            @Override
            public void onSwiped(int adapterPosition, int direction) {
                if (direction == ItemTouchHelper.DOWN || direction == ItemTouchHelper.UP) {
                    mDatas.add(0, mDatas.remove(adapterPosition));
//                    Collections.swap(mDatas, 0, adapterPosition);
                    recyclerView.getAdapter().notifyDataSetChanged();
                } else {
                    mDatas.remove(adapterPosition);
//                    recyclerView.getAdapter().notifyItemRemoved(adapterPosition);
                }
                if (mDatas.size() == 0) {
                    Log.d("Debug", "卡片数据没有了");
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onSwipeTo(RecyclerView.ViewHolder viewHolder, float offset) {
            }
        });
        new ItemTouchHelper(callback).attachToRecyclerView(recyclerView);

        findViewById(R.id.to_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.toLeft(recyclerView);
            }
        });
        findViewById(R.id.to_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.toRight(recyclerView);
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

//        TextView mTextView;
//        ImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        public int dp(int dp) {
            final float density = getResources().getDisplayMetrics().density;
            return (int) (dp * density);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            final View view = LayoutInflater.from(CardTextActivity.this).inflate(R.layout.item_renren_layout, parent, false);

//            CardView cardView = new CardView(CardTextActivity.this);
//            cardView.setLayoutParams(new ViewGroup.LayoutParams(dp(270), dp(300)));
//            cardView.setCardBackgroundColor(Color.parseColor("#393F4E"));
////            cardView.setBackgroundColor(Color.BLUE);
//            TextView textView = new TextView(CardTextActivity.this);
////            textView.setBackgroundColor(Color.YELLOW);
//
//            ImageView imageView = new ImageView(CardTextActivity.this);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//
//            textView.setPadding(0, dp(260), 0, 0);
//
//            cardView.addView(textView, new ViewGroup.LayoutParams(dp(270), dp(300)));
//            cardView.addView(imageView, new ViewGroup.LayoutParams(dp(270), dp(260)));

            final MyViewHolder myViewHolder = new MyViewHolder(view);
//            myViewHolder.mTextView = textView;
//            myViewHolder.mImageView = imageView;
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
//            Glide.with(CardTextActivity.this).load(mDatas.get(position).getUrl()).fitCenter().into((ImageView) holder.itemView.findViewById(R.id.image_view));
//            ((TextView) holder.itemView.findViewById(R.id.text_view)).setText(mDatas.get(position).getName());
//            TextView viewById = (TextView) holder.itemView.findViewById(R.id.text_view);
//            viewById.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("Debug", "点击事件");
//                    callback.toLeft(recyclerView);
//                }
//            });
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
    }

}
