package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 * 资讯类型
 * Created by chengaofu on 2017/6/28.
 */

public class NewsTypeInfoBean implements Serializable {

    private String name;
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
