package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/7.
 */

public class encyclopediaRequestBean extends BaseBeen {
    private int type;
    private String search;
    private int id;

    //我的合同的界面需要参数

    private int page;
    //    模版H5展示需要参数
    private String table_name;
    //签署页面需要的验证码
    private String verify_code;

    public String getVerify_code() {
        return verify_code;
    }

    public void setVerify_code(String verify_code) {
        this.verify_code = verify_code;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public encyclopediaRequestBean() {
    }
}
