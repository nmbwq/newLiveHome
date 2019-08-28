package shangri.example.com.shangri.model.bean.event;

/**
 * 浏览数量
 * Created by zuyuli on 2017/7/4.
 */

public class FourshowEventBean {

    private Boolean isshow;
    private int type = 0;
    //判断是否来自第五底导点击事件
    private Boolean isFromFive = false;

    //判断是不是来自我的页面（功能是判断是否有未读消息）
    private Boolean isFromMine = false;



    //下面是有关音频需要的信息
    public Boolean isFromVideo = false;
    //音乐图片
    public String image="";
    //音乐名称
    public String name="";
    //发布时间
    public String time="";
    //音乐总时间  以及现在播放的时间   用在求出百分比
    public String SumNumberTime="";
    public String NowTime="";
    //是否是停止
    public Boolean stop=false;
    //是否来自极光推送  状态为10  弹出小播推荐全局弹窗
    public boolean isFromTuisongAllDialog=false;
    //主播端   是否来自第三底导 赚钱 点击简历投递去完成 跳转到招聘的界面
    public boolean isfromMakeMoney=false;

    public boolean isIsfromMakeMoney() {
        return isfromMakeMoney;
    }

    public void setIsfromMakeMoney(boolean isfromMakeMoney) {
        this.isfromMakeMoney = isfromMakeMoney;
    }

    public boolean isFromTuisongAllDialog() {
        return isFromTuisongAllDialog;
    }

    public void setFromTuisongAllDialog(boolean fromTuisongAllDialog) {
        isFromTuisongAllDialog = fromTuisongAllDialog;
    }



    public FourshowEventBean(Boolean isFromVideo, Boolean stop) {
        this.isFromVideo = isFromVideo;
        this.stop = stop;
    }

    public FourshowEventBean(Boolean isFromVideo, String image, String name, String time, String sumNumberTime, String nowTime) {
        this.isFromVideo = isFromVideo;
        this.image = image;
        this.name = name;
        this.time = time;
        SumNumberTime = sumNumberTime;
        NowTime = nowTime;
    }

    public Boolean getFromVideo() {
        return isFromVideo;
    }

    public void setFromVideo(Boolean fromVideo) {
        isFromVideo = fromVideo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSumNumberTime() {
        return SumNumberTime;
    }

    public void setSumNumberTime(String sumNumberTime) {
        SumNumberTime = sumNumberTime;
    }

    public String getNowTime() {
        return NowTime;
    }

    public void setNowTime(String nowTime) {
        NowTime = nowTime;
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }

    public Boolean getFromFive() {
        return isFromFive;
    }

    public void setFromFive(Boolean fromFive) {
        isFromFive = fromFive;
    }

    public FourshowEventBean(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public FourshowEventBean() {
    }

    public FourshowEventBean(Boolean isshow) {
        this.isshow = isshow;
    }

    public Boolean getFromMine() {
        return isFromMine;
    }

    public void setFromMine(Boolean fromMine) {
        isFromMine = fromMine;
    }

    public Boolean getIsshow() {
        return isshow;
    }

    public void setIsshow(Boolean isshow) {
        this.isshow = isshow;
    }
}
