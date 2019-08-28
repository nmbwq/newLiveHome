package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 * 版本返回信息
 * Created by chengaofu on 2017/7/1.
 */

public class AppVersionBean implements Serializable {

    private Long update_time;
    private String code;
    private Long create_time;
    private String intro;
    private String name;
    private Long id;
    private String url;
    public void setUpdate_time(Long update_time) {
        this.update_time = update_time;
    }
    public Long getUpdate_time() {
        return update_time;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }
    public Long getCreate_time() {
        return create_time;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
    public String getIntro() {
        return intro;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
}
