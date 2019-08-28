package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/2/1.
 */

public class PersonalData extends BaseBeen {
    private String field;
    private String value;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
