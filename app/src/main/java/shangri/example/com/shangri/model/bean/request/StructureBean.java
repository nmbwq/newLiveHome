package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/3/28.
 */

public class StructureBean extends BaseBeen {

    int position;
    String text;
    boolean isClick;


    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public StructureBean(int position, String text, boolean isClick) {
        this.position = position;
        this.text = text;
        this.isClick = isClick;
    }

    public StructureBean(int position, String text) {
        this.position = position;
        this.text = text;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public StructureBean() {
    }


}
