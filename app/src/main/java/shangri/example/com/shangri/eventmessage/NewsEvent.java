package shangri.example.com.shangri.eventmessage;

public class NewsEvent {


    public int advicesType;
    public int position;
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

    public NewsEvent(Boolean isFromVideo, Boolean stop) {
        this.isFromVideo = isFromVideo;
        this.stop = stop;
    }

    public NewsEvent(int advicesType, int position) {
        this.advicesType = advicesType;
        this.position = position;
    }

    public NewsEvent(Boolean isFromVideo, String image, String name, String time, String sumNumberTime, String nowTime) {
        this.isFromVideo = isFromVideo;
        this.image = image;
        this.name = name;
        this.time = time;
        SumNumberTime = sumNumberTime;
        NowTime = nowTime;
    }

    public NewsEvent() {
    }
}
