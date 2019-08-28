package shangri.example.com.shangri.model.bean.request;

import java.io.Serializable;

/**
 *  banner的请求类型
 * Created by chengaofu on 2017/6/28.
 */

public class BannerBean implements Serializable {

    private String typeId;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}
