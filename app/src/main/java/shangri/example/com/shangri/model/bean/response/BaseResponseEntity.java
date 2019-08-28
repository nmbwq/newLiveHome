package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 * 统一封装的基类
 * Created by chengaofu on 2017/6/19.
 */

public class BaseResponseEntity<T> implements Serializable {

    private String code;
    //人脸认证与百度云对接
    private String access_token;
    private String  score;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
    private T data;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
