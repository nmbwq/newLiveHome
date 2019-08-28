package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/3/28.
 */

public class CompanyRequestBean extends BaseBeen {
    public String search;
    //请求公司详情接口
    public String company_id;

    //申请成为管理员
    public String deacon_id;
    public String guild_id;

    //帮助中心统计需要参数
//    帮助问题ID
    public int qa_id;
//    是否有用 1:是 2:不是
    public int is_useful;


    public int getQa_id() {
        return qa_id;
    }

    public void setQa_id(int qa_id) {
        this.qa_id = qa_id;
    }

    public int getIs_useful() {
        return is_useful;
    }

    public void setIs_useful(int is_useful) {
        this.is_useful = is_useful;
    }

    public String getDeacon_id() {
        return deacon_id;
    }

    public void setDeacon_id(String deacon_id) {
        this.deacon_id = deacon_id;
    }

    public String getGuild_id() {
        return guild_id;
    }

    public void setGuild_id(String guild_id) {
        this.guild_id = guild_id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
