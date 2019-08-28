package shangri.example.com.shangri;

/**
 * Created by chengaofu on 2017/6/23.
 */

public class Constant {
    public static final String KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDAwpyRmHA0U/RTmaGMiCHjYXJ8\n" +
            "Zd0ICGBLhdOQWTUupKdA1OHa0zGkOh4hL/mtWBZ4VmJQTXZu+8xBVMvymCBSzNOQ\n" +
            "fVp+4VVXXtawnQk7f2Jb18O+t57mmEhaY/Bs/vj5AfLYjPbmGNtuDQc/4ERP9cis\n" +
            "zlGC1Ai5Hi9koQgfdwIDAQAB";

    //根据该字段在SharedPreference中是否存在
    public static final String IS_GUIDED = "guide";
    //资讯fragment的类型
    public static final int TYPE_HOT = 0x01;
    public static final int TYPE_VIDEO = 0x02;
    public static final int TYPE_ANCHOR = 0x03;
    public static final int TYPE_ENTERTAIN = 0x04;

    public static final int EVENT_NETWORK_ENTRY = 0x05; //重新请求网络

    public static final int TYPE_PRAISE = 0x06; //培训点赞
    public static final int TYPE_Guild = 0x022; //公会解读点赞
    public static final int TYPE_BROWSE = 0x07; //培训浏览
    public static final int TYPE_COllECT = 0x12; //培训收藏

    public static final int TYPE_HEAD_PRAISE = 0x10; //头条点赞
    public static final int TYPE_HEAD_BROWSE = 0x11; //头条浏览
    public static final int TYPE_HEAD_COllECT = 0x13; //头条收藏
    public static final int TYPE_Guild_COllECT = 0x15; //公会收藏

    //code的类型
    public static final int CODE_100027 = 100027; //token失效 请先登录


    public static final int SearchActivity = 0x08;
    public static final int ConsultationFragment = 0x09;

}
