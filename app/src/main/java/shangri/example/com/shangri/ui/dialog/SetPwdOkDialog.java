package shangri.example.com.shangri.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseDialogFragment;
import shangri.example.com.shangri.util.ActivityManager;
import shangri.example.com.shangri.util.DensityUtil;

/**
 * 设置密码成功
 * Created by chengaofu on 2017/7/2.
 */

public class SetPwdOkDialog extends BaseDialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.LoadingDialog);
        //初始化数据
//        mCircleAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_round_rotate);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_set_pwd,container,false);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(  //设置DialogFragment的宽高
                DensityUtil.dip2px(getActivity(),243),DensityUtil.dip2px(getActivity(),283));
        view.setLayoutParams(params);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.set_pwd_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                ActivityManager.getInstance().getLastActivity().finish();
            }
        });
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
