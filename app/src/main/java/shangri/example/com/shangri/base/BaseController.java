package shangri.example.com.shangri.base;

import android.app.Activity;
import android.os.Message;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * 消息分发控制器
 * Created by chengaofu on 2017/6/5.
 */

public class BaseController {

    @Subscribe
    public void register(Activity context) {
        if (!EventBus.getDefault().isRegistered(context)) {
            EventBus.getDefault().register(context);
        }
    }

    @Subscribe
    public void unregister(Activity context) {
        EventBus.getDefault().unregister(context);
    }

    //创建BaseEvent, BaseEvent进行接收消息
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEventMain(Activity context, Message message) {
    }

    @Subscribe(threadMode = ThreadMode.POSTING, priority = 2, sticky = true)
    public void onMessageEventPost(Activity context, Message message) {

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND, priority = 3)
    public void onMessageEventBackground(Activity context, Message message) {

    }


    @Subscribe(threadMode = ThreadMode.ASYNC, priority = 4)
    public void onMessageEventAsync(Activity context, Message message) {
        // 不能在这里执行更新ui的操作

    }
}
