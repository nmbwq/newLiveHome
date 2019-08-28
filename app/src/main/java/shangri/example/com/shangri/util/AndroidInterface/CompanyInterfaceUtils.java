package shangri.example.com.shangri.util.AndroidInterface;

import shangri.example.com.shangri.ui.adapter.PatrolAdapter;

/**
 * Created by Administrator on 2018/5/9.
 */

public class CompanyInterfaceUtils {

    public static CompanyFace companyFace;
    public static AnchorChectFace anchorChectFace;
    public static ApllayFace apllayFace;
    public static AlertFace alertFace;
    public static RefushFace refushFace;

    public static CallPhoneFace callPhoneFace;

    public static VideoFace videoFace;
    public static TellAboutFace tellAboutFace;
    public static yueLiaoMessageFace yueLiaoMessageFace;
    public static void setCallBack(CompanyFace callBack) {
        companyFace = callBack;
    }

    public static void setChectBack(AnchorChectFace callBack) {
        anchorChectFace = callBack;
    }

    public static void setApllayBack(ApllayFace callBack) {
        apllayFace = callBack;
    }

    public static void setAlertBack(AlertFace callBack) {
        alertFace = callBack;
    }

    public static void setRefushBack(RefushFace callBack) {
        refushFace = callBack;
    }

    public static void setcallPhoneFace(CallPhoneFace callBack) {
        callPhoneFace = callBack;
    }

    public static void setVideoFace(VideoFace callBack) {
        videoFace = callBack;
    }
    public static void setTellAboutFace(TellAboutFace callBack) {
        tellAboutFace = callBack;
    }

    public static void setyueLiaoMessageFace(yueLiaoMessageFace callBack) {
        yueLiaoMessageFace = callBack;
    }



}
