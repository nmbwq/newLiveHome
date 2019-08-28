package shangri.example.com.shangri.model.bean.request;

/**
 * 请求数据公共类
 * Created by chengaofu on 2017/6/21.
 */

public class BaseRequestEntity<T> {
//    private String timestamp;
//    private String nonce;
//    private String sign;
    private T data;
//
//    public String getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(String timestamp) {
//        this.timestamp = timestamp;
//    }
//
//    public String getNonce() {
//        return nonce;
//    }
//
//    public void setNonce(String nonce) {
//        this.nonce = nonce;
//    }
//
//    public String getSign() {
//        return sign;
//    }
//
//    public void setSign(String sign) {
//        this.sign = sign;
//    }
//
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
