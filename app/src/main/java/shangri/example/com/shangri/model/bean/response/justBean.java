package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class justBean implements Serializable {

    boolean isOpen=false;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public justBean(boolean isOpen) {
        this.isOpen = isOpen;
    }
}


