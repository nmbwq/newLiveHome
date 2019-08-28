package shangri.example.com.shangri.wheelviewSelectDays;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.wheelviewSelectDays.wheelview.OnWheelChangedListener;
import shangri.example.com.shangri.wheelviewSelectDays.wheelview.OnWheelScrollListener;
import shangri.example.com.shangri.wheelviewSelectDays.wheelview.WheelView;
import shangri.example.com.shangri.wheelviewSelectDays.wheelview.adapter.AbstractWheelTextAdapter1;
import shangri.example.com.shangri.wheelviewSelectDays.wheelview.adapter.WheelViewAdapter;

/**
 * Author:  Chen.yuan
 * Email:   hubeiqiyuan2010@163.com
 * Date:    2016/7/28 17:37
 * Description:自定义日期选择器  只有两列或是一列   所以年这里一列隐藏
 */
public class MyChangeChangeDatePopwindow extends PopupWindow implements View.OnClickListener {

    private Context context;
    private WheelView wvYear;
    private WheelView wvMonth;
    private WheelView wvDay;

    private TextView btnSure;
    private TextView btnCancel;

    private ArrayList<String> arry_years = new ArrayList<String>();
    private ArrayList<String> arry_months = new ArrayList<String>();
    private ArrayList<String> arry_days = new ArrayList<String>();
    private CalendarTextAdapter mYearAdapter;
    private CalendarTextAdapter mMonthAdapter;
    private CalendarTextAdapter mDaydapter;

    private String month;
    private String day;


    private int maxTextSize = 24;
    private int minTextSize = 14;

    private boolean issetdata = false;

    private String selectYear = "";
    private String selectMonth = "";
    private String selectDay = "";

    //默认显示的位置
    private int DefaultPositionYear = 0;
    private int DefaultPositionMonth = 0;
    private int DefaultPositionDay = 0;

    //    0是选择薪资  1是选择底薪
    int symbol;

    private OnBirthListener onBirthListener;

    public MyChangeChangeDatePopwindow(final Context context, int symbol1, ArrayList<String> list_months, ArrayList<String> list_days) {
        super(context);
        this.context = context;
        View view = View.inflate(context, R.layout.new_dialog_myinfo_changebirth, null);
        wvYear = (WheelView) view.findViewById(R.id.wv_birth_year);
        wvMonth = (WheelView) view.findViewById(R.id.wv_birth_month);
        wvDay = (WheelView) view.findViewById(R.id.wv_birth_day);
        btnSure = (TextView) view.findViewById(R.id.btn_myinfo_sure);
        btnCancel = (TextView) view.findViewById(R.id.btn_myinfo_cancel);
        //根据需求直接隐藏 用不到
        symbol = symbol1;
        wvYear.setVisibility(View.GONE);
        for (int i = 0; i < list_months.size(); i++) {
            arry_months.add(list_months.get(i));
        }
        //取中间的位置   总数如果是偶数   直接除以2   如果是奇数 总数量加上1在进行除以2
        if (list_months.size() % 2 == 0) {
            DefaultPositionMonth = list_months.size() / 2;
        } else {
            DefaultPositionMonth = (list_months.size() + 1) / 2;
        }
        selectMonth = list_months.get(DefaultPositionMonth);
        wvMonth.setVisibility(View.VISIBLE);


        for (int i = 0; i < list_days.size(); i++) {
            arry_days.add(list_days.get(i));
        }
        //取中间的位置   总数如果是偶数   直接除以2   如果是奇数 总数量加上1在进行除以2
        if (list_days.size() % 2 == 0) {
            DefaultPositionDay = list_days.size() / 2;
        } else {
            DefaultPositionDay = (list_days.size() + 1) / 2;
        }
        selectDay = list_days.get(DefaultPositionDay);
        wvDay.setVisibility(View.VISIBLE);

        if (symbol == 0) {
            wvMonth.setVisibility(View.VISIBLE);
        } else {
            wvMonth.setVisibility(View.GONE);
        }

        //设置SelectPicPopupWindow的View
        this.setContentView(view);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
//		this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

        btnSure.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        mYearAdapter = new CalendarTextAdapter(context, arry_years, DefaultPositionYear, maxTextSize, minTextSize);
        wvYear.setVisibleItems(5);
        wvYear.setViewAdapter((WheelViewAdapter) mYearAdapter);
        wvYear.setCurrentItem(DefaultPositionYear);

        mMonthAdapter = new CalendarTextAdapter(context, arry_months, DefaultPositionMonth, maxTextSize, minTextSize);
        wvMonth.setVisibleItems(5);
        wvMonth.setViewAdapter((WheelViewAdapter) mMonthAdapter);
        wvMonth.setCurrentItem(DefaultPositionMonth);

        mDaydapter = new CalendarTextAdapter(context, arry_days, DefaultPositionDay, maxTextSize, minTextSize);
        wvDay.setVisibleItems(5);
        wvDay.setViewAdapter((WheelViewAdapter) mDaydapter);
        wvDay.setCurrentItem(DefaultPositionDay);

        wvYear.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) mYearAdapter.getItemText(wheel.getCurrentItem());
                selectYear = currentText;
                setTextviewSize(currentText, mYearAdapter);
            }
        });

        wvYear.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) mYearAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mYearAdapter);
            }
        });

        wvMonth.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) mMonthAdapter.getItemText(wheel.getCurrentItem());
                selectMonth = currentText;
                setTextviewSize(currentText, mMonthAdapter);
            }
        });

        wvMonth.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) mMonthAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mMonthAdapter);
            }
        });

        wvDay.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) mDaydapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mDaydapter);
                selectDay = currentText;
            }
        });

        wvDay.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) mDaydapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mDaydapter);
            }
        });
    }

    private class CalendarTextAdapter extends AbstractWheelTextAdapter1 {
        ArrayList<String> list;

        protected CalendarTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
            super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
            this.list = list;
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return list.get(index) + "";
        }
    }

    public void setBirthdayListener(OnBirthListener onBirthListener) {
        this.onBirthListener = onBirthListener;
    }

    @Override
    public void onClick(View v) {

        if (v == btnSure) {
            if (onBirthListener != null) {
                String k = selectMonth.replace("K", "");
                String k1 = selectDay.replace("K", "");
                if (Integer.parseInt(k) >= Integer.parseInt(k1)) {
                    ToastUtil.showShort("选择后面金额应大于前面金额");
                    return;
                } else {
                    onBirthListener.onClick(symbol, selectYear, selectMonth, selectDay);
                    Log.d("cy", "" + selectYear + "" + selectMonth + "" + selectDay);
                }
            }
        } else if (v == btnSure) {

        } else {
            dismiss();
        }
        dismiss();

    }

    public interface OnBirthListener {
        public void onClick(int symbol, String year, String month, String day);
    }

    /**
     * 设置字体大小
     *
     * @param curriteItemText
     * @param adapter
     */
    public void setTextviewSize(String curriteItemText, CalendarTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(maxTextSize);
            } else {
                textvew.setTextSize(minTextSize);
            }
        }
    }


}