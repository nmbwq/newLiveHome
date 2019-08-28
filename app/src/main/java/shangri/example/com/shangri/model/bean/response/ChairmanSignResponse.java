package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/1.
 */

public class ChairmanSignResponse implements Serializable {


    public int id;
    public String contract_url;
    //主播签章以及会长撤销返回参数
    public int ok;

    public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }

    public ChairmanSignResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContract_url() {
        return contract_url;
    }

    public void setContract_url(String contract_url) {
        this.contract_url = contract_url;
    }
}

