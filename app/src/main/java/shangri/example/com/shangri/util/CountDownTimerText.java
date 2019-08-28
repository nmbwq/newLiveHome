package shangri.example.com.shangri.util;

import android.os.CountDownTimer;
import android.widget.TextView;


public class CountDownTimerText extends CountDownTimer {
    public interface CountDownFinishListener {
        void finish();
    }

    TextView view;
    String txt;
    String formatTxt;
    CountDownFinishListener finishListener = null;

    public CountDownTimerText(TextView view, String formatTxt, String txt, long millisInFuture, CountDownFinishListener listener) {
        super(millisInFuture, 1000);
        this.view = view;
        this.formatTxt = formatTxt;
        this.txt = txt;
        this.view.setClickable(false);
        finishListener = listener;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        // TODO Auto-generated method stub        
        String content = String.format(formatTxt, millisUntilFinished / 1000);
        view.setText(content);
        view.setClickable(false);
    }

    @Override
    public void onFinish() {
        // TODO Auto-generated method stub
        view.setClickable(true);
        view.setText(txt);
        if (finishListener != null) {
            finishListener.finish();
        }
    }

    public void Stop() {
        this.cancel();
    }

}
