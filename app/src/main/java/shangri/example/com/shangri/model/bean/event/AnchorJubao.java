package shangri.example.com.shangri.model.bean.event;

/**
 * 浏览数量
 * Created by zuyuli on 2017/7/4.
 */

public class AnchorJubao {
    //1 移除主播页面刷新
    int type;

    public AnchorJubao(int type) {
        this.type = type;
    }

    public AnchorJubao() {
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
