package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/2/1.
 */

public class CompactListResponse implements Serializable {


    /**
     * current_page : 1
     * total_page : 1
     * contracts : [{"id":1,"title":"收到福建省的看法","status":3,"nickname":"地方","create_date":"2018-06-19"}]
     */

    private int current_page;
    private int total_page;
    private List<ContractsBean> contracts;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public List<ContractsBean> getContracts() {
        return contracts;
    }

    public void setContracts(List<ContractsBean> contracts) {
        this.contracts = contracts;
    }

    public static class ContractsBean implements Serializable{
        /**
         * id : 1
         * title : 收到福建省的看法
         * status : 3
         * nickname : 地方
         * create_date : 2018-06-19
         */

        private int id;
        private String title;
        private int status;
        private String nickname;
        private String create_date;
//        是否已经生成PDF 0否 1是
        private int is_pdf;
        private String table_name;

        public String getTable_name() {
            return table_name;
        }

        public void setTable_name(String table_name) {
            this.table_name = table_name;
        }

        public int getIs_pdf() {
            return is_pdf;
        }

        public void setIs_pdf(int is_pdf) {
            this.is_pdf = is_pdf;
        }

        public ContractsBean() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }
    }
}

