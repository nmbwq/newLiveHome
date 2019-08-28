package shangri.example.com.shangri.ui.listener;

import com.jph.takephoto.model.TResult;

/**
 * 选取图片结束 或者是拍完照结束 返回的结果
 * Created by chengaofu on 2017/7/1.
 */

public interface TakephotoFinishListener {

    void takeSuccess(TResult result);

    void takeFail(TResult result, String msg);

    void takeCancel();
}
